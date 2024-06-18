<%@ page import="java.util.*, com.google.gson.Gson" %>
<%@ page import="stat.StatRevenueMensuel" %>
<%@ page import="stat.TotalMoyenneRevenu" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="assets/css/revenue.css" />
    
<%

    TotalMoyenneRevenu totalMoyenneRevenu = (TotalMoyenneRevenu) request.getAttribute("totalMoyenneRevenu");
    // Créez des listes pour stocker les années et les nombres de clients
    List<String> mois = new ArrayList<>();
    List<Double> revenu_mensuel = new ArrayList<>();
    
    // Remplissez les listes avec les données extraites de la requête
    List<StatRevenueMensuel> revenu_mensuels = (List<StatRevenueMensuel>) request.getAttribute("statRevenueMensuel");
    for (StatRevenueMensuel v_revenu_mensuels : revenu_mensuels) {
        mois.add(v_revenu_mensuels.getMois());
        revenu_mensuel.add(v_revenu_mensuels.getRevenu_mensuel());
    }
%>

<%@ include file="../template/header.jsp" %>

<div class="chart-wrapper">
    <div class="main-container">
        <div class="sub-container">
            <div><div class="placeholder"></div></div>
            <div class="item-container">
                <img src="assets/images/revenue.png" alt="Image">
                <div>
                    <div class="label">Revenu Total</div>
                    <span>MGA <%= ((totalMoyenneRevenu != null && !Double.isNaN(totalMoyenneRevenu.getTotal_revenu_mensuel())) ? totalMoyenneRevenu.getTotal_revenu_mensuel() : 0.0) %></span>
                </div>
            </div>
        </div>
        <div class="sub-container">
            <div><div class="placeholder"></div></div>
            <div class="item-container">
                <img src="assets/images/moyenne.png" alt="Image">
                <div>
                    <div class="label">Moyenne mensuelle</div>
                    <span>MGA <%= (totalMoyenneRevenu != null && !Double.isNaN(totalMoyenneRevenu.getMoyenne_revenu_mensuel())) ? totalMoyenneRevenu.getMoyenne_revenu_mensuel() : 0.0 %></span>
                </div>
            </div>
        </div>
    </div>


    <div class="chart-title">
        <h2>Revenue Mensuel</h2>
    </div>
    <div class="chart-filter">
        <form action="TraitementStatRevenueMensuel" method="post">
            <label for="year1">Année</label>
            <input type="text" name="year1" id="year1" class="form-control" placeholder="Entrez l'année">
            <input type="submit" class="btn btn-custom" value="Valider">
        </form>
    </div>
    <canvas id="myChart"></canvas>
    </div>
</div>

<%@ include file="../template/footer.jsp" %>

<script>
var mois = <%= new Gson().toJson(mois) %>;
var revenu_mensuel = <%= new Gson().toJson(revenu_mensuel) %>;

var ctx = document.getElementById('myChart').getContext('2d');

// Créez un graphique en ligne avec Chart.js
var myChart = new Chart(ctx, {
    type: 'line', // Utilisation d'un line graph
    data: {
        labels: mois,
        datasets: [{
            label: 'Revenue Mensuel',
            data: revenu_mensuel,
            fill: false, // Désactive le remplissage sous la ligne
            borderColor: 'rgba(54, 162, 235, 1)', // Couleur de la ligne
            borderWidth: 2 // Épaisseur de la ligne
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
</script>