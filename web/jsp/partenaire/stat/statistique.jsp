<%@ page import="java.util.*, com.google.gson.Gson" %>
<%@ page import="stat.StatNombreclients" %>
<%@ page import="stat.StatNombreclients_ayant_achete_pack" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="assets/css/statistique.css" />
    


<%
    // Créez des listes pour stocker les années et les nombres de clients
    List<String> mois = new ArrayList<>();
    List<Integer> nombresClients = new ArrayList<>();
    
    // Remplissez les listes avec les données extraites de la requête
    List<StatNombreclients> nombreclients = (List<StatNombreclients>) request.getAttribute("nombreclients");
    for (StatNombreclients v_nombreclients : nombreclients) {
        mois.add(v_nombreclients.getMois());
        nombresClients.add(v_nombreclients.getNombre_clients());
    }

    List<String> mois2 = new ArrayList<>();
    List<Integer> nombresClients2 = new ArrayList<>();
    List<StatNombreclients_ayant_achete_pack> nombreclients_ayant_achete_pack = (List<StatNombreclients_ayant_achete_pack>) request.getAttribute("nombreclients_ayant_achete_pack");
    for (StatNombreclients_ayant_achete_pack v_nombreclients_ayant_achete_pack : nombreclients_ayant_achete_pack) {
        mois2.add(v_nombreclients_ayant_achete_pack.getMois());
        nombresClients2.add(v_nombreclients_ayant_achete_pack.getNombre_clients_ayant_achete_pack());
    }
    
%>
<%@ include file="../template/header.jsp" %>

<!-- Conteneur principal pour les graphiques -->
<div class="chart-wrapper">
<!-- Conteneur du premier graphique -->
<div class="chart-container">
  <div class="chart-title">
    <h2> Evolution nombre de client inscrits</h2>
  </div>
  <div class="chart-filter">
      <form action="TraitementStatistique" method="post">
        <label for="year1">Année</label>
        <input type="text" name="year1" id="year1" class="form-control" placeholder="Entrez l'année">
        <input type="submit" class="btn btn-custom" value="Valider">

      </form>
  </div>
  <canvas id="myChart"></canvas>
</div>


<!-- Conteneur du deuxième graphique -->
<div class="chart-container">
  <div class="chart-title">
  <h2>Evolution nombre de client ayant acheté un pack</h2>
    
  </div>
  
  <canvas id="myChart2"></canvas>
</div>
</div>
<%@ include file="../template/footer.jsp" %>



<script>
var mois = <%= new Gson().toJson(mois) %>;
var nombresClients = <%= new Gson().toJson(nombresClients) %>;

var ctx = document.getElementById('myChart').getContext('2d');

// Créez un graphique en ligne avec Chart.js
var myChart = new Chart(ctx, {
type: 'line', // Utilisation d'un line graph
data: {
    labels: mois,
    datasets: [{
        label: 'Nombre de clients par an',
        data: nombresClients,
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


var mois2 = <%= new Gson().toJson(mois2) %>;
var nombresClients2 = <%= new Gson().toJson(nombresClients2) %>;

var ctx = document.getElementById('myChart2').getContext('2d');

// Créez un graphique en ligne avec Chart.js
var myChart2 = new Chart(ctx, {
    type: 'line', // Utilisation d'un line graph
    data: {
        labels: mois2,
        datasets: [{
            label: 'Nombre Clients Ayant Achete Pack Par An',
            data: nombresClients2,
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
