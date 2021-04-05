package com.ensta.dao.impl;

import com.ensta.exception.DaoException;
import com.ensta.dao.EmpruntDao;
import com.ensta.model.Emprunt;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDaoImpl implements EmpruntDao {
	
	private static EmpruntDaoImpl instance;
	private EmpruntDaoImpl() { }	
	public static EmpruntDaoImpl getInstance() {
		if(instance == null) {
			instance = new EmpruntDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<Emprunt> getList() throws DaoException {
		List<Emprunt> list = new ArrayList<Emprunt>();
		ResultSet rs = null;
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n"
	        		+ "FROM emprunt AS e\n"
	        		+ "INNER JOIN membre ON membre.id = e.idMembre\n"
	        		+ "INNER JOIN livre ON livre.id = e.idLivre\n"
	        		+ "ORDER BY dateRetour DESC;");
	   
	        while(rs.next()){
	        	int id = rs.getInt("id");
	        	int idMembre = rs.getInt("idMembre");
	        	int idLivre = rs.getInt("idLivre");
	        	LocalDate dateEmprunt = null;
	        	if (rs.getDate("dateEmprunt")!=null) dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
	        	LocalDate dateRetour = null;
	        	if (rs.getDate("dateRetour") != null) dateRetour = rs.getDate("dateRetour").toLocalDate();
	        	
	        	Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
	        	list.add(emprunt);
	        }
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		List<Emprunt> list = new ArrayList<Emprunt>();
		ResultSet rs = null;
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n"
	        		+ "FROM emprunt AS e\n"
	        		+ "INNER JOIN membre ON membre.id = e.idMembre\n"
	        		+ "INNER JOIN livre ON livre.id = e.idLivre\n"
	        		+ "WHERE dateRetour IS NULL;");
	        
	        while(rs.next()){
	        	int id = rs.getInt("id");
	        	int idMembre = rs.getInt("idMembre");
	        	int idLivre = rs.getInt("idLivre");
	        	LocalDate dateEmprunt = null;
	        	if (rs.getDate("dateEmprunt")!=null) dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
	        	LocalDate dateRetour = null;
	        	if (rs.getDate("dateRetour") != null) dateRetour = rs.getDate("dateRetour").toLocalDate();
	        	Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
	        	list.add(emprunt);
	            
	        }
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des emprunts pas encore rendus", e);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	       

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		List<Emprunt> list = new ArrayList<Emprunt>();
		ResultSet rs = null;
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n"
	        		+ "FROM emprunt AS e\n"
	        		+ "INNER JOIN membre ON membre.id = e.idMembre\n"
	        		+ "INNER JOIN livre ON livre.id = e.idLivre\n"
	        		+ "WHERE dateRetour IS NULL AND membre.id = "+idMembre+";");
	        
	        while(rs.next()){
	        	int id = rs.getInt("id");
	        	int idLivre = rs.getInt("idLivre");
	        	LocalDate dateEmprunt = null;
	        	if (rs.getDate("dateEmprunt")!=null) dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
	        	LocalDate dateRetour = null;
	        	if (rs.getDate("dateRetour") != null) dateRetour = rs.getDate("dateRetour").toLocalDate();
	        	Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
	        	list.add(emprunt);
	        }
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des emprunts pas encore rendus pour le membre de id:"+idMembre, e);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		List<Emprunt> list = new ArrayList<Emprunt>();

		ResultSet rs = null;
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n"
	        		+ "FROM emprunt AS e\n"
	        		+ "INNER JOIN membre ON membre.id = e.idMembre\n"
	        		+ "INNER JOIN livre ON livre.id = e.idLivre\n"
	        		+ "WHERE dateRetour IS NULL AND livre.id = "+idLivre+";");
	        
	        while(rs.next()){
	        	int id = rs.getInt("id");
	        	int idMembre = rs.getInt("idMembre");
	        	LocalDate dateEmprunt = null;
	        	if (rs.getDate("dateEmprunt")!=null) dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
	        	LocalDate dateRetour = null;
	        	if (rs.getDate("dateRetour") != null) dateRetour = rs.getDate("dateRetour").toLocalDate();
	        	Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
	        	list.add(emprunt);
	            
	        }
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des emprunts pas encore rendus pour le livre id:"+idLivre, e);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	@Override
	public Emprunt getById(int id) throws DaoException {

		ResultSet rs = null;
		Connection connection = null;
		Emprunt emprunt = new Emprunt();
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n"
	        		+ "FROM emprunt AS e\n"
	        		+ "INNER JOIN membre ON membre.id = e.idMembre\n"
	        		+ "INNER JOIN livre ON livre.id = e.idLivre\n"
	        		+ "WHERE e.id = "+id+";");
	        
	        if(rs.next()){
	        	int idMembre = rs.getInt("idMembre");
	        	int idLivre = rs.getInt("idLivre");
	        	LocalDate dateEmprunt = null;
	        	if (rs.getDate("dateEmprunt")!=null) dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
	        	LocalDate dateRetour = null;
	        	if (rs.getDate("dateRetour") != null) dateRetour = rs.getDate("dateRetour").toLocalDate();
	        	emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
	        }
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de l'emprunt de id:"+id, e);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return emprunt;
	}


	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int idnew = -1;
	    try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES ("+idMembre+", "+idLivre+", '"+dateEmprunt+"', "+null+");", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			if(rs.next()){
				idnew = rs.getInt(1);				
			}
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la création de l'emprunt idMembre : "+idMembre+" et idLivre: "+idLivre, e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	
	@Override
	public void update(Emprunt emprunt) throws DaoException {
		
		Connection connection = null;
		int id = emprunt.getId();
		int idMembre = emprunt.getIdMembre();
		int idLivre = emprunt.getIdLivre();
		LocalDate dateEmprunt = emprunt.getDateEmprunt();
		LocalDate dateRetour = emprunt.getDateRetour();
		
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("UPDATE emprunt SET idMembre = "+idMembre+", idLivre = "+idLivre+", dateEmprunt = '"+dateEmprunt+"', dateRetour = '"+dateRetour+"' WHERE id = "+id+";");
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la mise à jour de l'emprunt de id: "+id, e);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int count() throws DaoException {
		ResultSet rs = null;
		Connection connection = null;
		int count = 0;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT COUNT(id) AS count FROM emprunt;");
	        if(rs.next()){ 
	        	count = rs.getInt(1);
	        }
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de compter les emprunts", e);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return count;
	}
	

	

}
