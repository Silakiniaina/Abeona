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

import model.partenaire.obj.Evenement;


public class Menu_evenement_controller {
	
   protected Evenement getObject(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException, Exception {
     	try{
            String id_evenement="0";
            if (request.getParameter("id_evenement")!=null) {
                id_evenement=request.getParameter("id_evenement");
            }
            String titre=request.getParameter("titre_evenement");
            String desc=request.getParameter("description");
            String lieu=request.getParameter("lieu_evenement");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate_debut = format.parse(request.getParameter("date_debut_evenement"));
            Date sqlDate_debut = new Date(utilDate_debut.getTime());
            Date utilDate_fin = format.parse(request.getParameter("date_fin_evenement"));
            Date sqlDate_fin = new Date(utilDate_fin.getTime());
            String id_hotel=request.getParameter("id_hotel");
            String id_ville=request.getParameter("id_ville");
            String categorie=request.getParameter("categorie");
            long millis = System.currentTimeMillis();
            Date insertDate = new Date(millis);
            Evenement p=new Evenement(id_evenement,titre,desc,lieu,sqlDate_debut,sqlDate_fin,id_hotel,id_ville,categorie,insertDate);
         	return p;
        }
        catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	throw e;
      	}
   }
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        try{

            Evenement evenement=new Evenement();
          	if (request.getParameter("mode").equalsIgnoreCase("supprimer")==true) {
            	String id=request.getParameter("id_evenement");
            	evenement.delete(id);
          	}
          	else if (request.getParameter("mode").equalsIgnoreCase("info")==true) {
          		String id=request.getParameter("id_evenement");
          		evenement.getById(id);
          		request.setAttribute("evenement",evenement);
          		RequestDispatcher dispat = request.getRequestDispatcher("formCueillette.jsp");
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
            Evenement evenement=getObject(request,response);
            if (request.getParameter("mode").equalsIgnoreCase("modifier")==true) {
	            evenement.update();
            }
            else{
                evenement.insert();
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