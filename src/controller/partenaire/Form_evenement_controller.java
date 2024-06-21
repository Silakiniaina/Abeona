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

import model.partenaire.obj.Evenement;
import model.partenaire.obj.Categorie_evenement;
import model.partenaire.obj.Hotels;
import model.partenaire.obj.Ville;

public class Form_evenement_controller {
	
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
            String id=request.getParameter("id_evenement");
            Evenement evenement=new Evenement();
            evenement=evenement.select(id);
            Class c=evenement.getClass();
            Field[] f=c.getDeclaredFields();
	        for (int i=0;i<f.length;++i ) {
	           String name=f[i].getName();
	           Method get=c.getDeclaredMethod("get"+name.substring(0,1).toUpperCase()+name.substring(1));
	           request.setAttribute(name,get.invoke(evenement));
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

        	Hotels hotel=new Hotels();
        	List<Hotels> hotels=hotel.getByIdPartenaire(id_partenaire);
        	request.setAttribute("liste_hotels",hotels);

        	Categorie_evenement categerie=new Categorie_evenement();
        	List<Categorie_evenement> categories=categerie.getAll();
        	request.setAttribute("liste_categorie",categories);

        	Ville categerie=new Ville();
        	List<Ville> categories=categerie.getAll();
        	request.setAttribute("liste_ville",categories);

            mode(request,response);
        }
        catch (Exception e) {
     		request.setAttribute("error",e.getMessage());

        }
        finally{
            RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/formulaire_evenement.jsp");
            dispat.forward(request, response);
        }

   }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
    }
	
}