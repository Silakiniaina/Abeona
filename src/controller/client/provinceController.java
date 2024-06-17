package controller.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.client.PointInteret;
import model.client.Province;

public class provinceController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            ArrayList<Province> provinces = Province.get_liste_provinces();
            request.setAttribute("listeProvince", provinces);
        } catch (Exception e) {
            out.print(e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("provinceClient.jsp").forward(request, response);  
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String id_province = request.getParameter("provinceId");
            Province province = Province.get_province_par_id(null, id_province);
            ArrayList<PointInteret> interets = province.get_point_interets();
            request.setAttribute("listeInteret", interets);
        } catch (Exception e) {
            out.print(e.getMessage());
            e.printStackTrace();
        } finally {
            doGet(request, response);
        }
    }
}
