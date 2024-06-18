<%@ page import="java.util.*, com.google.gson.Gson" %>
<%@ page import="stat.StatReservationMensuelHotel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Créez des listes pour stocker les années et les nombres de clients
    List<String> mois = new ArrayList<>();
    List<Integer> nombre_reservation = new ArrayList<>();
    
    // Remplissez les listes avec les données extraites de la requête
    List<StatReservationMensuelHotel> nombre_reservations = (List<StatReservationMensuelHotel>) request.getAttribute("statReservationMensuelHotel");
    for (StatReservationMensuelHotel v_nombre_reservations : nombre_reservations) {
        mois.add(v_nombre_reservations.getMois());
        nombre_reservation.add(v_nombre_reservations.getNombre_reservations());
    }
%>

<%@ include file="../template/header.jsp" %>

<div class="section-1 row">
<section class="section-text">
<h1 style="text-align: center;">Les statistiques</h1>
</section>
<div class="button-group row">
    <a class="btn abeona-custom_4" href="TraitementStatReservationMensuelGeneral">General</a>
<a class="btn abeona-custom_4 active" href="TraitementStatReservationMensuelHotel">Hotel</a>
<a class="btn abeona-custom_4" href="TraitementStatReservationMensuelTransport">Transport</a>
</div>
</div>

<div class="section-2 row chart">
<div class="chart_header">
    <h1>Evolutions des nombres de reservations mensuels General</h1>
    <div class="options_container">
        <div class="input-section type2">
            <form action="TraitementStatReservationMensuelHotel" method="post">
            <label for="year1">Année</label>
            <input type="text" name="year1" id="year1" class="form-control" placeholder="Entrez l'année">
            <input type="submit" class="btn-custom" value="Valider">
        </form>
        </div>
    </div>
</div>
<div class="chart_container">
    <div class="container">
    
        <canvas class="my_chart" id="myChart"></canvas>
    </div>
</div>
</div>
</body>
<%@ include file="../template/footer.jsp" %>


<script>
    var mois = <%= new Gson().toJson(mois) %>;
    var nombre_reservation = <%= new Gson().toJson(nombre_reservation) %>;
    
    var ctx = document.getElementById('myChart').getContext('2d');
    
    // Créez un graphique en ligne avec Chart.js
    var myChart = new Chart(ctx, {
        type: 'line', // Utilisation d'un line graph
        data: {
            labels: mois,
            datasets: [{
                label: 'Revenue Mensuel',
                data: nombre_reservation,
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