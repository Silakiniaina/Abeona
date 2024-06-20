// package controller.admin;

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.sql.Date;
// import java.util.ArrayList;
// import java.util.Enumeration;

// import jakarta.servlet.RequestDispatcher;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import model.client.Evenement;
// import com.google.gson.*;

// public class RechercheServlet extends HttpServlet{
//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         ArrayList ls = null;
//         RequestDispatcher dispatcher = null;
//         PrintWriter out = response.getWriter();
//         String tab = request.getParameter("tab");
//         if(tab != null){
//             if(tab.equals("e")){
//                 String nom = request.getParameter("nom");
//                 String categorie = request.getParameter("categorie");
//                 String id_ville = request.getParameter("id_ville");
//                 Date date_debut =null;
//                 Date date_fin =null;
//                 if(!request.getParameter("date_debut").equals("null")) date_debut = Date.valueOf(request.getParameter("date_debut"));
//                 if(!request.getParameter("date_fin").equals("null")) date_fin = Date.valueOf(request.getParameter("date_fin"));
//                 try {
//                     ls = Evenement.rechercher_evenement(Evenement.EVENEMENT_ALL, nom, categorie, id_ville, date_debut, date_fin);
//                     request.setAttribute("listeEvenement", ls);
//                     dispatcher = request.getRequestDispatcher("evenement?mode=r");
//                 } catch (Exception e) {
//                     out.println(e.getMessage());
//                 }
//             }
//             dispatcher.forward(request, response);
//         }else{
//             out.println("tab null");
//         }
//     }
//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         doPost(request, response);
//     }
// }
