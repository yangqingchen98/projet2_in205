package com.ensta.dao.impl;

import com.ensta.exception.DaoException;
import com.ensta.dao.MembreDao;
import com.ensta.model.Membre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.DeleteDbFiles;

import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreDaoImpl implements MembreDao {	
	
	private static MembreDaoImpl instance;
	private MembreDaoImpl() { }	
	public static MembreDaoImpl getInstance() {
		if(instance == null) {
			instance = new MembreDaoImpl();
		}
		return instance;
	}
	
	public List<Membre> getList()  throws DaoException{
		List<Membre> list_temp = new ArrayList<Membre>();
		List<Membre> list = new ArrayList<Membre>();
		ResultSet rs = null;
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;");
	        
	        while(rs.next()){
	            Membre membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"),  rs.getString("telephone"), rs.getString("abonnement"));
	            list_temp.add(membre);
	        }
	        
	      //mettre les membres dans la liste selon l'ordre de leur id
	        int count = 1;
	        for (int i = 0; i<=list_temp.size(); i++) {
	        	for(Membre m : list_temp) {
	        		if (m.getId() == count) list.add(m);
		        }
	        	count++;
	        }
	        
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des membres", e);
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
	

	public Membre getById(int id) throws DaoException {
		
		ResultSet rs = null;
		Connection connection = null;
		Membre membre = new Membre();
	    try {
			connection = ConnectionManager.getConnection();
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = "+id+";");
			if(rs.next()){
            membre = new Membre(id, rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), rs.getString("abonnement"));
        }
        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération du membre: id=" + id, e);
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
		return membre;
	}
        
	
	public int create(String nom, String prenom, String adresse, String email, String telephone, String abonnement) throws DaoException  {
		
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int idnew = -1;
	    try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('"+nom+"', '"+prenom+"', '"+adresse+"', '"+email+"', '"+telephone+"', '"+abonnement+"');", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			if(rs.next()){
				idnew = rs.getInt(1);				
			}
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la création du membre: "+nom+" "+prenom, e);
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
		return idnew;
	}
	
	public void update(Membre membre) throws DaoException {

		int id = membre.getId();
		String nom = membre.getNom();
		String prenom = membre.getPrenom();
		String adresse = membre.getAdresse();
		String email = membre.getEmail();
		String telephone = membre.getTelephone();
		String abonnement = membre.getAbonnement().getLabel();
		
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("UPDATE membre SET nom = '"+nom+"', prenom = '"+prenom+"', adresse = '"+adresse+"', email = '"+email+"', telephone = '"+telephone+"', abonnement = '"+abonnement+"' WHERE id = "+id+";");
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la la mise à jour du membre: "+nom+" "+prenom, e);
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
	public void delete(int id) throws DaoException  {
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DELETE FROM membre WHERE id = "+id+";");
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la suppression du membre, id: "+id, e);
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
	public int count() throws DaoException  {
		ResultSet rs = null;
		Connection connection = null;
		int count = 0;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT COUNT(id) AS count FROM membre;");
	        if(rs.next()){ 
	        	count = rs.getInt(1);
	        }
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de compter le nombre des membres", e);
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

