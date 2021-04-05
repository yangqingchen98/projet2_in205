<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ensta.model.Emprunt" %>
<%@ page import="com.ensta.model.Livre" %>
<%@ page import="com.ensta.model.Membre" %>
<%@ page import="java.util.List" %>

<%! private List<Emprunt> emprunt = new ArrayList<Emprunt>();%>
<% emprunt = (List<Emprunt>) request.getAttribute("emprunt"); %>

<%! private List<Emprunt> empruntCurrent = new ArrayList<Emprunt>();%>
<% empruntCurrent = (List<Emprunt>) request.getAttribute("empruntCurrent"); %>

<%! private List<Livre> livredispo = new ArrayList<Livre>();%>
<% livredispo = (List<Livre>) request.getAttribute("livredispo"); %>

<%! private List<Membre> membredispo = new ArrayList<Membre>();%>
<% membredispo = (List<Membre>) request.getAttribute("membredispo"); %>

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
        <h1 class="page-announce-text valign">Emprunter un livre</h1>
      </div>
      <div class="row">
      <div class="container">
        <h5>Sélectionnez le livre et le membre emprunteur</h5>
        <div class="row">
	      <form action="/LibraryManager/emprunt_add" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s6">
	            <select id="idLivre" name="idLivre" class="browser-default">
	              <option value="" disabled selected>-- Livres --</option>
	              
	              
	              <% for(Livre l : livredispo){%>
	              
                  <option value="l.getId()">"<%= l.getTitre() %>", de <%= l.getAuteur() %></option>
                  <% }%>
	            </select>
	          </div>
	          <div class="input-field col s6">
	            <select id="idMembre" name="idMembre" class="browser-default">
	              <option value="" disabled selected>-- Membres --</option>
	              <!-- TODO : parcourir la liste des membres pouvant emprunter et afficher autant d'options que nécessaire, sur la base de l'exemple ci-dessous -->
                  
                  
                  <% for(Membre m : membredispo){%>
	              
                  <option value="id.getId()">"<%= m.getNom() %>",de  <%= m.getPrenom() %></option>
                  <% }%>
                  
	            </select>
	          </div>
	        </div>
	        <div class="row center">
	        
	        <input type="text" value="Enregistrer l'emprunt" onclick='window.location.href=("dashboard")'>
	    
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	    </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
