EX 2, 3 et 4 peut être testé par les ficher dans com.ensta.test:

ModelTest.java
pour tester Livre, Membre, Emprunt

EmpruntDaoTest.java   LivreDaoTest.java  MembreDaoTest.java
pour tester les Dao et 

EmpruntServiceTest.java   LivreServiceTest.java  MembreServiceTest.java
pour tester les Service

Les fonctions pour tester tous les fonctions des 3 Dao et des 3 Service sont déjà fournis dans ces *Test.java, vous pouvez juste les lancer un par un.

ATTENTION: avant d'exécuter chaque *Test.java, il faut exécuter FillDatabase.java encore une fois.





EX 5:

Pour exécuter, il faut exécuter LibraryServlet.java dans com.ensta.servlet. Pour les servlets que j'ai fait:

Dashboard peut afficher tous les informations demandées.

Livre: 
LivreAdd, LivreList, LivreDelete: fonctionne.
LivreDetails: peut afficher toutes les informations mais problème avec enregistrer, le mis-à-jour des livres est perdu quand on quitte la page de l'édition.

Membre: même cas avec Livre.

Emprunt: 
EmpruntList: fonctionne.
EmpruntReturn: afficher les choix des emprunts mais de temps en temps il y a des problèmes avec enregistrer.
EmpruntAdd: ne peut qu'afficher les choix possibles pour un nouveau emprunt mais problème avec enregistrer le nouveau emprunt.

Inconvénients: parfois il faut mettre à jour manuellement la page Web ou entrer manuellement l'URL pour revenir à l'URL précédente

