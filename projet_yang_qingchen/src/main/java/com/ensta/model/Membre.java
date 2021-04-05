package com.ensta.model;

public class Membre {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String telephone;
	public Abonnement abonnement;
	
	public Membre() {
		this.id = -1;
		nom = null;
		prenom = null;
		adresse = null;
		email = null;
		telephone = null;
		abonnement = null;
	}
	
	public Membre(int id, String nom, String prenom, String adresse, String email, String telephone, String abonnement) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;	
		this.abonnement = Abonnement.StringToAbonnement(abonnement);
	}
	
	public Membre(int id, String nom, String prenom, String adresse, String email, String telephone) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;	
		this.abonnement = Abonnement.StringToAbonnement("BASIC");
	}

	public void setId(int id) {this.id = id;}
	public void setNom(String nom) {this.nom = nom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public void setAdresse(String adresse) {this.adresse = adresse;}
	public void setEmail(String email) {this.email = email;}
	public void setTelephone(String telephone) {this.telephone = telephone;}
	public void setAbonnement(Abonnement abonnement) {this.abonnement = abonnement;}
	
	
	public int getId() {return id;}
	public String getNom() {return nom;}
	public String getPrenom() {return prenom;}
	public String getAdresse() {return adresse;}
	public String getEmail() {return email;}
	public String getTelephone() {return telephone;}
	public Abonnement getAbonnement() {return abonnement;}
	
}
