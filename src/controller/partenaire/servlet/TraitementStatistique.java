package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stat.StatNombreclients;
import stat.StatNombreclients_ayant_achete_pack;

public class TraitementStatistique extends HttpServlet {

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

        StatNombreclients nombreclients = new StatNombreclients();
        List<StatNombreclients> getNombreclients = null;
        StatNombreclients_ayant_achete_pack nombreclients_ayant_achete_pack = new StatNombreclients_ayant_achete_pack();
        List<StatNombreclients_ayant_achete_pack> getNombreclients_ayant_achete_pack = null; // Corrected variable name
        
        if (year1 != 0) { // Primitive type check for non-zero instead of null
            getNombreclients = nombreclients.getAllYear(year1);
            getNombreclients_ayant_achete_pack = nombreclients_ayant_achete_pack.getAllYear(year1);
        } else {
            getNombreclients = nombreclients.getAll();
            getNombreclients_ayant_achete_pack = nombreclients_ayant_achete_pack.getAll();
        }
        
        
        request.setAttribute("nombreclients", getNombreclients);
        request.setAttribute("nombreclients_ayant_achete_pack", getNombreclients_ayant_achete_pack);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/stat/statistique.jsp");
        dispatcher.forward(request, response);
    }

}
