<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.partenaire.obj.Partenaire, model.partenaire.obj.Categorie_hotel, model.partenaire.obj.Ville "%>
<%
    HttpSession session = request.getSession();
    if (session.getAttribute("partenaire")==null) {
        reponse.sendRedirect("");
    } 
    Partenaire partenaire=(Partenaire)session.getAttribute("partenaire");
    String id_partenaire=partenaire.getId_partenaire();

    List<Ville> villes=List<Ville>request.getAttribute("liste_ville");
    List<Categorie_hotel> categories=List<Categorie_hotel>request.getAttribute("liste_categorie");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>INSERTION Hôtel</title>
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
            <h1>Insertion <span class="yellow">Hôtel</span></h1>
            <p class="desc_inscr">
                
            </p>
            <form action="form_hotel" method="post" id="form_hotel">
                 <input type="hidden" name="mode" value="<%=(String)request.getAttribute("mode")%>">
                <input type="hidden" name="id_hotel" value="<% 
                     if (request.getAttribute("id_hotel")!=null) {
                        out.print((String)request.getAttribute("id_hotel"));
                     }
                     else{
                        out.print("0");
                     }
                %>">
                <div class="input-section">
                    <input name="nom_hotel" id="nom_hotel" class="input nom_hotel" type="text" value="<% 
                     if (request.getAttribute("nom_hotel")!=null) {
                        out.print(request.getAttribute("nom_hotel"));
                     }%>"/>
                    <label for="nom_hotel" class="label-input">Nom</label>
                </div>

                <div class="input-section">
                    <select name="id_categorie_hotel" id="id_categorie_hotel" class="options">
                        <% if (request.getAttribute("id_categorie_hotel")!=null) { 
                                String id=(String)request.getAttribute("id_categorie_hotel");
                                Categorie_hotel categorie=new Categorie_hotel();
                                categorie=categorie.getById(id);

                        %>
                            <option value="<%=id%>" ><%=categorie.getLibelle()%></option>
                        <% } %>
                        <% for (Categorie_hotel categorie : categories) { %>
                            <option value="<%=categorie.getId_categorie_hotel()%>"><%=categorie.getLibelle()%></option>
                        <% } %>
                    </select>
                     <label for="id_categorie_hotel" class="label-options">Categorie</label>
                </div>

                <div class="input-section">
                    <% if (request.getAttribute("id_ville")!=null) { 
                                String id=(String)request.getAttribute("id_ville");
                                Ville ville=new Ville();
                                ville=ville.getById(id);

                        %>
                            <option value="<%=id%>" ><%=ville.getNom_ville()%></option>
                        <% } %>
                        <% for (Ville ville : villes) { %>
                            <option value="<%=ville.getId_ville()%>"><%=ville.getNom_ville()%></option>
                        <% } %>
                    <label for="ville" class="label-options">Ville</label>
                </div>

                <div class="input-section">
                    <input name="adresse" id="adresse" class="input adresse" type="text" value="<% 
                     if (request.getAttribute("adresse")!=null) {
                        out.print(request.getAttribute("adresse"));
                     }%>"/>
                    <label for="adresse" class="label-input">Nom</label>
                </div>

                <div class="input-section">
                    <input name="evaluation" id="evaluation" class="input evaluation" type="number" value="<% 
                     if (request.getAttribute("evaluation")!=null) {
                        out.print(request.getAttribute("evaluation"));
                     }%>"/>
                    <label for="evaluation" class="label-input">Nom</label>
                </div>

                <p class="description">
                   
                </p>

                <div class="input-section">
                    <textarea name="description" id="description" class="input textarea description"  value="<% 
                     if (request.getAttribute("description")!=null) {
                        out.print(request.getAttribute("description"));
                     }%>"></textarea>
                    <label for="description" class="label-input">Description</label>
                </div>
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