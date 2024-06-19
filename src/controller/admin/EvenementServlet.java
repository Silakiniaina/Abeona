package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.client.CategorieEvenement;
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
                ArrayList<CategorieEvenement> categorieEvenement = CategorieEvenement.get_liste_categorie_evenement();
                request.setAttribute("listeVille", listeVille);
                request.setAttribute("nombreEvenement", nombreEvenement);
                request.setAttribute("listeEvenement", listeEvenement);
                request.setAttribute("categories", categorieEvenement);
                request.setAttribute("page", "evenement");
            } catch (Exception e) {
                
            }
        }
        disp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        PrintWriter out = response.getWriter();
        try {
            
            if(mode != null){
                
            }else{
                String titre = request.getParameter("titre");
                String categorie = request.getParameter("categorie");
                Date date = Date.valueOf(request.getParameter("date_evenement"));
                String description = request.getParameter("desc");
                Evenement e = new Evenement(titre, description, null, date, date, null, categorie, null);
                e.inserer();
                response.sendRedirect("evenement?mode=r");
            }
        }catch(Exception e){
            out.println(e.getMessage());
        }
    }
    
}
