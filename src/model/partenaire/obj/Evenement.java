package obj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Evenement{
	String id_evenement;
	String nom_evenement;
	Date date_evenement;
	Date date_insertion;
	String description;
	String id_categorie_evenement;
	String id_ville;
	String id_hotel;

	public Evenement() throws Exception {
	}

	public Evenement(String id_evenement, String nom_evenement, Date date_evenement, Date date_insertion, String description, String id_categorie_evenement, String id_ville, String id_hotel) throws Exception {
		this.id_evenement = id_evenement;
		this.nom_evenement = nom_evenement;
		this.date_evenement = date_evenement;
		this.date_insertion = date_insertion;
		this.description = description;
		this.id_categorie_evenement = id_categorie_evenement;
		this.id_ville = id_ville;
		this.id_hotel = id_hotel;
	}

	public void setId_evenement(String newId_evenement) throws Exception {
		this.id_evenement = newId_evenement;
	}

	public void setNom_evenement(String newNom_evenement) throws Exception {
		this.nom_evenement = newNom_evenement;
	}

	public void setDate_evenement(Date newDate_evenement) throws Exception {
		this.date_evenement = newDate_evenement;
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public void setDescription(String newDescription) throws Exception {
		this.description = newDescription;
	}

	public void setId_categorie_evenement(String newId_categorie_evenement) throws Exception {
		this.id_categorie_evenement = newId_categorie_evenement;
	}

	public void setId_ville(String newId_ville) throws Exception {
		this.id_ville = newId_ville;
	}

	public void setId_hotel(String newId_hotel) throws Exception {
		this.id_hotel = newId_hotel;
	}

	public String getId_evenement(){
		return this.id_evenement;
	}

	public String getNom_evenement(){
		return this.nom_evenement;
	}

	public Date getDate_evenement(){
		return this.date_evenement;
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}

	public String getDescription(){
		return this.description;
	}

	public String getId_categorie_evenement(){
		return this.id_categorie_evenement;
	}

	public String getId_ville(){
		return this.id_ville;
	}

	public String getId_hotel(){
		return this.id_hotel;
	}

	public List<Evenement> getAll() throws Exception {
		List<Evenement> evenements = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Evenement";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Evenement obj = new Evenement(
				resultSet.getString("id_evenement"),
				resultSet.getString("nom_evenement"),
				resultSet.getDate("date_evenement"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("description"),
				resultSet.getString("id_categorie_evenement"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_hotel")
				);
				evenements.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Evenements: " + e.getMessage());
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

		if (evenements.isEmpty()) {
			throw new Exception("No Evenements found");
		}

		return evenements;
	}

	public Evenement getById(String id_evenement) throws Exception {
		Evenement evenement = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Evenement WHERE id_evenement = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_evenement);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				evenement = new Evenement(
				resultSet.getString("id_evenement"),
				resultSet.getString("nom_evenement"),
				resultSet.getDate("date_evenement"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("description"),
				resultSet.getString("id_categorie_evenement"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_hotel")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Evenements: " + e.getMessage());
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

		return evenement;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Evenement (id_evenement,nom_evenement,date_evenement,date_insertion,description,id_categorie_evenement,id_ville,id_hotel) VALUES (generate_id_evenement(),?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_evenement());
            statement.setDate(2, getDate_evenement());
            statement.setDate(3, getDate_insertion());
            statement.setString(4, getDescription());
            statement.setString(5, getId_categorie_evenement());
            statement.setString(6, getId_ville());
            statement.setString(7, getId_hotel());
            statement.executeUpdate();
            System.out.println("Donnees Evenement inserer avec succes");
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
            String query = "UPDATE Evenement SET nom_evenement = ? ,date_evenement = ? ,date_insertion = ? ,description = ? ,id_categorie_evenement = ? ,id_ville = ? ,id_hotel = ?  WHERE id_evenement = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_evenement());
            statement.setDate(2, getDate_evenement());
            statement.setDate(3, getDate_insertion());
            statement.setString(4, getDescription());
            statement.setString(5, getId_categorie_evenement());
            statement.setString(6, getId_ville());
            statement.setString(7, getId_hotel());
            statement.setString(8, getId_evenement());
            statement.executeUpdate();
            System.out.println("Donnees Evenement mise a jour avec succes");
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

	public void delete(String id_evenement)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Evenement WHERE id_evenement = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_evenement);
            statement.executeUpdate();
            System.out.println("Donnees Evenement supprimee avec succes");
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
