<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.partenaire.obj.Partenaire, model.partenaire.obj.Guide"%>
<%
    HttpSession session = request.getSession();
    if (session.getAttribute("partenaire")==null) {
        reponse.sendRedirect("");
    } 
    Partenaire partenaire=(Partenaire)session.getAttribute("partenaire");
    String id_partenaire=partenaire.getId_partenaire();

    Guide guide=(Guide)request.getAttribute("guide");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Description transport</title>
    <link rel="stylesheet" href="assets/css/styles.css">
    <script src="assets/js/toggle.js" defer></script>
    <script src="assets/js/input.js" defer></script>
    <script src="assets/js/evaluation.js" defer></script>
</head>
<body>
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
    <h1 class="header_h">Description Guide</h1>
    <section class="descr_1">
        <div class="image">
            <div class="img_guide">
                <img src="assets/image_videos/Tourisme national.png" alt="">
            </div>
              <form method="POST" enctype="multipart/form-data" action="upload">
                <input type="file" name="image" id="upload" accept="image/*">
                <button type="submit">Upload</button>
            </form>
        </div>

        <div class="script_descr">
            <div class="container_desc">
                <h2 class="nom">
                   <%=guide.getNom_guide()%>
                </h2>
                <p class="categorie">
                    Guide touristique
                </p>
            </div>
        </div>
    </section>
        <section class="descr_2 bio">
        <div class="row description">
            <h2>Biographie</h2>
            <div class="container_desc">
               <p class="paragraphe"> <%=guide.getBiographie()%></p>
            </div>
        </div>
    </section>
    <section class="descr_3">
        <h2>
            Avis Et Evaluation
        </h2>
        <div class="evaluation row">
            <p>
                <span class="note">4,30</span>
                <span class="num_eval" data-target="854">0</span>
                Reviews
            </p>
            <!-- étoiles du partenaire : data-target ; max étoiles : data-max -->
            <!-- Même logique pour les avis qui suivront -->
            <div class="stars" data-target="3" data-max="5">
            </div>
        </div>
        <div class="avis row">
            <div class="avis_perso">
                <div class="profil_container">
                    <div class="profil">
                        <img src="assets/image_videos/demitra kalogeras.jpg" alt="">
                    </div>
                    <div class="info_avis">
                        <div class="stars" data-target="4" data-max="5"></div>
                        <p class="nom">Demitra Kalogeras</p>
                        <p class="date">2 October 2012</p>
                    </div>
                </div>
                <div class="avis_inside">
                    <p class="avis_real">
                        Good Tour,Really Well Organised
                    </p>
                    <p class="descr_avis">
                        The tour was very well organised. One minus is that you get completely bombarded with information. You also have to stand up for too long at the private entrance to the Tower of London, which leads to a lack of time later. Lunch was the same, too stress, the quality was great but you couldn't enjoy it. I'd like to ask the organisers: please
                    </p>
                </div>
            </div>
            <div class="avis_perso">
                <div class="profil_container">
                    <div class="profil">
                        <img src="assets/image_videos/demitra kalogeras.jpg" alt="">
                    </div>
                    <div class="info_avis">
                        <div class="stars" data-target="4" data-max="5"></div>
                        <p class="nom">Demitra Kalogeras</p>
                        <p class="date">2 October 2012</p>
                    </div>
                </div>
                <div class="avis_inside">
                    <p class="avis_real">
                        Good Tour,Really Well Organised
                    </p>
                    <p class="descr_avis">
                        The tour was very well organised. One minus is that you get completely bombarded with information. You also have to stand up for too long at the private entrance to the Tower of London, which leads to a lack of time later. Lunch was the same, too stress, the quality was great but you couldn't enjoy it. I'd like to ask the organisers: please
                    </p>
                </div>
            </div>
            <div class="avis_perso">
                <div class="profil_container">
                    <div class="profil">
                        <img src="assets/image_videos/demitra kalogeras.jpg" alt="">
                    </div>
                    <div class="info_avis">
                        <div class="stars" data-target="4" data-max="5"></div>
                        <p class="nom">Demitra Kalogeras</p>
                        <p class="date">2 October 2012</p>
                    </div>
                </div>
                <div class="avis_inside">
                    <p class="avis_real">
                        Good Tour,Really Well Organised
                    </p>
                    <p class="descr_avis">
                        The tour was very well organised. One minus is that you get completely bombarded with information. You also have to stand up for too long at the private entrance to the Tower of London, which leads to a lack of time later. Lunch was the same, too stress, the quality was great but you couldn't enjoy it. I'd like to ask the organisers: please
                    </p>
                </div>
            </div>
            <div class="avis_perso">
                <div class="profil_container">
                    <div class="profil">
                        <img src="assets/image_videos/demitra kalogeras.jpg" alt="">
                    </div>
                    <div class="info_avis">
                        <div class="stars" data-target="4" data-max="5"></div>
                        <p class="nom">Demitra Kalogeras</p>
                        <p class="date">2 October 2012</p>
                    </div>
                </div>
                <div class="avis_inside">
                    <p class="avis_real">
                        Good Tour,Really Well Organised
                    </p>
                    <p class="descr_avis">
                        The tour was very well organised. One minus is that you get completely bombarded with information. You also have to stand up for too long at the private entrance to the Tower of London, which leads to a lack of time later. Lunch was the same, too stress, the quality was great but you couldn't enjoy it. I'd like to ask the organisers: please
                    </p>
                </div>
            </div>
            <div class="avis_perso">
                <div class="profil_container">
                    <div class="profil">
                        <img src="assets/image_videos/demitra kalogeras.jpg" alt="">
                    </div>
                    <div class="info_avis">
                        <div class="stars" data-target="4" data-max="5"></div>
                        <p class="nom">Demitra Kalogeras</p>
                        <p class="date">2 October 2012</p>
                    </div>
                </div>
                <div class="avis_inside">
                    <p class="avis_real">
                        Good Tour,Really Well Organised
                    </p>
                    <p class="descr_avis">
                        The tour was very well organised. One minus is that you get completely bombarded with information. You also have to stand up for too long at the private entrance to the Tower of London, which leads to a lack of time later. Lunch was the same, too stress, the quality was great but you couldn't enjoy it. I'd like to ask the organisers: please
                    </p>
                </div>
            </div>
        </div>
        <div class="voir">
            <p class="voir-plus">Voir plus</p>
        </div>
    </section>
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

                    <a class="btn">
                        Details
                    </a>
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
    <p class="copyright">
        Copyright 2024 Abeona. All rights reserved
    </p>
</footer>
</html>