package controller.client;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.client.Utilisateur;

public class loginClientController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter out = new PrintWriter(response.getWriter());
        try {
            String mail = request.getParameter("email");
            String password = request.getParameter("password");
            Utilisateur user=Utilisateur.login(mail, password);
            if (user != null) {
               HttpSession session = request.getSession();
               session.setAttribute("userConnected", user);
               request.getRequestDispatcher("acceuilClient.jsp").forward(request, response);

            } else {
                String message = "Veuillez verifier les informations que vous avez fournies. Votre mot de passe est incorrect ou votre email est incorrect";
                request.setAttribute("message", message);
                request.getRequestDispatcher("loginClient.jsp").forward(request, response);
            }
        } catch (Exception e) {
            out.print(e.getMessage());
            e.printStackTrace();
        }
    }

}