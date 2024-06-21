<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.partenaire.obj.Partenaire"%>
<%
    HttpSession session = request.getSession();
    if (session.getAttribute("partenaire")==null) {
        reponse.sendRedirect("");
    } 
    Partenaire partenaire=(Partenaire)session.getAttribute("partenaire");
    String id_partenaire=partenaire.getId_partenaire();
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
            <h1>Insertion <span class="yellow">Guide</span></h1>
            <p class="desc_inscr">
            </p>
            <form action="form_guide" methode="post" id="form_guide">
                <input type="hidden" name="mode" value="<%=(String)request.getAttribute("mode")%>">
                <input type="hidden" name="disponiblite" value="libre">
                <input type="hidden" name="id_guide" value="<% 
                     if (request.getAttribute("id_guide")!=null) {
                        out.print((String)request.getAttribute("id_guide"));
                     }
                     else{
                        out.print("0");
                     }
                %>">
                <div class="input-section">
                    <input name="nom_guide" id="nom_guide" class="input nom_guide" type="text" value="<% 
                     if (request.getAttribute("nom_guide")!=null) {
                        out.print(request.getAttribute("nom_guide"));
                     }%>"/>
                    <label for="nom_guide" class="label-input">Nom</label>
                </div>

                <p class="description">
                   
                </p>

                <div class="input-section" style="margin-bottom: 150px;">
                    <textarea name="description" rows="10" id="description" class="input textarea description" value="<% 
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