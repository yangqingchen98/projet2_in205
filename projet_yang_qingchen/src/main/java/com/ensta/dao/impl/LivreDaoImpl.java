package com.ensta.dao.impl;

import com.ensta.exception.DaoException;
import com.ensta.dao.LivreDao;
import com.ensta.model.Livre;
import com.ensta.model.Membre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao {
	
	private static LivreDaoImpl instance;
	private LivreDaoImpl() { }	
	public static LivreDaoImpl getInstance() {
		if(instance == null) {
			instance = new LivreDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Livre> getList() throws DaoException {
		List<Livre> list_temp = new ArrayList<Livre>();
		List<Livre> list = new ArrayList<Livre>();
		ResultSet rs = null;
		Connection connection = null;
	    try {
	    	
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT id, titre, auteur, isbn FROM livre;");
	        
	        while(rs.next()){
	            Livre livre = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn"));
	            list_temp.add(livre);
	            
	        }
	        int count = 1;
	        
	        //mettre les livres dans la liste selon l'ordre de leur id
	      
	        for (int i = 0; i<=list_temp.size(); i++) {
	        	for(Livre l : list_temp) {
	        		if (l.getId() == count) list.add(l);
		        }
	        	count++;
	        }
	        
	        connection.commit();
	        
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des livres", e);
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
	public Livre getById(int id) throws DaoException {
		ResultSet rs = null;
		Connection connection = null;
		Livre livre = new Livre();
		
	    try {
			connection = ConnectionManager.getConnection();
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT id, titre, auteur, isbn FROM livre WHERE id = "+id+";");
			if(rs.next()){
            livre = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn"));
        }
        connection.commit();
        
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération du livre: id=" + id, e);
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
		return livre;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int idnew = -1;
	    try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES('"+titre+"', '"+auteur+"', '"+isbn+"');", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			if(rs.next()){
				idnew = rs.getInt(1);				
			}
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la création du livre: "+titre+" "+auteur+" "+isbn, e);
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

	@Override
	public void update(Livre livre) throws DaoException {		
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("UPDATE livre SET titre = '"+livre.getTitre()+"', auteur = '"+livre.getAuteur()+"', isbn = '"+livre.getIsbn()+"' WHERE id = "+livre.getId()+";");
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la mise à jour du livre: "+livre.getTitre()+" "+livre.getAuteur()+" "+livre.getIsbn(), e);
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
	public void delete(int id) throws DaoException {
		Connection connection = null;
	    try {
			connection = ConnectionManager.getConnection();
	        connection.setAutoCommit(false);
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DELETE FROM livre WHERE id = "+id+";");
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de la suppression du livre, id: "+id, e);
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
	        rs = stmt.executeQuery("SELECT COUNT(id) AS count FROM livre;");
	        if(rs.next()){ 
	        	count = rs.getInt(1);
	        }
	        connection.commit();
	    } catch (SQLException e) {
			throw new DaoException("Problème lors de compter les livres", e);
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


