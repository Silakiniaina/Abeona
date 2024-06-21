<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.partenaire.obj.Chambre, model.partenaire.obj.Categorie_chambre, model.partenaire.obj.Hotels "%>
<% 
    HttpSession session = request.getSession();
    if (session.getAttribute("partenaire")==null) {
        reponse.sendRedirect("");
    }
    List<Hotels> hotels=List<Hotels>request.getAttribute("liste_hotels");
    List<Categorie_chambre> categories=List<Categorie_chambre>request.getAttribute("liste_categorie");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion Chambre</title>
    <link rel="stylesheet" href="assets/css/styles.css">
    <script src="assets/js/input.js" defer></script>
    <script src="assets/js/toggle.js" defer></script>
    <script src="assets/js/form_controll.js" defer></script>
</head>
<body>
    <!-- Veuillez changer le for='...' des labels correspondants et l'id des inputs si vous changez leurs name MERCI -->
    <!-- id == name -->
    <header>
         <nav class="navbar">
        <a href="" class="logo">
            LOGO
        </a>
        <div class="nav-links">
                <ul class="list-container">
                    <li class="active">
                        <a href="province">
                            Accueil
                        </a>
                    </li>
                    <li><a href="menu_hotel?mode=afficher">Gestion des Hôtels</a></li>
                    <li><a href="menu_transport?mode=afficher">Gestion des Transports</a></li>
                    <li><a href="menu_guidel?mode=afficher">Gestion des Guides</a></li>
                    <li><a href="">A propos</a></li>
                    <li><a href="">Aide</a></li>
                </ul>
                <ul class="list-container">
                  <li class="container-profil">
                    <div class="profil-partenaire">
                      <img
                        src="assets/image_videos/login_2.png"
                        alt="profil partenaire"
                      />
                      <div class="status"></div>
                    </div>
                    <div class="descr-partenaire">
                      <h3><%=partenaire.getNom_partenaire()%></h3>
                      <p><%=partenaire.getEmail_partenaire()%></p>
                    </div>
                  </li>
                </ul>
        </div>
        <img src="assets/Icons/icons8-menu-64.png" alt="menu-icons" class="menu-hamburger">
    </nav>
    </header>
    <div class="row">
        <div class="first_container">
             <div class="container_forms">
            <h1>Insertion <span class="yellow">Chambre</span></h1>
            <p class="desc_inscr">
            </p>
            <form action="menu_chambre" method="post" id="form_chambre">

                <input type="hidden" name="mode" value="<%=(String)request.getAttribute("mode")%>">
                <input type="hidden" name="id_chambre" value="<% 
                     if (request.getAttribute("id_chambre")!=null) {
                        out.print((String)request.getAttribute("id_chambre"));
                     }
                     else{
                        out.print("0");
                     }
                %>">
                <div class="input-section">
                    <select name="id_hotel" id="hotel" class="options">
                        <% if (request.getAttribute("id_hotel")!=null) { 
                                String id=(String)request.getAttribute("id_hotel");
                                Hotels hotel=new Hotels();
                                hotel=hotel.getById(id);

                        %>
                            <option value="<%=id%>" ><%=hotel.getNom_hotel()%></option>
                        <% } %>
                        <% for (Hotels hotel : hotels) { %>
                            <option value="<%=hotel.getId_hotel()%>"><%=hotel.getNom_hotel()%></option>
                        <% } %>
                    </select>
                    <label for="hotel" class="label-options">Hôtel</label>
                </div>

                <div class="input-section">
                    <select name="id_categorie_chambre" id="hotel" class="options">
                        <% if (request.getAttribute("id_categorie_chambre")!=null) { 
                                String id=(String)request.getAttribute("id_categorie_chambre");
                                Categorie_chambre categorie=new Categorie_chambre();
                                categorie=categorie.getById(id);

                        %>
                            <option value="<%=id%>" ><%=categorie.getLibelle()%></option>
                        <% } %>
                        <% for (Categorie_chambre categorie : categories) { %>
                            <option value="<%=categorie.getId_categorie_chambre()%>"><%=hotel.getLibelle()%></option>
                        <% } %>
                    </select>
                     <label for="categorie" class="label-options">Categorie</label>
                </div>

                <div class="input-section">
                    <input type="number" id="capacite" name="capacite" min="1" class="input capacite" value="<% 
                     if (request.getAttribute("capacite")!=null) {
                        out.print(request.getAttribute("capacite"));
                     }%>">
                     <label for="capacite" class="label-input">Capacité</label>
                </div>

                <div class="input-section">
                    <input type="number" id="prix" name="prix" min="1" class="input prix" value="<% 
                     if (request.getAttribute("prix")!=null) {
                        out.print(request.getAttribute("prix"));
                     }%>">
                     <label for="prix" class="label-input">Prix</label>
                </div>

                <div class="input-section">
                    <input type="number" id="status" name="status" min="1" class="input status" value="<% 
                     if (request.getAttribute("status")!=null) {
                        out.print(request.getAttribute("status"));
                     }%>">
                     <label for="status" class="label-input">Status</label>
                </div>
                <% if (request.getAttribute("mode").equalsIgnoreCase("afficher")==true) {%>
                <div class="input-section">
                    <input type="number" id="nombres" name="nombres" min="0" class="input nombres">
                     <label for="nombres" class="label-input">Nombres</label>
                </div>
                <% } %>
                <p><% 
                     if (request.getAttribute("error")!=null) {
                        out.print(request.getAttribute("error"));
                     }%></p>
                <button class="btn abeona-custom_3">Insérer<img src="assets/Icons/icons8-arrow-50.png" alt=""></button>
            </form>
        </div>
        </div>
        </div>
    </div>
</body>
</html>