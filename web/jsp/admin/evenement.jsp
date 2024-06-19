<%@include file="../shared/sidebar.jsp" %>

<%@page import="model.client.Ville" %>   
<%@page import="model.client.CategorieEvenement" %>   
<%@page import="model.client.Evenement" %>   
<%@page import="java.util.HashMap" %> 
<%@page import="java.util.ArrayList" %> 
<%
    ArrayList<Ville> listeVille = (ArrayList<Ville>)request.getAttribute("listeVille");
    ArrayList<CategorieEvenement> categories = (ArrayList<CategorieEvenement>)request.getAttribute("categories");
    ArrayList<Evenement> listeEvenement = (ArrayList<Evenement>)request.getAttribute("listeEvenement");
    HashMap<String, Integer> nombre = (HashMap<String, Integer>)request.getAttribute("nombreEvenement");
%>

    <!-- Nombre -->
        <h2 class="main__title">Nombre de partenaire</h2>
        <div class="main__number">
            <div class="main__number__wrapper">
                <div class="main__number__wrapper__img">
                    <i class="fa fa-calendar"></i>
                </div>
                <div class="main__number__wrapper__content">
                    <div class="main__number__wrapper__content-title">Total evenements cet annee</div>
                    <div class="main__number__wrapper__content-value"><%= nombre.get("total") %></div>
                </div>
            </div>
            <div class="main__number__wrapper">
                <div class="main__number__wrapper__img">
                    <i class="fa fa-calendar-alt"></i>
                </div>
                <div class="main__number__wrapper__content">
                    <div class="main__number__wrapper__content-title">Evenements cet mois</div>
                    <div class="main__number__wrapper__content-value"><%= nombre.get("mois") %></div>
                </div>
            </div>
            <div class="main__number__wrapper">
                <div class="main__number__wrapper__img">
                    <i class="fa fa-calendar-check"></i>
                </div>
                <div class="main__number__wrapper__content">
                    <div class="main__number__wrapper__content-title">Aujourd'hui</div>
                    <div class="main__number__wrapper__content-value"><%= nombre.get("jour") %></div>
                </div>
            </div>
        </div>

        <!-- Modal insertion -->
        <div class="modal" id="evenement_modal">
            <div class="modal_content">
                <h2 class="title">Insertion Evenement Calendrier</h2>
                <span class="close">&times;</span>
                <form action="evenement" method="POST" id="insertion">
                    <div class="form-content input_lieu">
                        <label for="input_titre">Titre evenement </label>
                        <input type="text" name="titre" id="input_titre" placeholder="Titre..."required>
                    </div> 
                    <div class="form-content input_city">
                        <label for="input_categorie">Categorie Evenement</label>
                        <select name="categorie" id="input_categorie">
                        <% for(CategorieEvenement c: categories){ %>
                            <option value="<%= c.get_id_categorie_evenement() %>"><%= c.get_libelle() %></option>
                        <% } %>
                        </select>
                    </div>
                    <div class="form-content input_date_insertion_fin">
                        <label for="input_i_f">Date Evenement</label>
                        <input type="date" name="date_evenement" id="input_i_f" required>
                    </div>
                    <div class="form-content input_desc">
                        <label for="input_description">Description</label>
                        <textarea name="desc" placeholder="Description..." id="input_description"required></textarea>
                    </div>
                    <button type="submit">Inserer</button>
                </form>
            </div>
        </div>
        <!-- List of partenaire -->
        <div class="head">
            <h2 class="main__title">Liste des evenements</h2>
            <button id="btn_modal">Add</button>
        </div>
        <div class="main__list">
            <div class="main__list__tabs">
                <div class="main__list__tabs__filter">
                    <form action="" method="POST" id="filter-container">
                        <div class="form-content input_name">
                            <label for="input_nom">Nom</label>
                            <input type="text" name="nom" id="input_nom">
                        </div>
                        <div class="form-content input_city">
                            <label for="input_ville">Categorie</label>
                            <select name="categorie" id="input_categorie">
                                <option value="">Tous</option>
                            </select>
                        </div>
                        <div class="form-content input_city">
                            <label for="input_ville">Ville</label>
                            <select name="id_ville" id="input_ville">
                                <% for(Ville v: listeVille){ %>
                                    <option value="<%= v.get_id_ville() %>"><%= v.get_nom_ville() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-content input_date_insertion_debut">
                            <label for="input_date_i_d">Date debut</label>
                            <input type="date" name="date_debut" id="input_date_d">
                        </div>
                        <div class="form-content input_date_insertion_fin">
                            <label for="input_i_f">Date fin</label>
                            <input type="date" name="date_fin" id="input_date_f">
                        </div>
                        <input type="hidden" id="tab" name="tab"value="hoho">
                        <input type="hidden" id="type" name="type" value="hoho">
                        <button type="submit" id="submit_filter">Rechercher</button>
                    </form>
                </div>
                <div class="main__list__tabs__header" id="list_tabs">
                    <button class="main__list__tabs__header__content active" data-tab="evenement-calendrier">CALENDRIER</button>
                    <button class="main__list__tabs__header__content" data-tab="evenement-hotel">HOTEL</button>
                </div>
                <div class="main__list__tabs__body">
                    <div id="evenement-calendrier_container" class="main__list__tabs__body__content">
                        <div class="table-container-evenement-calendrier" id="evenement-calendrier"></div>
                    </div>
                    <div id="evenement-hotel" class="main__list__tabs__body__content active">
                        <div class="table-container-evenement-hotel" id="evenement-hotel"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%@include file="../shared/footer.jsp" %>