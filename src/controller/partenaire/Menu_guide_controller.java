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

import model.partenaire.obj.Guide;

public class Menu_guide_controller {

	protected void show(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     	try{
     		HttpSession session=request.getSession();
        	Partenaire partenaire=session.getAttribute("partenaire");
            String id_partenaire=partenaire.getId_partenaire();

     		Guide guide=new Guide();
     		List<Guide> guides=guide.getByIdPartenaire(id_partenaire);
     		request.setAttribute("liste_guide",guide);

     	}
     	catch (Exception e) {
     		request.setAttribute("error",e.getMessage());

     	}
   	}

   	protected Guide getObject(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException, Exception {
     	try{
            String id_guide="0";
            if (request.getParameter("id_guide")!=null) {
                id_guide=request.getParameter("id_guide");
            }
            String nom= request.getParameter("nom_guide");
            String desc = request.getParameter("description");
            String dispo="libre";
            long millis = System.currentTimeMillis();
            Date insertDate = new Date(millis);
            HttpSession session = request.getSession();
            Partenaire current_partenaire = (Partenaire) session.getAttribute("partenaire");
            if (current_partenaire==null) {
                response.sendRedirect("");
            }
            Guide new_Guide=new Guide(id_guide,nom,desc,dispo,insertDate,current_partenaire.getId_partenaire());
            return new_Guide;
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

            Guide guide=new Guide();
          	if (request.getParameter("mode").equalsIgnoreCase("supprimer")==true) {
            	String id=request.getParameter("id_guide");
            	guide.delete(id);
          	}
          	else if (request.getParameter("mode").equalsIgnoreCase("info")==true) {
          		String id=request.getParameter("id_guide");
          		guide.getById(id);
          		request.setAttribute("guide",guide);
          		RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/description_guide.jsp");
            	dispat.forward(request, response);
          	}
          	show(request,response);
        }
        catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	out.println(e.getMessage());
      	}
      	finally{
         	RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/info_guide.jsp");
            dispat.forward(request, response);
      	}
   }
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
		try{
            Guide guide=getObject(request,response);
            if (request.getParameter("mode").equalsIgnoreCase("modifier")==true) {
	            guide.update();
            }
            else{
                guide.insert();
            }
         
      	} 
      	catch (Exception e) {
        	request.setAttribute("error",e.getMessage());
        	out.println(e.getMessage());
      	}
      	finally{
            show(request,response);
         	RequestDispatcher dispat = request.getRequestDispatcher("/web/jsp/partenaire/info_guide.jsp");
            dispat.forward(request, response);
      	}
   }
	
	
}