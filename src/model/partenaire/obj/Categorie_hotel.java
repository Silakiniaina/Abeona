package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Categorie_hotel{
	String id_categorie_hotel;
	String libelle;

	public Categorie_hotel() throws Exception {
	}

	public Categorie_hotel(String id_categorie_hotel, String libelle) throws Exception {
		this.id_categorie_hotel = id_categorie_hotel;
		this.libelle = libelle;
	}

	public void setId_categorie_hotel(String newId_categorie_hotel) throws Exception {
		this.id_categorie_hotel = newId_categorie_hotel;
	}

	public void setLibelle(String newLibelle) throws Exception {
		this.libelle = newLibelle;
	}

	public String getId_categorie_hotel(){
		return this.id_categorie_hotel;
	}

	public String getLibelle(){
		return this.libelle;
	}

	public List<Categorie_hotel> getAll() throws Exception {
		List<Categorie_hotel> categorie_hotels = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_hotel";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Categorie_hotel obj = new Categorie_hotel(
				resultSet.getString("id_categorie_hotel"),
				resultSet.getString("libelle")
				);
				categorie_hotels.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_hotels: " + e.getMessage());
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

		if (categorie_hotels.isEmpty()) {
			throw new Exception("No Categorie_hotels found");
		}

		return categorie_hotels;
	}

	public Categorie_hotel getById(String id_categorie_hotel) throws Exception {
		Categorie_hotel categorie_hotel = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_hotel WHERE id_categorie_hotel = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_categorie_hotel);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				categorie_hotel = new Categorie_hotel(
				resultSet.getString("id_categorie_hotel"),
				resultSet.getString("libelle")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_hotels: " + e.getMessage());
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

		return categorie_hotel;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Categorie_hotel (id_categorie_hotel,libelle) VALUES (generate_id_categorie_hotel(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_hotel inserer avec succes");
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

}
