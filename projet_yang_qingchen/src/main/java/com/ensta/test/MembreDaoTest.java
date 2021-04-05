package com.ensta.test;

import java.util.List;

import com.ensta.dao.impl.LivreDaoImpl;
import com.ensta.dao.impl.MembreDaoImpl;
import com.ensta.exception.DaoException;
import com.ensta.model.Livre;
import com.ensta.model.Membre;

public class MembreDaoTest {

	public static void main(String[] args) throws DaoException  {
		
		
		
		
		System.out.println("Membre:");
		
		MembreDaoImpl membredaoimpl = MembreDaoImpl.getInstance();
		
		System.out.println();
		System.out.println("getList");
		List<Membre> list = membredaoimpl.getList();
		for (Membre m: list) {
			System.out.println("id: "+m.getId()+", Nom: "+m.getNom()+", Prenom: "+m.getPrenom()+", Adresse: "+m.getAdresse()+", Email: "+m.getEmail()+", Telephone: "+m.getTelephone());
		}
		
		
		System.out.println();
		System.out.println("getById (exemple: id=3)");
		Membre m = membredaoimpl.getById(3);
		System.out.println("id: "+m.getId()+", Nom: "+m.getNom()+", Prenom: "+m.getPrenom()+", Adresse: "+m.getAdresse()+", Email: "+m.getEmail()+", Telephone: "+m.getTelephone());
		
		
		System.out.println();
		System.out.println("delete (exemple: id=3) et afficher la nouveau liste");
		membredaoimpl.delete(3);
		List<Membre> list1 = membredaoimpl.getList();
		for (Membre m1: list1) {
			System.out.println("id: "+m1.getId()+", Nom: "+m1.getNom()+", Prenom: "+m1.getPrenom()+", Adresse: "+m1.getAdresse()+", Email: "+m1.getEmail()+", Telephone: "+m1.getTelephone());
		}
	


		System.out.println();
		System.out.println("count");
		int k = membredaoimpl.count();
		System.out.println("nombre total: "+k);
		
		
		
		System.out.println();
		System.out.println("update (exemple: update le membre dont id=10, on fixe maintenant  Nom = y, Prenom = q, adresse = 1 rue, email = y@ensta.com, telephone = 06)");
		Membre membre2 = new Membre(10, "y", "q", "1 rue", "y@ensta.com", "06");
		membredaoimpl.update(membre2);
		List<Membre> list2 = membredaoimpl.getList();
		for (Membre m1: list2) {
			System.out.println("id: "+m1.getId()+", Nom: "+m1.getNom()+", Prenom: "+m1.getPrenom()+", Adresse: "+m1.getAdresse()+", Email: "+m1.getEmail()+", Telephone: "+m1.getTelephone());
		}
		
		System.out.println();
		System.out.println("create (exemple: create un membre dont Nom = aa, Prenom = cc, adresse = 2 rue, email = aa@ensta.com, telephone = 07");
		int j = membredaoimpl.create("aa", "cc", "2 rue", "aa@ensta.com", "07", "BASIC");
		Membre m1 = membredaoimpl.getById(j);
		System.out.println("id: "+m1.getId()+", Nom: "+m1.getNom()+", Prenom: "+m1.getPrenom()+", Adresse: "+m1.getAdresse()+", Email: "+m1.getEmail()+", Telephone: "+m1.getTelephone());

	}
}
