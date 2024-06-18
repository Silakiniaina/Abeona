<%@page import="model.shared.URL" %>
<%@include file="/WEB-INF/views/shared/sidebar.jsp" %>

    <h1>Hello, Bienvenue dans Abeona</h1>
    <h2>Votre agence de voyage</h2>
    <a href="<%= URL.base_url()+"partenaire" %>" >Partenaire</a>
    <%= URL.base_url() %>

<%@include file="/WEB-INF/views/shared/footer.jsp" %>