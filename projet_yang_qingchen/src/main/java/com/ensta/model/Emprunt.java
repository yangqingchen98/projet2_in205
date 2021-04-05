package com.ensta.model;
import java.time.LocalDate;

public class Emprunt {
	
	
	private int id;
	private int idMembre;
	private int idLivre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	
	public Emprunt() {
		
		this.id = -1;
		this.idMembre = -1;
		this.idLivre = -1;
		this.dateEmprunt = null;
		this.dateRetour = null;
	}
	
public Emprunt(int id, int idmembre, int idlivre, LocalDate dateemprunt, LocalDate dateretour) {
		
		this.id = id;
		this.idMembre = idmembre;
		this.idLivre = idlivre;
		this.dateEmprunt = dateemprunt;
		this.dateRetour = dateretour;
	}
	
	
	public void setId(int id) {this.id = id;}
	public void setIdMembre(int idmembre) {this.idMembre = idmembre;}
	public void setIdLivre(int idlivre) {this.idLivre = idlivre;}
	public void setDateEmprunt(LocalDate dateemprunt) {this.dateEmprunt = dateemprunt;}
	public void setDateRetour(LocalDate dateretour) {this.dateRetour = dateretour;}
	
	
	public int getId() {return id;}
	public int getIdMembre() {return idMembre;}
	public int getIdLivre() {return idLivre;}
	public LocalDate getDateEmprunt() {return dateEmprunt;}
	public LocalDate getDateRetour() {return dateRetour;}
}
