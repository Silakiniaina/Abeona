<%@page import="shared/sidebar.js" %>

    <div class="main">
        <!-- Nombre -->
        <h2 class="main__title">Nombre de partenaire</h2>
        <div class="main__number">
            <div class="main__number__wrapper">
                <div class="main__number__wrapper__img">
                    <i class="fa fa-database"></i>
                </div>
                <div class="main__number__wrapper__content">
                    <div class="main__number__wrapper__content-title">Total partenaires inscrits</div>
                    <div class="main__number__wrapper__content-value">545</div>
                </div>
            </div>
            <div class="main__number__wrapper">
                <div class="main__number__wrapper__img">
                    <i class="fa fa-hotel"></i>
                </div>
                <div class="main__number__wrapper__content">
                    <div class="main__number__wrapper__content-title">Inscrit cet mois</div>
                    <div class="main__number__wrapper__content-value">20</div>
                </div>
            </div>
        </div>

        <!-- Demande d'inscription -->
        <h2 class="main__title">Nombre de partenaire</h2>
        <div class="main__inscription">
            <p href="#" class="main__inscription__more" id="see-more">Voir plus</p>
        </div>

        <!-- List of partenaire -->
        <h2 class="main__title">Liste des partenaires</h2>
        <div class="main__list">
            <div class="main__list__tabs">
                <div class="main__list__tabs__filter">
                    <form action="" id="filter-container">
                        <div class="form-content input_name">
                            <label for="input_nom">Nom</label>
                            <input type="text" name="nom" id="input_nom">
                        </div>
                        <div class="form-content input_city">
                            <label for="input_ville">Ville</label>
                            <select name="ville" id="input_ville">
                                <option value="">Tous</option>
                                <option value="">Ville 1</option>
                                <option value="">Ville 2</option>
                                <option value="">Ville 3</option>
                            </select>
                        </div>
                        <div class="form-content input_eval">
                            <label for="input_evaluation">Evaluation</label>
                            <input type="number" name="evaluation" id="input_evaluation">
                        </div>
                        <div class="form-content input_date_insertion_debut">
                            <label for="input_date_i_d">Date insertion debut</label>
                            <input type="date" name="" id="input_date_i_d">
                        </div>
                        <div class="form-content input_date_insertion_fin">
                            <label for="input_i_f">Date insertion fin</label>
                            <input type="date" name="" id="input_i_f">
                        </div>
                        <button type="submit">Rechercher</button>
                    </form>
                </div>
                <div class="main__list__tabs__header" id="list_tabs">
                    <button class="main__list__tabs__header__content active" data-tab="hotel">HOTEL</button>
                    <button class="main__list__tabs__header__content" data-tab="transport">TRANSPORT</button>
                    <button class="main__list__tabs__header__content" data-tab="guide">GUIDE</button>
                </div>
                <div class="main__list__tabs__body">
                    <div id="hotel_container" class="main__list__tabs__body__content">
                        <div class="table-container-hotel" id="hotel"></div>
                    </div>
                    <div id="transport_container" class="main__list__tabs__body__content active">
                        <div class="table-container-transport" id="transport"></div>
                    </div>
                    <div id="guide_container" class="main__list__tabs__body__content">
                        <div class="table-container-guide" id="guide"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%@include file="shared/footer.jsp" %>