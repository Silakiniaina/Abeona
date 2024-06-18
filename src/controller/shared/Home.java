package controller.shared;

import java.io.IOException;
import model.shared.URL;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
        disp.forward(request, response);
    }

    // Override methods
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}