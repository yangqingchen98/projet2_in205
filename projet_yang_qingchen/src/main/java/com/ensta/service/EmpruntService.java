package com.ensta.service;

import com.ensta.exception.ServiceException;
import com.ensta.model.Emprunt;
import com.ensta.model.Membre;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface EmpruntService {
	public List<Emprunt> getList() throws ServiceException;
	public List<Emprunt> getListCurrent() throws ServiceException;
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException;
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException;
	public Emprunt getById(int id) throws ServiceException;
	public void create(int idMembre, int idLivre, LocalDate localTime) throws ServiceException;
	public void returnBook(int id) throws ServiceException;
	public int count() throws ServiceException;
	public boolean isLivreDispo(int idLivre) throws ServiceException;
	public boolean isEmpruntPossible(Membre membre) throws ServiceException ;

}
