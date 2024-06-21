package controller.partenaire;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.partenaire.obj.Chambre;


public class Menu_chambre_controller {
	
   protected Chambre getObject(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException, Exception {
     	try{
            String id_chambre="0";
            if (request.getParameter("id_chambre")!=null) {
                id_chambre=request.getParameter("id_chambre");
            }
            String id_hotel= request.getParameter("id_hotel");
            String categorie = request.getParameter("id_categorie_chambre");
            String status = request.getParameter("status");
            int capacite=Integer.parseInt(request.getParameter("capacite"));
            Double prix=Double.parseDouble(request.getParameter("prix"));
            long millis = System.currentTimeMillis();
            Date insertDate = new Date(millis);
            Chambre chambre=new Chambre(id_chambre,id_hotel,categorie,capacite,prix,status,insertDate);
         	return chambre;
        }
        catch (Exception e) {
        	throw e;
      	}
   }
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        try{

            Chambre chambre=new Chambre();
          	if (request.getParameter("mode").equalsIgnoreCase("supprimer")==true) {
            	String id=request.getParameter("id_chambre");
            	chambre.delete(id);
          	}
          	else if (request.getParameter("mode").equalsIgnoreCase("info")==true) {
          		String id=request.getParameter("id_chambre");
          		chambre.getById(id);
          		request.setAttribute("chambre",chambre);
          		RequestDispatcher dispat = request.getRequestDispatcher("");
            	dispat.forward(request, response);
          	}
        }
        catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	out.println(e.getMessage());
      	}
      	finally{
            String id_hotel=(String)request.getParameter("id_hotel");
            request.setAttribute("id_hotel",id_hotel);
            request.setAttribute("mode","info");
         	RequestDispatcher dispat = request.getRequestDispatcher("menu_hotel");
            dispat.forward(request, response);
      	}
   }
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
		try{
            Chambre chambre=getObject(request,response);
            if (request.getParameter("mode").equalsIgnoreCase("modifier")==true) {
	            chambre.update();
            }
            else{
                int nombres=Integer.parseInt(request.getParameter("nombres"));
                for (int i=0;i<nombres;++i ) {
                    chambre.insert();
                }
            }
         
      	} 
      	catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	out.println(e.getMessage());
      	}
      	finally{
            String id_hotel=(String)request.getParameter("id_hotel");
            request.setAttribute("id_hotel",id_hotel);
            request.setAttribute("mode","info");
         	RequestDispatcher dispat = request.getRequestDispatcher("menu_hotel");
            dispat.forward(request, response);
      	}
   }
	
	
}