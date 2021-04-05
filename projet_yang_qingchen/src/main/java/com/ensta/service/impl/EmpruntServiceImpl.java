package com.ensta.service.impl;

import com.ensta.exception.ServiceException;
import com.ensta.exception.DaoException;
import com.ensta.model.Emprunt;
import com.ensta.service.EmpruntService;
import com.ensta.dao.EmpruntDao;
import com.ensta.dao.impl.EmpruntDaoImpl;
import com.ensta.model.Membre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntServiceImpl implements EmpruntService {
	
	private static EmpruntServiceImpl instance = new EmpruntServiceImpl();
	private EmpruntServiceImpl() { }	
	public static EmpruntServiceImpl getInstance() {		
		return instance;
	}

	@Override
	public List<Emprunt> getList() {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> list = new ArrayList<Emprunt>();
		try {
			list = empruntDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return list;
	}

	@Override
	public List<Emprunt> getListCurrent() {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> list = new ArrayList<Emprunt>();
		try {
			list = empruntDao.getListCurrent();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return list;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> list = new ArrayList<Emprunt>();
		try {
			list = empruntDao.getListCurrentByMembre(idMembre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return list;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> list = new ArrayList<Emprunt>();
		try {
			list = empruntDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return list;
	}

	@Override
	public Emprunt getById(int id) {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		Emprunt emprunt = new Emprunt();

		try {
			emprunt = empruntDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunt;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();

		try {
			empruntDao.create(idMembre, idLivre, dateEmprunt);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void returnBook(int id) {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		int count = count();
		int idEmprunt = 0;
		for (int k = 1; k<=count; k++) {
			Emprunt emprunt1 = getById(k);
			if (emprunt1.getIdLivre()==id && emprunt1.getDateRetour()==null) {
				idEmprunt = emprunt1.getId();
			}
		}
		Emprunt emprunt = getById(idEmprunt);
		LocalDate localdate = LocalDate.now();
		emprunt.setDateRetour(localdate);
		
		try {
			empruntDao.update(emprunt);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public int count() {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		int count = -1;
		try {
			count = empruntDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return count;
		
	}

	@Override
	public boolean isLivreDispo(int idLivre) {
		List<Emprunt> list = getListCurrentByLivre(idLivre);
		for (Emprunt e : list) {
			if (e.getIdLivre() == idLivre && e.getDateRetour() == null) return false;
		}
		return true;
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) {
		List<Emprunt> list = getListCurrentByMembre(membre.getId());
		int count = 0;
		for (Emprunt e : list) {
			if (e.getIdMembre() == membre.getId() && e.getDateRetour() == null) count++;
		}
		
		int NombreMaxEmprunt = membre.getAbonnement().getNombreMaxEmprunt();
		if (count == NombreMaxEmprunt) return false;
		else return true;
	}

}
