package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.client.Evenement;
import model.client.Ville;

public class EvenementServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/admin/evenement.jsp");
        if(mode.equals("r")){
            try {
                ArrayList<Ville> listeVille = Ville.get_liste_ville();
                HashMap<String,Integer> nombreEvenement = Evenement.get_nombre_evenement();
                ArrayList<Evenement> listeEvenement = Evenement.get_liste_evenement_calendrier();
                request.setAttribute("listeVille", listeVille);
                request.setAttribute("nombreEvenement", nombreEvenement);
                request.setAttribute("listeEvenement", listeEvenement);
                request.setAttribute("page", "evenement");
            } catch (Exception e) {
                
            }
        }
        disp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(request, response);
    }
    
}
