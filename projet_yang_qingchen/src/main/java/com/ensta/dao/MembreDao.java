package com.ensta.dao;
import java.util.List;
import com.ensta.exception.DaoException;
import com.ensta.model.Membre;


public interface MembreDao {
	public List<Membre> getList()throws DaoException;
	public Membre getById(int id) throws DaoException;
	public int create(String nom, String prenom, String adresse, String email, String telephone, String abonnement) throws DaoException ;
	public void update(Membre membre) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
