<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.partenaire.obj.Categorie_transport,model.partenaire.obj.Partenaire"%>
<%
    HttpSession session = request.getSession();
    if (session.getAttribute("partenaire")==null) {
        reponse.sendRedirect("");
    }
    List<Categorie_transport> categories=List<Categorie_transport>request.getAttribute("liste_categorie");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion transport</title>
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
            <h1>Insertion <span class="yellow">Transport</span></h1>
            <p class="desc_inscr">
            </p>
            <form action="form_transport" method="post" id="form_transport">
                 <input type="hidden" name="mode" value="<%=(String)request.getAttribute("mode")%>">
                <input type="hidden" name="id_transport" value="<% 
                     if (request.getAttribute("id_transport")!=null) {
                        out.print((String)request.getAttribute("id_transport"));
                     }
                     else{
                        out.print("0");
                     }
                %>">
                <div class="input-section">
                    <input name="nom_transport" id="nom_transport" class="input nom_transport" type="text" value="<% 
                     if (request.getAttribute("nom_transport")!=null) {
                        out.print(request.getAttribute("nom_transport"));
                     }%>"/>
                    <label for="nom_transport" class="label-input">Nom</label>
                </div>

                <div class="input-section">
                    <select name="id_categorie_transport" id="id_categorie_transport" class="options">
                        <% if (request.getAttribute("id_categorie_transport")!=null) { 
                                String id=(String)request.getAttribute("id_categorie_transport");
                                Categorie_transport categorie=new Categorie_transport();
                                categorie=categorie.getById(id);

                        %>
                            <option value="<%=id%>" ><%=categorie.getLibelle()%></option>
                        <% } %>
                        <% for (Categorie_transport categorie : categories) { %>
                            <option value="<%=categorie.getId_categorie_transport()%>"><%=categorie.getLibelle()%></option>
                        <% } %>
                    </select>
                     <label for="id_categorie_transport" class="label-options">Categorie</label>
                </div>

                <div class="input-section">
                    <input type="number" id="tarif" name="tarif" min="10" class="input tarif" value="<% 
                     if (request.getAttribute("tarif")!=null) {
                        out.print(request.getAttribute("tarif"));
                     }%>">
                    <label for="tarif" class="label-input">Tarif par jour</label>
                </div>
                <div class="input-section">
                    <textarea name="description" id="description" class="input textarea description"  value="<% 
                     if (request.getAttribute("description")!=null) {
                        out.print(request.getAttribute("description"));
                     }%>"></textarea>
                    <label for="description" class="label-input">Description</label>
                </div>
                <button class="btn abeona-custom_3">Insérer<img src="assets/Icons/icons8-arrow-50.png" alt=""></button>
            </form>
        </div>
        </div>
        </div>
    </div>
</body>
</html>