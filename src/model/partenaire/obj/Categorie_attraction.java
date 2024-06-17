package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Categorie_attraction{
	String id_categorie_attraction;
	String libelle;

	public Categorie_attraction() throws Exception {
	}

	public Categorie_attraction(String id_categorie_attraction, String libelle) throws Exception {
		this.id_categorie_attraction = id_categorie_attraction;
		this.libelle = libelle;
	}

	public void setId_categorie_attraction(String newId_categorie_attraction) throws Exception {
		this.id_categorie_attraction = newId_categorie_attraction;
	}

	public void setLibelle(String newLibelle) throws Exception {
		this.libelle = newLibelle;
	}

	public String getId_categorie_attraction(){
		return this.id_categorie_attraction;
	}

	public String getLibelle(){
		return this.libelle;
	}

	public List<Categorie_attraction> getAll() throws Exception {
		List<Categorie_attraction> categorie_attractions = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_attraction";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Categorie_attraction obj = new Categorie_attraction(
				resultSet.getString("id_categorie_attraction"),
				resultSet.getString("libelle")
				);
				categorie_attractions.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_attractions: " + e.getMessage());
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

		if (categorie_attractions.isEmpty()) {
			throw new Exception("No Categorie_attractions found");
		}

		return categorie_attractions;
	}

	public Categorie_attraction getById(String id_categorie_attraction) throws Exception {
		Categorie_attraction categorie_attraction = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_attraction WHERE id_categorie_attraction = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_categorie_attraction);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				categorie_attraction = new Categorie_attraction(
				resultSet.getString("id_categorie_attraction"),
				resultSet.getString("libelle")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_attractions: " + e.getMessage());
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

		return categorie_attraction;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Categorie_attraction (id_categorie_attraction,libelle) VALUES (generate_id_categorie_attraction(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_attraction inserer avec succes");
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
            String query = "UPDATE Categorie_attraction SET libelle = ?  WHERE id_categorie_attraction = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.setString(2, getId_categorie_attraction());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_attraction mise a jour avec succes");
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

	public void delete(String id_categorie_attraction)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Categorie_attraction WHERE id_categorie_attraction = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_categorie_attraction);
            statement.executeUpdate();
            System.out.println("Donnees Categorie_attraction supprimee avec succes");
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
