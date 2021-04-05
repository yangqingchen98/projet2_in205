package com.ensta.service.impl;

import com.ensta.exception.ServiceException;
import com.ensta.exception.DaoException;
import com.ensta.model.Livre;
import com.ensta.service.LivreService;
import com.ensta.dao.LivreDao;
import com.ensta.dao.impl.LivreDaoImpl;
import com.ensta.service.EmpruntService;
import java.util.ArrayList;
import java.util.List;

public class LivreServiceImpl implements LivreService{
	private static LivreServiceImpl instance = new LivreServiceImpl();
	private LivreServiceImpl() { }	
	public static LivreServiceImpl getInstance() {		
		return instance;
	}
	@Override
	public List<Livre> getList() {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		List<Livre> list = new ArrayList<Livre>();
		try {
			list = livreDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return list;
	}

	@Override
	public List<Livre> getListDispo() {
		EmpruntService empruntServiceImpl = EmpruntServiceImpl.getInstance();
		List<Livre> list = new ArrayList<Livre>();
		List<Livre> listLivre = new ArrayList<Livre>();
		listLivre = getList();
		try {
			for(Livre l : listLivre) {
				if (empruntServiceImpl.isLivreDispo(l.getId())) {
					list.add(l);
				}
			}	
		} catch (ServiceException e1) {
			System.out.println(e1.getMessage());			
		}
		return list;
	}
	

	@Override
	public Livre getById(int id) {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		Livre livre = new Livre();
		try {
			livre = livreDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livre;
	}

	@Override
	public int create(String titre, String auteur, String isbn) {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		int i = -1;
		try {
			i = livreDao.create(titre, auteur, isbn);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return i;
	}

	@Override
	public void update(Livre livre) {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		try {
			livreDao.update(livre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		
		
	}

	@Override
	public void delete(int id) {
		LivreDao livreDao = LivreDaoImpl.getInstance();

		try {
			livreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		
	}

	@Override
	public int count() {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		int i = -1;
		try {
			i = livreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return i;
	}

}
