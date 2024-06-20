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
        try {
            HashMap<String,Integer> nombreEvenement = Evenement.get_nombre_evenement();
            ArrayList<Evenement> listeEvenement = null;
            ArrayList<CategorieEvenement> categorieEvenement = CategorieEvenement.get_liste_categorie_evenement();
            request.setAttribute("nombreEvenement", nombreEvenement);
            request.setAttribute("categories", categorieEvenement);
            if(mode.equals("r")){
                listeEvenement = Evenement.get_liste_evenement_calendrier();
            }else if(mode.equals("s")){
                String nom = request.getParameter("nom");
                String categorie = request.getParameter("categorie");
                Date date_debut = Date.valueOf(request.getParameter("date_debut"));
                Date date_fin = Date.valueOf(request.getParameter("date_fin"));
                listeEvenement = Evenement.rechercher_evenement(Evenement.EVENEMENT_ALL, nom, categorie, date_debut, date_fin);
            }
            request.setAttribute("listeEvenement", listeEvenement);
            request.setAttribute("page", "evenement");
        }catch (Exception e) {
                
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
                Date date_debut = Date.valueOf(request.getParameter("date_debut"));
                Date date_fin = Date.valueOf(request.getParameter("date_fin"));
                String description = request.getParameter("desc");
                Evenement e = new Evenement(titre, description, date_debut, date_fin, null, categorie);
                e.inserer();
                response.sendRedirect("evenement?mode=r");
            }
        }catch(Exception e){
            out.println(e.getMessage());
        }
    }
    
}
