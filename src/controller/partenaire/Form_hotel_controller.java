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


import model.partenaire.obj.Categorie_hotel;
import model.partenaire.obj.Hotels;
import model.partenaire.obj.Ville;

public class Form_hotel_controller {
	
	protected void mode(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     	if (request.getParameter("mode").equalsIgnoreCase("modifier")==true) {
           update(request, response);
        }
        else
        {
           add(request, response);
        }  
     	
    }
	protected void add(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     	try{
            request.setAttribute("mode","ajouter");

     	}
     	catch (Exception e) {
     		throw e;

     	}
     	
    }
   protected void update(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
        try{
            String id=request.getParameter("id_hotel");
            Hotels hotel=new Hotels();
            hotel=hotel.select(id);
            Class c=hotel.getClass();
            Field[] f=c.getDeclaredFields();
	        for (int i=0;i<f.length;++i ) {
	           String name=f[i].getName();
	           Method get=c.getDeclaredMethod("get"+name.substring(0,1).toUpperCase()+name.substring(1));
	           request.setAttribute(name,get.invoke(hotel));
	        }
         	request.setAttribute("mode","modifier");
        }
        catch (Exception e) {
           throw e;

        }
      
   }
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        	HttpSession session=request.getSession();
        	Partenaire partenaire=session.getAttribute("partenaire");
            String id_partenaire=partenaire.getId_partenaire();

        	Ville ville=new Ville();
        	List<Ville> villes=ville.getByIdPartenaire(id_partenaire);
        	request.setAttribute("liste_ville",villes);

        	Categorie_hotel categerie=new Categorie_hotel();
        	List<Categorie_hotel> categories=categerie.getAll();
        	request.setAttribute("liste_categorie",categories);

            mode(request,response);
        }
        catch (Exception e) {
     		request.setAttribute("error",e.getMessage());

        }
        finally{
            RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/formulaire_hotel.jsp");
            dispat.forward(request, response);
        }

   }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
    }
	
}