<%@include file="../shared/sidebar.jsp" %>

<%@page import="model.client.Ville" %>   
<%@page import="model.client.CategorieEvenement" %>   
<%@page import="model.client.Evenement" %>   
<%@page import="java.util.HashMap" %> 
<%@page import="java.util.ArrayList" %> 
<%
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
                    <div class="main__number__wrapper__content-title">Total evenements</div>
                    <div class="main__number__wrapper__content-value"><%= nombre.get("total") %></div>
                </div>
            </div>
            <div class="main__number__wrapper">
                <div class="main__number__wrapper__img">
                    <i class="fa fa-calendar"></i>
                </div>
                <div class="main__number__wrapper__content">
                    <div class="main__number__wrapper__content-title">Cet annee</div>
                    <div class="main__number__wrapper__content-value"><%= nombre.get("annee") %></div>
                </div>
            </div>
            <div class="main__number__wrapper">
                <div class="main__number__wrapper__img">
                    <i class="fa fa-calendar-alt"></i>
                </div>
                <div class="main__number__wrapper__content">
                    <div class="main__number__wrapper__content-title">Cet mois</div>
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
                            <% for(CategorieEvenement c : categories){ %>
                                <option value="<%= c.get_id_categorie_evenement() %>"><%= c.get_libelle() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-content input_date_insertion_debut">
                        <label for="input_i_d">Date Debut Evenement</label>
                        <input type="date" name="date_debut" id="input_i_d" required>
                    </div>
                    <div class="form-content input_date_insertion_fin">
                        <label for="input_i_f">Date Fin Evenement</label>
                        <input type="date" name="date_fin" id="input_i_f" required>
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
                    <form action="evenement?mode=s" method="GET" id="filter-container">
                        <div class="form-content input_name">
                            <label for="input_nom">Nom</label>
                            <input type="text" name="nom" id="input_nom">
                        </div>
                        <div class="form-content input_city">
                        <label for="input_categorie">Categorie Evenement</label>
                        <select name="categorie" id="input_categorie">
                            <% for(CategorieEvenement c : categories){ %>
                                <option value="<%= c.get_id_categorie_evenement() %>"><%= c.get_libelle() %></option>
                            <% } %>
                        </select>
                        </div>
                        <div class="form-content input_date_evenement_debut">
                            <label for="input_date_i_d">Date evenement debut</label>
                            <input type="date" name="" id="input_date_i_d">
                        </div>
                        <div class="form-content input_date_evenement_fin">
                            <label for="input_i_f">Date evenement fin</label>
                            <input type="date" name="" id="input_i_f">
                        </div>
                        <input type="hidden" name="mode" value="s">
                        <button type="submit">Rechercher</button>
                    </form>
                </div>
                <div class="main__list__tabs__body">
                    <div id="evenement-calendrier_container" class="main__list__tabs__body__content">
                        <table>
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Titre</th>
                                    <th>Categorie</th>
                                    <th>Description</th>
                                    <th>Date debut</th>
                                    <th>Date fin</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Evenement e : listeEvenement){ %>
                                <tr>
                                    <td><%= e.get_id_evenement() %></td>
                                    <td><%= e.get_nom_evenement() %></td>
                                    <% 
                                        String id = "";
                                        for(CategorieEvenement c : categories){ 
                                            if(c.get_id_categorie_evenement().equals(e.get_id_categorie_evenement())){
                                                id = c.get_libelle();
                                                break;
                                            }
                                        }
                                    %>
                                    <td>
                                        <%= id %>
                                    </td>
                                    <td><%= e.get_description() %></td>
                                    <td><%= e.get_date_debut_evenement() %></td>
                                    <td><%= e.get_date_fin_evenement() %></td>
                                    <td>
                                        <div class="actions">
                                            <a href="evenement?mode=d&&id=<%= e.get_id_evenement() %>"class="delete">
                                                <i class="fa fa-trash"></i>
                                            </a>
                                            <a href="evenement?mode=u&&id=<%= e.get_id_evenement() %>" class="edit">
                                                <i class="fa fa-pen"></i>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

<%@include file="../shared/footer.jsp" %>