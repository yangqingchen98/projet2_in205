package com.ensta.test;

import java.time.LocalDate;
import java.util.List;

import com.ensta.dao.impl.EmpruntDaoImpl;
import com.ensta.dao.impl.LivreDaoImpl;
import com.ensta.exception.DaoException;
import com.ensta.model.Emprunt;
import com.ensta.model.Livre;
import com.ensta.service.impl.EmpruntServiceImpl;

public class EmpruntServiceTest {
	
	
public static void main(String[] args) throws DaoException  {
		
		EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
		

		System.out.println("Emprunt:");
		
		System.out.println();
		System.out.println("getList");
		List<Emprunt> list1 = empruntServiceImpl.getList();
		for (Emprunt e:list1) {
			System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());
		}
		
		
		System.out.println();
		System.out.println("getListCurrent");
		List<Emprunt> list2 = empruntServiceImpl.getListCurrent();
		for (Emprunt e:list2) {
			System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());
		}
		
		
		
		
		System.out.println();
		System.out.println("getListCurrentByLivre, exemple: idLivre=2");
		List<Emprunt> list3 = empruntServiceImpl.getListCurrentByLivre(2);
		for (Emprunt e:list3) {
			System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());
		}
		
		
		System.out.println();
		System.out.println("getListCurrentByMembre, exemple: idMembre=5");
		List<Emprunt> list4 =  empruntServiceImpl.getListCurrentByMembre(5);
		for (Emprunt e:list4) {
			System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());
		}

		System.out.println();
		System.out.println("getById (exemple: id=1)");
		Emprunt e = empruntServiceImpl.getById(1);
		System.out.println("id: "+e.getId()+", idLivre: "+e.getIdLivre()+", idMembre: "+e.getIdMembre()+", DateEmprunt: "+e.getDateEmprunt()+", DateRetour: "+e.getDateRetour());

		

		System.out.println();
		System.out.println("count");
		int k = empruntServiceImpl.count();
		System.out.println("nombre total: "+k);
		
		
		System.out.println();
		System.out.println("create idlivre=6, idmembre=6, now");
		empruntServiceImpl.create(6, 6, LocalDate.now());
		int count = empruntServiceImpl.count();
		Emprunt e2 = empruntServiceImpl.getById(count);
		System.out.println("id: "+e2.getId()+", idLivre: "+e2.getIdLivre()+", idMembre: "+e2.getIdMembre()+", DateEmprunt: "+e2.getDateEmprunt()+", DateRetour: "+e2.getDateRetour());
		
		System.out.println();
		System.out.println("returnbook idLivre=8, afficher les emprunts en cours");
		empruntServiceImpl.returnBook(8);
		List<Emprunt> list5 = empruntServiceImpl.getList();
		for (Emprunt e5:list5) {
			System.out.println("id: "+e5.getId()+", idLivre: "+e5.getIdLivre()+", idMembre: "+e5.getIdMembre()+", DateEmprunt: "+e5.getDateEmprunt()+", DateRetour: "+e5.getDateRetour());
		}
		
		
		LivreDaoImpl livredaoimpl = LivreDaoImpl.getInstance();
		System.out.println();
		System.out.println("isLivreDispo");
		List<Livre> list6 = livredaoimpl.getList();
		for (Livre l:list6) {
			System.out.println("idLivre: "+l.getId()+" : "+empruntServiceImpl.isLivreDispo(l.getId()));
			
		}
}
}
