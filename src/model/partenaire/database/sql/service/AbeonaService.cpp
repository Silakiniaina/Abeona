#include <windows.h>
#include <iostream>
#include <fstream>
#include <thread>
#include <chrono>
#include <libpq-fe.h>
#include <ctime>
#include <tchar.h>
#include <strsafe.h>
#include <sstream>

#define SERVICE_NAME _T("AbeonaService")
#define LOG_DIRECTORY _T("Logs")
#define LOGS_DIRECTORY _T("C:\\Program Files\\AbeonaService\\Logs");

SERVICE_STATUS g_ServiceStatus = { 0 };
SERVICE_STATUS_HANDLE g_StatusHandle = NULL;
HANDLE g_ServiceStopEvent = INVALID_HANDLE_VALUE;

void WINAPI ServiceMain(DWORD argc, LPTSTR *argv);
void WINAPI ServiceCtrlHandler(DWORD CtrlCode) ;
void InsertValueIntoDatabase();

DWORD WINAPI ServiceWorkerThread(LPVOID lpParam) ;
DWORD WINAPI ServiceWorkerThread(LPVOID lpParam) ;

std::string GetCurrentDateTimeString(const char* format = "%Y-%m-%d %H:%M:%S");
std::chrono::system_clock::time_point getNextHour();

int main()
{

    SERVICE_TABLE_ENTRY ServiceTable[] =
    {
        {
            (LPSTR)SERVICE_NAME,
            (LPSERVICE_MAIN_FUNCTION)ServiceMain
        },
        {
            NULL,
            NULL
        }

    };

    if (StartServiceCtrlDispatcher(ServiceTable) == FALSE)
    {
        return GetLastError();
    }
    
    return 0;

}

void WINAPI ServiceMain(DWORD argc, LPTSTR *argv) 
{
    g_StatusHandle = RegisterServiceCtrlHandler((LPSTR)SERVICE_NAME, ServiceCtrlHandler);

    if ( g_StatusHandle == NULL )
    {
        return;
    }

    ZeroMemory(&g_ServiceStatus, sizeof(g_ServiceStatus));
    g_ServiceStatus.dwServiceType = SERVICE_WIN32_OWN_PROCESS;
    g_ServiceStatus.dwControlsAccepted = 0;
    g_ServiceStatus.dwCurrentState = SERVICE_START_PENDING;
    g_ServiceStatus.dwWin32ExitCode = 0;
    g_ServiceStatus.dwServiceSpecificExitCode = 0;
    g_ServiceStatus.dwCheckPoint = 0;

    SetServiceStatus(g_StatusHandle, &g_ServiceStatus);

    g_ServiceStopEvent = CreateEvent(NULL, TRUE, FALSE, NULL);

    if (g_ServiceStopEvent == NULL)
    {
        g_ServiceStatus.dwControlsAccepted = 0;
        g_ServiceStatus.dwCurrentState = SERVICE_STOPPED;
        g_ServiceStatus.dwWin32ExitCode = GetLastError();
        g_ServiceStatus.dwCheckPoint = 1;

        SetServiceStatus(g_StatusHandle, &g_ServiceStatus);
        return;
    }

    g_ServiceStatus.dwControlsAccepted = SERVICE_ACCEPT_STOP;
    g_ServiceStatus.dwCurrentState = SERVICE_RUNNING;
    g_ServiceStatus.dwWin32ExitCode = 0;
    g_ServiceStatus.dwCheckPoint = 0;

    SetServiceStatus(g_StatusHandle,&g_ServiceStatus);
    
    HANDLE hThread = CreateThread(NULL, 0, ServiceWorkerThread, NULL, 0, NULL);

    WaitForSingleObject(g_ServiceStopEvent, INFINITE);

    CloseHandle(g_ServiceStopEvent);

    g_ServiceStatus.dwControlsAccepted = 0;
    g_ServiceStatus.dwCurrentState = SERVICE_STOP;
    g_ServiceStatus.dwWin32ExitCode = 0;
    g_ServiceStatus.dwCheckPoint = 3;

    SetServiceStatus(g_StatusHandle, &g_ServiceStatus);

}

void WINAPI ServiceCtrlHandler(DWORD CtrlCode) 
{
    
    switch (CtrlCode)
    {
    case SERVICE_CONTROL_STOP:
        if (g_ServiceStatus.dwCurrentState != SERVICE_RUNNING) break;

        g_ServiceStatus.dwControlsAccepted = 0;
        g_ServiceStatus.dwCurrentState = SERVICE_STOP_PENDING;
        g_ServiceStatus.dwWin32ExitCode = 0;
        g_ServiceStatus.dwCheckPoint = 4;

        SetServiceStatus(g_StatusHandle, &g_ServiceStatus);

        SetEvent(g_ServiceStopEvent);
        break;
    
    default:
        break;
    }

}

DWORD WINAPI ServiceWorkerThread(LPVOID lpParam) 
{

    while (WaitForSingleObject(g_ServiceStopEvent, 0) != WAIT_OBJECT_0)
    {
        InsertValueIntoDatabase();
        auto next_hour = getNextHour();
        std::this_thread::sleep_until(next_hour);
    }

    return ERROR_SUCCESS;

}

void InsertValueIntoDatabase() 
{

    TCHAR szCurrentPath[MAX_PATH];
    TCHAR szNewDir[MAX_PATH];

    DWORD dwResult = GetCurrentDirectory(MAX_PATH, szCurrentPath);

    if (dwResult == 0)
    {
        std::cerr << "Error getting current directory: " << GetLastError() << std::endl;
        return;
    }

    _stprintf_s(szNewDir, MAX_PATH, _T("%s\\%s"), szCurrentPath, LOG_DIRECTORY);
    
    std::string datetime = GetCurrentDateTimeString("%Y-%m-%d");

    #ifdef UNICODE
        std::wstringstream ws;
        ws << szNewDir << L"\\" << datetime.c_str() << L".log";

        std::wstring fullPathW = ws.str();

        std::string fullPath(fullPathW.begin(), fullPathW.end());
    #else
        std::stringstream ss;
        ss << szNewDir << "\\" << datetime.c_str() << ".log";
        std::string fullPath = ss.str();
    #endif

    std::string currentDateTime = GetCurrentDateTimeString();

    std::ofstream logfile(fullPath.c_str(), std::ios_base::app);

    if (!logfile.is_open())
    {
        std::cerr << "Failed to open log file" << std::endl;
        return;
    }

    logfile << "Writing to database at: " << currentDateTime << std::endl;

    const char* conninfo = "dbname=abeona user=admin_abeona password=admin hostaddr=127.0.0.1 port=5432";

    PGconn *conn = PQconnectdb(conninfo);

    if ( PQstatus(conn) != CONNECTION_OK )
    {
        std::cerr << "Connection to database failed: " << PQerrorMessage(conn) << std::endl;
        logfile << "Connection to database failed: " << PQerrorMessage(conn) << std::endl;
        PQfinish(conn);
        return;
    }
    else
    {
        std::cout << "Opened database successfully" << std::endl;
        logfile << "Opened database succefully" << std::endl;
    }
    
    std::string sql = "INSERT INTO update_log(actual) VALUES ('" + currentDateTime  + "')";

    PGresult *res = PQexec(conn, sql.c_str());

    if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        std::cerr << "Insert failed: " << PQerrorMessage(conn) << std::endl;
        logfile << "Insert failed: " << PQerrorMessage(conn) << std::endl;
    }
    else
    {
        std::cout << "Record inserted successfully" << std::endl;
        logfile << "Record inserted successfully" << std::endl;
    }

    PQclear(res);
    PQfinish(conn);
    logfile.close();

}

std::string GetCurrentDateTimeString(const char* format)
{

    time_t now = time(0);
    struct tm* timeinfo = localtime(&now);
    char buffer[80];
    strftime(buffer, 80, format, timeinfo);

    return buffer;

}

void WriteToLogFile(std::string message, std::ofstream logfile)
{
    logfile << message << std::endl;
}

std::chrono::system_clock::time_point getNextHour()
{
    std::time_t now = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());

    std::tm* tm_now = std::localtime(&now);

    tm_now->tm_hour += 1;
    tm_now->tm_min = 0;
    tm_now->tm_sec = 0;

    std::time_t next_hour_time = std::mktime(tm_now);

    return std::chrono::system_clock::from_time_t(next_hour_time);
}