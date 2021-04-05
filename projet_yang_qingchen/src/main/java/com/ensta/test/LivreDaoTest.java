package com.ensta.test;

import java.time.LocalDate;
import java.util.List;

import com.ensta.dao.impl.LivreDaoImpl;
import com.ensta.exception.DaoException;
import com.ensta.model.Emprunt;
import com.ensta.model.Livre;

public class LivreDaoTest {
	
	public static void main(String[] args) throws DaoException  {
		
		
		System.out.println("Livre:");
		
		
		
		LivreDaoImpl livredaoimpl = LivreDaoImpl.getInstance();
		
		System.out.println();
		System.out.println("getList");
		List<Livre> list = livredaoimpl.getList();
		for (Livre l: list) {
			System.out.println("id: "+l.getId()+", Titre: "+l.getTitre()+", Auteur: "+l.getAuteur()+", isbn: "+l.getIsbn());
		}
		
		
		System.out.println();
		System.out.println("getById (exemple: id=3)");
		Livre l = livredaoimpl.getById(3);
		System.out.println("id: "+l.getId()+", Titre: "+l.getTitre()+", Auteur: "+l.getAuteur()+", isbn: "+l.getIsbn());
		
		
		System.out.println();
		System.out.println("delete (exemple: id=3) et afficher la nouveau liste");
		livredaoimpl.delete(3);
		List<Livre> list1 = livredaoimpl.getList();
		for (Livre l1: list1) {
			System.out.println("id: "+l1.getId()+", Titre: "+l1.getTitre()+", Auteur: "+l1.getAuteur()+", isbn: "+l1.getIsbn());
		}
		


		System.out.println();
		System.out.println("count");
		int k = livredaoimpl.count();
		System.out.println("nombre total: "+k);
		
		
		
		System.out.println();
		System.out.println("update (exemple: update le livre dont id=7, on fixe maintenant Titre=aa, Auteur=bb, isbn=1)");
		Livre ll = new Livre(7, "aa", "bb", "1");
		livredaoimpl.update(ll);
		Livre l2 = livredaoimpl.getById(7);
		System.out.println("id: "+l2.getId()+", Titre: "+l2.getTitre()+", Auteur: "+l2.getAuteur()+", isbn: "+l2.getIsbn());
		
		
		
		System.out.println();
		System.out.println("create (exemple: create un livre dont titre = y, auteur = q, isbn = 2");
		int j = livredaoimpl.create("y", "q", "2");
		Livre l3 = livredaoimpl.getById(j);
		System.out.println("id: "+l3.getId()+", Titre: "+l3.getTitre()+", Auteur: "+l3.getAuteur()+", isbn: "+l3.getIsbn());
		
		
	}
	
	

}
