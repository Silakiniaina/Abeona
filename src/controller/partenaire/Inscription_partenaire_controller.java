package controller.partenaire;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.partenaire.obj.Partenaire;
public class Inscription_partenaire_controller  extends HttpServlet {
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        try{ 
            String nom= request.getParameter("partenaire");
            String email = request.getParameter("email");
            String mdp = request.getParameter("mdp");
            long millis = System.currentTimeMillis();
            Date insertDate = new Date(millis);
            Partenaire newuser=new Partenaire("0",email,nom,mdp,insertDate);
            newuser.insert();
            response.sendRedirect("/web/jsp/partenaire/pages_success_register_partenaires.jsp");
        }catch(Exception e){
            out.print(e.getMessage());
            e.printStackTrace();
        }
    }
	
}