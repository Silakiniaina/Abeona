package obj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Attractions{
	String id_attraction;
	String nom_attraction;
	String description_attraction;
	String id_ville;
	String id_categorie_attraction;
	Date date_insertion;

	public Attractions() throws Exception {
	}

	public Attractions(String id_attraction, String nom_attraction, String description_attraction, String id_ville, String id_categorie_attraction, Date date_insertion) throws Exception {
		this.id_attraction = id_attraction;
		this.nom_attraction = nom_attraction;
		this.description_attraction = description_attraction;
		this.id_ville = id_ville;
		this.id_categorie_attraction = id_categorie_attraction;
		this.date_insertion = date_insertion;
	}

	public void setId_attraction(String newId_attraction) throws Exception {
		this.id_attraction = newId_attraction;
	}

	public void setNom_attraction(String newNom_attraction) throws Exception {
		this.nom_attraction = newNom_attraction;
	}

	public void setDescription_attraction(String newDescription_attraction) throws Exception {
		this.description_attraction = newDescription_attraction;
	}

	public void setId_ville(String newId_ville) throws Exception {
		this.id_ville = newId_ville;
	}

	public void setId_categorie_attraction(String newId_categorie_attraction) throws Exception {
		this.id_categorie_attraction = newId_categorie_attraction;
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public String getId_attraction(){
		return this.id_attraction;
	}

	public String getNom_attraction(){
		return this.nom_attraction;
	}

	public String getDescription_attraction(){
		return this.description_attraction;
	}

	public String getId_ville(){
		return this.id_ville;
	}

	public String getId_categorie_attraction(){
		return this.id_categorie_attraction;
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}

	public List<Attractions> getAll() throws Exception {
		List<Attractions> attractionss = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Attractions";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Attractions obj = new Attractions(
				resultSet.getString("id_attraction"),
				resultSet.getString("nom_attraction"),
				resultSet.getString("description_attraction"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_categorie_attraction"),
				resultSet.getDate("date_insertion")
				);
				attractionss.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Attractionss: " + e.getMessage());
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

		if (attractionss.isEmpty()) {
			throw new Exception("No Attractionss found");
		}

		return attractionss;
	}

	public Attractions getById(String id_attraction) throws Exception {
		Attractions attractions = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Attractions WHERE id_attraction = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_attraction);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				attractions = new Attractions(
				resultSet.getString("id_attraction"),
				resultSet.getString("nom_attraction"),
				resultSet.getString("description_attraction"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_categorie_attraction"),
				resultSet.getDate("date_insertion")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Attractionss: " + e.getMessage());
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

		return attractions;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Attractions (id_attraction,nom_attraction,description_attraction,id_ville,id_categorie_attraction,date_insertion) VALUES (generate_id_attraction(),?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_attraction());
            statement.setString(2, getDescription_attraction());
            statement.setString(3, getId_ville());
            statement.setString(4, getId_categorie_attraction());
            statement.setDate(5, getDate_insertion());
            statement.executeUpdate();
            System.out.println("Donnees Attractions inserer avec succes");
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
            String query = "UPDATE Attractions SET nom_attraction = ? ,description_attraction = ? ,id_ville = ? ,id_categorie_attraction = ? ,date_insertion = ?  WHERE id_attraction = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_attraction());
            statement.setString(2, getDescription_attraction());
            statement.setString(3, getId_ville());
            statement.setString(4, getId_categorie_attraction());
            statement.setDate(5, getDate_insertion());
            statement.setString(6, getId_attraction());
            statement.executeUpdate();
            System.out.println("Donnees Attractions mise a jour avec succes");
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

	public void delete(String id_attraction)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Attractions WHERE id_attraction = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_attraction);
            statement.executeUpdate();
            System.out.println("Donnees Attractions supprimee avec succes");
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
