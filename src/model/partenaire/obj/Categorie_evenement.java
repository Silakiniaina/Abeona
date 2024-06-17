package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Categorie_evenement{
	String id_categorie_evenement;
	String libelle;

	public Categorie_evenement() throws Exception {
	}

	public Categorie_evenement(String id_categorie_evenement, String libelle) throws Exception {
		this.id_categorie_evenement = id_categorie_evenement;
		this.libelle = libelle;
	}

	public void setId_categorie_evenement(String newId_categorie_evenement) throws Exception {
		this.id_categorie_evenement = newId_categorie_evenement;
	}

	public void setLibelle(String newLibelle) throws Exception {
		this.libelle = newLibelle;
	}

	public String getId_categorie_evenement(){
		return this.id_categorie_evenement;
	}

	public String getLibelle(){
		return this.libelle;
	}

	public List<Categorie_evenement> getAll() throws Exception {
		List<Categorie_evenement> categorie_evenements = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_evenement";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Categorie_evenement obj = new Categorie_evenement(
				resultSet.getString("id_categorie_evenement"),
				resultSet.getString("libelle")
				);
				categorie_evenements.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_evenements: " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new Exception("Error while closing resources: " + e.getMessage());
			}
		}

		if (categorie_evenements.isEmpty()) {
			throw new Exception("No Categorie_evenements found");
		}

		return categorie_evenements;
	}

	public Categorie_evenement getById(String id_categorie_evenement) throws Exception {
		Categorie_evenement categorie_evenement = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_evenement WHERE id_categorie_evenement = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_categorie_evenement);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				categorie_evenement = new Categorie_evenement(
				resultSet.getString("id_categorie_evenement"),
				resultSet.getString("libelle")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_evenements: " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new Exception("Error while closing resources: " + e.getMessage());
			}
		}
		return categorie_evenement;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Categorie_evenement (id_categorie_evenement,libelle) VALUES (generate_id_categorie_evenement(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_evenement inserer avec succes");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	public void update()throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "UPDATE Categorie_evenement SET libelle = ?  WHERE id_categorie_evenement = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.setString(2, getId_categorie_evenement());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_evenement mise a jour avec succes");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }finally{
            try {
                if (statement!=null) {
                    statement.close();
                }
                if (connection!=null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	public void delete(String id_categorie_evenement)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Categorie_evenement WHERE id_categorie_evenement = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_categorie_evenement);
            statement.executeUpdate();
            System.out.println("Donnees Categorie_evenement supprimee avec succes");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }finally{
            try {
                if (statement!=null) {
                    statement.close();
                }
                if (connection!=null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

}
