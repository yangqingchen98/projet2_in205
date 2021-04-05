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
        <h1 class="page-announce-text valign">Retour d'un livre</h1>
      </div>
      <div class="row">
      <div class="container">
        <h5>Sélectionnez le livre à retourner</h5>
        <div class="row">
	      <form action="/TP3Ensta/emprunt_return" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s12">
	            <select id="id" name="id" class="browser-default">
	              <option value="" disabled selected>---</option>

                    <% if(!empruntCurrent.isEmpty()) {
                	for(Emprunt e : empruntCurrent) { %> 
   
                  <!-- TODO : parcourir la liste des emprunts non rendus et afficher autant d'options que nécessaire, sur la base de l'exemple ci-dessous -->
                  <!-- TODO : si l'attribut id existe, l'option correspondante devra être sélectionnée par défaut (ajouter l'attribut selected dans la balise <option>) -->
                  <option value="e.getId()">"<%= livre.get(e.getIdLivre()-1).getTitre() %>", emprunté par <%= membre.get(e.getIdMembre()-1).getNom() %> <%= membre.get(e.getIdMembre()-1).getPrenom() %></option>
                 	<% }
					} %>   
					
	            </select>
	          </div>
			 <div class="row center">
			
	          <input type="submit" value="Return le livre" onclick='window.location.href=("emprunt_list")'>
	           <a href="emprunt_list"> Annuler</a>
	        </div>
	        
	         
	        </div>
	      </form>
	    </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
  <script>
    document.addEventListener('DOMContentLoaded', function() {
	  var elems = document.querySelectorAll('select');
	  var instances = M.FormSelect.init(elems, {});
	});
  </script>
</body>
</html>
