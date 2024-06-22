<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.partenaire.obj.Partenaire, model.partenaire.obj.Categorie_hotel, model.partenaire.obj.Hotels, model.partenaire.obj.Hotels "%>
<%
    HttpSession session = request.getSession();
    if (session.getAttribute("partenaire")==null) {
        reponse.sendRedirect("");
    } 
    Partenaire partenaire=(Partenaire)session.getAttribute("partenaire");
    String id_partenaire=partenaire.getId_partenaire();

    List<Hotels> hotels=List<Hotels>request.getAttribute("liste_hotels");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Information hôtel</title>
    <link rel="stylesheet" href="assets/css/styles.css" />
    <script src="assets/js/toggle.js" defer></script>
    <script src="assets/js/input.js" defer></script>
    <script src="assets/js/rating.js" defer></script>
    <script src="assets/js/pagination.js" defer></script>
  </head>
  <body>
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
    <div class="section-1 row">
      <section class="section-text">
        <h1>Vous pouvez gerez vos informations ici.</h1>
      </section>
      <div class="button-group row">
        <a class="btn abeona-custom_4 active">Hotel</a>
        <a class="btn abeona-custom_4">Transport</a>
        <a class="btn abeona-custom_4">Guide</a>
      </div>
    </div>
    <div class="section-2 row">
      <div class="section-search row">
        <div class="section-result row">
          <div class="left">
            <h2>Liste des hotels</h2>
            <p><%=hotels.size()%> resultats</p>
          </div>
          <a class="btn abeona-custom_5"> Ajouter </a>
        </div>
        <%-- <div class="section-form row">
          <form action="">
            <div class="input-section">
              <input type="text" id="nom" name="nom" class="input" />
              <label for="nom" class="label-input">Nom</label>
            </div>

            <div class="input-section">
              <select name="categorie" id="categorie" class="options">
                <option value="">Mahajanga</option>
                <option value="">Tananarive</option>
                <option value="">Antsiranana</option>
                <option value="">Finarantsoa</option>
              </select>
              <label for="categorie" class="label-options">Province</label>
            </div>

            <div class="input-section">
              <select name="categorie" id="categorie" class="options">
                <option value="">Hotelerie</option>
                <option value="">Transports</option>
                <option value="">Restaurants</option>
                <option value="">Location</option>
              </select>
              <label for="categorie" class="label-options">Categorie</label>
            </div>

            <div class="input-section">
              <input
                type="number"
                id="evaluation"
                name="evaluation"
                min="0"
                class="input"
              />
              <label for="evaluation" class="label-input">Evaluation</label>
            </div>

            <button class="btn abeona-custom_5 little">Rechercher</button>
          </form>         
        </div>--%>

      </div>
      <!-- Mettez la note dans le data-rates -->
      <div class="section-resultat row">
        <div class="resultat row">
          <% for (int i=0;i<hotels.size();++i) { 
            Hotel hotel=hotels.elementAt(i);
            Categorie_hotel categorie=new Categorie_hotel();
            categorie=categorie.getById(hotel.getId_categorie_hotel());
          %>
            
          <div class="container-hotel">
            <img src="assets/image_videos/login_2.png" alt="photo hôtel" />
            <div class="descr_hotel">
              <a class="nom" href="#">
                <span class="nom_inside"><%=hotel.getNom_hotel()%></span>
                <span class="categorie"><%=categorie.getLibelle()%></span>
              </a>
              <a href="form_hotel?mode=modifier&id_hotel=<%=hotel.getId_hotel()%>">Modifier</a>
              <a href="menu_hotel?mode=supprimer&id_hotel=<%=hotel.getId_hotel()%>">Supprimer</a>
              <p class="rating" data-rates="<%hotel.getEvaluation()%>"></p>
            </div>
          </div>

          <% } %>
          
        </div>
        <%-- <div class="pagination">
          <ul>
            <li>1</li>
            <li>2</li>
            <li>...</li>
            <li>8</li>
          </ul>
        </div> --%>
      </div>
    </div>
  </body>
  <footer>
    <div class="info row">
      <div class="parametre">
        <div class="input-section">
          <select name="categorie" id="categorie" class="options">
            <option value="">Français</option>
            <option value="">Malgache</option>
            <option value="">Anglais</option>
          </select>
          <label for="categorie" class="label-options">Langues</label>
        </div>

        <div class="input-section">
          <select name="categorie" id="categorie" class="options">
            <option value="">FR</option>
            <option value="">MGA</option>
            <option value="">US</option>
          </select>
          <label for="categorie" class="label-options">Devises</label>
        </div>

        <a class="btn"> Details </a>
      </div>
      <div class="contacts">
        <div class="info-container">
          <h2 class="titre">Compagnies</h2>
          <ul>
            <li><a href="">Accueil</a></li>
            <li><a href="">Partenaires</a></li>
            <li><a href="">A propos</a></li>
          </ul>
        </div>
        <div class="info-container">
          <h2 class="titre">Services</h2>
          <ul>
            <li><a href="">Proposition pack</a></li>
            <li><a href="">Recherche de destination</a></li>
            <li><a href="">Evaluation voyage</a></li>
          </ul>
        </div>
        <div class="info-container">
          <h2 class="titre">Aides</h2>
          <ul>
            <li><a href="">Contactez nous</a></li>
            <li><a href="">Termes et conditions d'utilisation</a></li>
            <li><a href="">A propos</a></li>
          </ul>
        </div>
        <div class="info-container">
          <h2 class="titre">Methode de paiement</h2>
          <!-- <ul>
                    <li>Accueil</li>
                    <li>Partenaires</li>
                    <li>A propos</li>
                </ul> -->
        </div>
      </div>
    </div>
    <p class="copyright">Copyright 2024 Abeona. All rights reserved</p>
  </footer>
</html>