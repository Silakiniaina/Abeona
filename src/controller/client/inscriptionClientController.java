package controller.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.client.Genre;
import model.client.Utilisateur;

public class inscriptionClientController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            ArrayList<Genre> genres = Genre.get_liste_genre();
            request.setAttribute("listeGenre",genres);
       } catch (Exception e) {
        out.print(e.getMessage());
        e.printStackTrace();
       }
       finally{
        request.getRequestDispatcher("inscriptionClient.jsp").forward(request, response);  
       }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter out=response.getWriter();
        try{ String nom= request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        Date date_naissance= Date.valueOf(request.getParameter("dtn"));
        int id_genre = Integer.parseInt(request.getParameter("genre"));
        String mdp = request.getParameter("password");
        Utilisateur newuser = new Utilisateur(nom, prenom, email, date_naissance, id_genre);
        newuser.set_mot_de_passe(mdp);
        newuser.inscrire();
        HttpSession session = request.getSession();
        session.setAttribute("userConnected", newuser);
        request.getRequestDispatcher("acceuilClient.jsp").forward(request, response);
    }catch(Exception e){
        out.print(e.getMessage());
        e.printStackTrace();
    }
}
}
