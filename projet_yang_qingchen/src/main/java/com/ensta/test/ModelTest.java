package com.ensta.test;

import java.time.LocalDate;
import java.util.List;

import com.ensta.dao.impl.LivreDaoImpl;
import com.ensta.dao.impl.MembreDaoImpl;
import com.ensta.exception.DaoException;
import com.ensta.exception.ServiceException;
import com.ensta.model.Emprunt;
import com.ensta.model.Livre;
import com.ensta.model.Membre;
import com.ensta.service.impl.MembreServiceImpl;

public class ModelTest {
	public static void main(String[] args)   {
		Livre l1 = new Livre();
		int id = 20;
		String titre="this is titre";
		String auteur="this is auteur";
		String isbn="this is isbn";
		Livre l2 = new Livre(id, titre, auteur, isbn);
		System.out.println("default livre : id: "+l1.getId()+", Titre: "+l1.getTitre()+", Auteur: "+l1.getAuteur()+", isbn: "+l1.getIsbn());
		System.out.println("created livre : id: "+l2.getId()+", Titre: "+l2.getTitre()+", Auteur: "+l2.getAuteur()+", isbn: "+l2.getIsbn());
		
		Membre m1 = new Membre();
		int id1 = 21;
		String nom = "this is nom";
		String prenom = "this is prenom";
		String adresse = "this is adresse";
		String email = "this is email";
		String telephone = "this is telephone";
		String abonnement = "VIP";
		Membre m2 = new Membre(id1, nom, prenom, adresse, email, telephone, abonnement);
		System.out.println("default membre : id: "+m1.getId()+", Nom: "+m1.getNom()+", Prenom: "+m1.getPrenom()+", Adresse: "+m1.getAdresse()+", Email: "+m1.getEmail()+", Telephone: "+m1.getTelephone()+", Abonnement: "+m1.getAbonnement());
		System.out.println("created membre : id: "+m2.getId()+", Nom: "+m2.getNom()+", Prenom: "+m2.getPrenom()+", Adresse: "+m2.getAdresse()+", Email: "+m2.getEmail()+", Telephone: "+m2.getTelephone()+", Abonnement: "+m2.getAbonnement());
		
		
		Emprunt e1 = new Emprunt();
		int id2 =22;
		int idMembre = 200;
		int idLivre = 100;
		LocalDate dateEmprunt=LocalDate.now();
		LocalDate dateRetour=LocalDate.now();
		Emprunt e2 = new Emprunt(id2, idMembre, idLivre, dateEmprunt, dateRetour);
		System.out.println("default emprunt : id: "+e1.getId()+", idLivre: "+e1.getIdLivre()+", idMembre: "+e1.getIdMembre()+", DateEmprunt: "+e1.getDateEmprunt()+", DateRetour: "+e1.getDateRetour());
		System.out.println("created emprunt : id: "+e2.getId()+", idLivre: "+e2.getIdLivre()+", idMembre: "+e2.getIdMembre()+", DateEmprunt: "+e2.getDateEmprunt()+", DateRetour: "+e2.getDateRetour());
	
	
	
	
	}


}
