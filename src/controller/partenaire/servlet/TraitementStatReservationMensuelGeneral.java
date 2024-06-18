package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stat.StatReservationMensuelGeneral;

public class TraitementStatReservationMensuelGeneral extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new ServletException("Erreur lors du traitement de la requête", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new ServletException("Erreur lors du traitement de la requête", e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        String year1Param = request.getParameter("year1");
        
        int year1 = 0;
        
        if (year1Param != null) {
            try {
                year1 = Integer.parseInt(year1Param);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        StatReservationMensuelGeneral statReservationMensuel = new StatReservationMensuelGeneral();
        List<StatReservationMensuelGeneral> getStatReservationMensuel = null;
        if (year1 != 0) {
            getStatReservationMensuel = statReservationMensuel.getAllByYear(year1);
        }else{
            getStatReservationMensuel = statReservationMensuel.getAll();
        }
        request.setAttribute("statReservationMensuelGeneral", getStatReservationMensuel);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/stat/statReservationMensuelGeneral.jsp");
        dispatcher.forward(request, response);
    }
}
