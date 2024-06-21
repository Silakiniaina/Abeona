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

import model.partenaire.obj.Hotels;
import model.partenaire.obj.Chambre;
import model.partenaire.obj.Evenement;


public class Menu_hotel_controller {

	protected void show(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     	try{
     		HttpSession session=request.getSession();
        	Partenaire partenaire=session.getAttribute("partenaire");
            String id_partenaire=partenaire.getId_partenaire();

     		Hotels hotel=new Hotels();
     		List<Hotels> hotels=hotel.getByIdPartenaire(id_partenaire);
     		request.setAttribute("liste_hotel",hotel);

     	}
     	catch (Exception e) {
     		request.setAttribute("error",e.getMessage());

     	}
   	}

   	protected Hotels getObject(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException, Exception {
     	try{
            String id_hotel="0";
            if (request.getParameter("id_hotel")!=null) {
                id_hotel=request.getParameter("id_hotel");
            }
            String nom= request.getParameter("nom_hotel");
            String categorie = request.getParameter("id_categorie_hotel");
            String ville = request.getParameter("id_ville");
            String adresse = request.getParameter("adresse");
            String description = request.getParameter("description");
            Double evaluation=Double.parseDouble(request.getParameter("evaluation"));
            long millis = System.currentTimeMillis();
            Date insertDate = new Date(millis);
            HttpSession session = request.getSession();
            Partenaire current_partenaire = (Partenaire) session.getAttribute("partenaire");
            if (current_partenaire==null) {
                response.sendRedirect(""); 
            }
            Hotels new_Hotels=new Hotels(id_hotel,current_partenaire.getId_partenaire(),nom,description,categorie,evaluation,ville,adresse,insertDate);
            return new_Hotels;
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

            Hotels hotel=new Hotels();
          	if (request.getParameter("mode").equalsIgnoreCase("supprimer")==true) {
            	String id=request.getParameter("id_hotel");
            	hotel.delete(id);
          	}
          	else if (request.getParameter("mode").equalsIgnoreCase("info")==true) {
          		String id=request.getParameter("id_hotel");
          		hotel.getById(id);
          		request.setAttribute("hotel",hotel);

          		Chambre chambre=new Chambre();
          		List<Chambre> chambres=chambre.getByIdHotel(id);
          		request.setAttribute("liste_chambre",chambres);

          		Evenement evenement=new Evenement();
          		List<Evenement> evenements=evenement.getByIdHotel(id);
          		request.setAttribute("liste_evenement",evenements);

          		RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/description_hotel.jsp");
            	dispat.forward(request, response);
          	}
          	show(request,response);
        }
        catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	out.println(e.getMessage());
      	}
      	finally{
         	RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/info_hotel.jsp");
            dispat.forward(request, response);
      	}
   }
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
		try{
            Hotels hotel=getObject(request,response);
            if (request.getParameter("mode").equalsIgnoreCase("modifier")==true) {
	            hotel.update();
            }
            else{
                hotel.insert();
            }
         
      	} 
      	catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	out.println(e.getMessage());
      	}
      	finally{
         	RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/info_hotel.jsp");
            dispat.forward(request, response);
      	}
   }
	
	
}