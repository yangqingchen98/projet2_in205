package com.ensta.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.ensta.dao.impl.EmpruntDaoImpl;
import com.ensta.exception.DaoException;
import com.ensta.model.Emprunt;

public class EmpruntDaoTest {
	
	public static void main(String[] args) throws DaoException  {
		EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
		
		
		System.out.println("Emprunt:");
		
		System.out.println();
		System.out.println("getList");
		List<Emprunt> list1 = empruntDaoImpl.getList();
		for (Emprunt e:list1) {
			System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());
		}
		
		
		System.out.println();
		System.out.println("getListCurrent");
		List<Emprunt> list2 = empruntDaoImpl.getListCurrent();
		for (Emprunt e:list2) {
			System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());
		}
		
		System.out.println();
		System.out.println("getListCurrentByLivre (exemple: idLivre=2)");
		List<Emprunt> list3 = empruntDaoImpl.getListCurrentByLivre(2);
		for (Emprunt e:list3) {
			System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());
		}
		
		System.out.println();
		System.out.println("getListCurrentByMembre (exemple: idMembre=5)");
		List<Emprunt> list4 = empruntDaoImpl.getListCurrentByMembre(5);
		for (Emprunt e:list4) {
			System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());
		}

		System.out.println();
		System.out.println("getById (exemple: id=1)");
		Emprunt e = empruntDaoImpl.getById(1);
		System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());

		System.out.println();
		System.out.println("count");
		int k = empruntDaoImpl.count();
		System.out.println("nombre total: "+k);
		
		System.out.println();
		System.out.println("update (exemple: update l'emprunt dont id=2, on fixe maintenant idLivre=10, idMembre=10, 2 DateTime = now)");
		Emprunt ee = new Emprunt(2, 10,10, LocalDate.now(),LocalDate.now());
		empruntDaoImpl.update(ee);
		Emprunt e1 = empruntDaoImpl.getById(2);
		System.out.println("id: "+e1.getId()+", idLivre: "+e1.getIdLivre()+", idMembre: "+e1.getIdMembre()+", DateEmprunt: "+e1.getDateEmprunt()+", DateRetour: "+e1.getDateRetour());
		
		System.out.println();
		System.out.println("create idlivre=4, idmembre=4, now ");
		empruntDaoImpl.create(4, 4, LocalDate.now());
		int count = empruntDaoImpl.count();
		List<Emprunt> list5 = empruntDaoImpl.getList();
		for (Emprunt e5:list5) {
			System.out.println("id: "+e5.getId()+", idLivre: "+e5.getIdLivre()+", idMembre: "+e5.getIdMembre()+", DateEmprunt: "+e5.getDateEmprunt()+", DateRetour: "+e5.getDateRetour());
		}
	}

}
