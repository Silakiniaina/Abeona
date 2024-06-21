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

import model.partenaire.obj.Transport;

public class Menu_transport_controller {

	protected void show(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     	try{
     		HttpSession session=request.getSession();
        	Partenaire partenaire=session.getAttribute("partenaire");
            String id_partenaire=partenaire.getId_partenaire();

     		Transport transport=new Transport();
     		List<transport> transports=transport.getByIdPartenaire(id_partenaire);
     		request.setAttribute("liste_transport",transport);

     	}
     	catch (Exception e) {
     		request.setAttribute("error",e.getMessage());

     	}
   	}

   	protected Transport getObject(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException, Exception {
     	try{
            String id_transport="0";
            if (request.getParameter("id_transport")!=null) {
                id_transport=request.getParameter("id_transport");
            }
             String nom= request.getParameter("nom_transport");
            String categorie = request.getParameter("id_categorie_transport");
            String description = request.getParameter("description");
            Double prix=Double.parseDouble(request.getParameter("tarif"));
            long millis = System.currentTimeMillis();
            Date insertDate = new Date(millis);
            HttpSession session = request.getSession();
            Partenaire current_partenaire = (Partenaire)session.getAttribute("partenaire");
            if (current_partenaire==null) {
                response.sendRedirect("");
            }
            Transport new_transport=new Transport(id_transport,current_partenaire.getId_partenaire(),nom,categorie,prix,insertDate);
         	return new_transport;
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

            Transport transport=new Transport();
          	if (request.getParameter("mode").equalsIgnoreCase("supprimer")==true) {
            	String id=request.getParameter("id_transport");
            	transport.delete(id);
          	}
          	else if (request.getParameter("mode").equalsIgnoreCase("info")==true) {
          		String id=request.getParameter("id_transport");
          		transport.getById(id);
          		request.setAttribute("transport",transport);
          		RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/description_transport.jsp");
            	dispat.forward(request, response);
          	}
          	show(request,response);
        }
        catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	out.println(e.getMessage());
      	}
      	finally{
         	RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/info_transport.jsp");
            dispat.forward(request, response);
      	}
   }
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
		try{
            Transport transport=getObject(request,response);
            if (request.getParameter("mode").equalsIgnoreCase("modifier")==true) {
	            transport.update();
            }
            else{
                transport.insert();
            }
         
      	} 
      	catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	out.println(e.getMessage());
      	}
      	finally{
         	RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/info_transport.jsp");
            dispat.forward(request, response);
      	}
   }
	
	
}