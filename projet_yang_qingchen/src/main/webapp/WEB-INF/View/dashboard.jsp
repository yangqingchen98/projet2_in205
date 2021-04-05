<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ensta.model.Emprunt" %>
<%@ page import="com.ensta.model.Livre" %>
<%@ page import="com.ensta.model.Membre" %>
<%@ page import="java.util.List" %>

<%! private List<Emprunt> emprunt = new ArrayList<Emprunt>();%>
<% emprunt = (List<Emprunt>) request.getAttribute("emprunt"); %>

<%! private List<Emprunt> empruntCurrent = new ArrayList<Emprunt>();%>
<% empruntCurrent = (List<Emprunt>) request.getAttribute("empruntCurrent"); %>

<%! private List<Livre> livre = new ArrayList<Livre>();%>
<% livre = (List<Livre>) request.getAttribute("livre"); %>

<%! private List<Membre> membre = new ArrayList<Membre>();%>
<% membre = (List<Membre>) request.getAttribute("membre"); %>



<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Tableau de bord</h1>
      </div>
      <div class="row">
        <div class="col l4 s6">
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3><%= membre.size() %></h3> <!-- TODO : afficher le nombre de membres � la place de 12 -->
              <p>Membres</p>
            </div>
            <div class="icon">
            <ion-icon name="people"></ion-icon>
            </div>
            <a href="membre_list" class="small-box-footer" class="animsition-link">Liste des membres <i class="fa fa-arrow-circle-right"></i></a>
            <a href="membre_add" class="small-box-footer" class="animsition-link">Ajouter un membre <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="col l4 s6">
          <div class="small-box bg-green">
            <div class="inner">
              <h3><%= livre.size() %></h3> <!-- TODO : afficher le nombre de livres � la place de 27 -->
              <p>Livres</p>
            </div>
            <div class="icon">
              <ion-icon name="book"></ion-icon>
            </div>
            <a href="livre_list" class="small-box-footer" class="animsition-link">Liste des livres <i class="fa fa-arrow-circle-right"></i></a>
            <a href="livre_add" class="small-box-footer" class="animsition-link">Ajouter un livre <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="col l4 s6">
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3><%= emprunt.size() %></h3> <!-- TODO : afficher le nombre d'emprunts � la place de 1515 -->
              <p>Emprunts</p>
            </div>
            <div class="icon">
              <ion-icon name="bookmarks"></ion-icon>
            </div>
            <a href="emprunt_list" class="small-box-footer" class="animsition-link">Liste des emprunts <i class="fa fa-arrow-circle-right"></i></a>
          	<a href="emprunt_add" class="small-box-footer" class="animsition-link">Emprunter un livre <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="container">
	        <div class="col s12">
	          <h5>Emprunts en cours</h5>
	          <table class="striped">
                <thead>
                    <tr>
                        <th>Livre</th>
                        <th>Membre emprunteur</th>
                        <th>Date d'emprunt</th>
                        <th>Retour</th>
                    </tr>
                </thead>
                <tbody id="results">
                <% if(!empruntCurrent.isEmpty()) {
                	for(Emprunt e : empruntCurrent) { %>
                    <tr>
                        <td><% for(Livre l : livre ) {if(l.getId()==e.getIdLivre()) {  %>  <%= l.getTitre()%> <em><%= l.getAuteur() %></em> <%}} %></td>
                        <td><% for(Membre m : membre ) {if(m.getId()==e.getIdMembre()) {  %>  <%= m.getNom()%> <%= m.getPrenom() %> <%}} %></td>
                        
                        <td><%= e.getDateEmprunt() %></td>
                 
                  
                        <td>
                            <a href="emprunt_return?id=idDeLEmprunt"><ion-icon class="table-item" name="log-in"></a>
                        </td>
                    </tr>
                    <% }
				} %>      
                    
                     <!-- TODO : parcourir la liste des emprunts en cours et les afficher selon la structure d'exemple ci-dessus -->
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
