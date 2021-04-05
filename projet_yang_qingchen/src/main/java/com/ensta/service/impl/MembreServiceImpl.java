package com.ensta.service.impl;

import com.ensta.exception.ServiceException;
import com.ensta.exception.DaoException;
import com.ensta.model.Membre;
import com.ensta.service.MembreService;
import com.ensta.dao.MembreDao;
import com.ensta.dao.impl.MembreDaoImpl;
import com.ensta.service.EmpruntService;

import java.util.ArrayList;
import java.util.List;

public class MembreServiceImpl implements MembreService {

	private static MembreServiceImpl instance = new MembreServiceImpl();
	private MembreServiceImpl() { }	
	public static MembreServiceImpl getInstance() {		
		return instance;
	}

	@Override
	public List<Membre> getList() {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		List<Membre> list = new ArrayList<Membre>();
		try {
			list = membreDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return list;
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() {
		EmpruntService empruntServiceImpl = EmpruntServiceImpl.getInstance();
		List<Membre> list = new ArrayList<Membre>();
		List<Membre> listMembre = new ArrayList<Membre>();
		listMembre = getList();
		try {
			for(Membre m : listMembre) {
				if (empruntServiceImpl.isEmpruntPossible(m)) {
					list.add(m);
				}
			}	
		} catch (ServiceException e1) {
			System.out.println(e1.getMessage());			
		}
		return list;
	}

	@Override
	public Membre getById(int id) {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		Membre membre = new Membre();
		try {
			membre = membreDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membre;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone, String abonnement) {
		int i = -1;
		MembreDao membreDao = MembreDaoImpl.getInstance();
		try {
			i = membreDao.create(nom, prenom, adresse, email, telephone, abonnement);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return i;
	}

	@Override
	public void update(Membre membre) {
		MembreDao membreDao = MembreDaoImpl.getInstance();

		try {
			membreDao.update(membre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void delete(int id) {
		MembreDao membreDao = MembreDaoImpl.getInstance();

		try {
			membreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public int count() {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		int count = -1;
		try {
			count = membreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return count;
	}

}
