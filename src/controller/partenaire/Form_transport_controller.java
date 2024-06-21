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


import model.partenaire.obj.Categorie_transport;
import model.partenaire.obj.Transport;

public class Form_transport_controller {
	
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
            String id=request.getParameter("id_transport");
            Transport transport=new Transport();
            transport=transport.select(id);
            Class c=transport.getClass();
            Field[] f=c.getDeclaredFields();
	        for (int i=0;i<f.length;++i ) {
	           String name=f[i].getName();
	           Method get=c.getDeclaredMethod("get"+name.substring(0,1).toUpperCase()+name.substring(1));
	           request.setAttribute(name,get.invoke(transport));
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

        	Categorie_transport categerie=new Categorie_transport();
        	List<Categorie_transport> categories=categerie.getAll();
        	request.setAttribute("liste_categorie",categories);

            mode(request,response);
        }
        catch (Exception e) {
     		request.setAttribute("error",e.getMessage());

        }
        finally{
            RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/formulaire_transport.jsp");
            dispat.forward(request, response);
        }

   }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
    }
	
}