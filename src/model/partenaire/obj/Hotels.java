package obj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Hotels{
	String id_hotel;
	String id_partenaire;
	String nom_hotel;
	String description;
	String id_categorie_hotel;
	double evaluation;
	String id_ville;
	String adresse;
	Date date_insertion;

	public Hotels() throws Exception {
	}

	public Hotels(String id_hotel, String id_partenaire, String nom_hotel, String description, String id_categorie_hotel, double evaluation, String id_ville, String adresse, Date date_insertion) throws Exception {
		this.id_hotel = id_hotel;
		this.id_partenaire = id_partenaire;
		this.nom_hotel = nom_hotel;
		this.description = description;
		this.id_categorie_hotel = id_categorie_hotel;
		this.evaluation = evaluation;
		this.id_ville = id_ville;
		this.adresse = adresse;
		this.date_insertion = date_insertion;
	}

	public void setId_hotel(String newId_hotel) throws Exception {
		this.id_hotel = newId_hotel;
	}

	public void setId_partenaire(String newId_partenaire) throws Exception {
		this.id_partenaire = newId_partenaire;
	}

	public void setNom_hotel(String newNom_hotel) throws Exception {
		this.nom_hotel = newNom_hotel;
	}

	public void setDescription(String newDescription) throws Exception {
		this.description = newDescription;
	}

	public void setId_categorie_hotel(String newId_categorie_hotel) throws Exception {
		this.id_categorie_hotel = newId_categorie_hotel;
	}

	public void setEvaluation(double newEvaluation) throws Exception {
		this.evaluation = newEvaluation;
	}

	public void setId_ville(String newId_ville) throws Exception {
		this.id_ville = newId_ville;
	}

	public void setAdresse(String newAdresse) throws Exception {
		this.adresse = newAdresse;
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public String getId_hotel(){
		return this.id_hotel;
	}

	public String getId_partenaire(){
		return this.id_partenaire;
	}

	public String getNom_hotel(){
		return this.nom_hotel;
	}

	public String getDescription(){
		return this.description;
	}

	public String getId_categorie_hotel(){
		return this.id_categorie_hotel;
	}

	public double getEvaluation(){
		return this.evaluation;
	}

	public String getId_ville(){
		return this.id_ville;
	}

	public String getAdresse(){
		return this.adresse;
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}

	public List<Hotels> getAll() throws Exception {
		List<Hotels> hotelss = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Hotels";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Hotels obj = new Hotels(
				resultSet.getString("id_hotel"),
				resultSet.getString("id_partenaire"),
				resultSet.getString("nom_hotel"),
				resultSet.getString("description"),
				resultSet.getString("id_categorie_hotel"),
				resultSet.getDouble("evaluation"),
				resultSet.getString("id_ville"),
				resultSet.getString("adresse"),
				resultSet.getDate("date_insertion")
				);
				hotelss.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Hotelss: " + e.getMessage());
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

		if (hotelss.isEmpty()) {
			throw new Exception("No Hotelss found");
		}

		return hotelss;
	}

	public Hotels getById(String id_hotel) throws Exception {
		Hotels hotels = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Hotels WHERE id_hotel = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_hotel);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				hotels = new Hotels(
				resultSet.getString("id_hotel"),
				resultSet.getString("id_partenaire"),
				resultSet.getString("nom_hotel"),
				resultSet.getString("description"),
				resultSet.getString("id_categorie_hotel"),
				resultSet.getDouble("evaluation"),
				resultSet.getString("id_ville"),
				resultSet.getString("adresse"),
				resultSet.getDate("date_insertion")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Hotelss: " + e.getMessage());
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

		return hotels;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Hotels (id_hotel,id_partenaire,nom_hotel,description,id_categorie_hotel,evaluation,id_ville,adresse,date_insertion) VALUES (generate_id_hotel(),?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_partenaire());
            statement.setString(2, getNom_hotel());
            statement.setString(3, getDescription());
            statement.setString(4, getId_categorie_hotel());
            statement.setDouble(5, getEvaluation());
            statement.setString(6, getId_ville());
            statement.setString(7, getAdresse());
            statement.setDate(8, getDate_insertion());
            statement.executeUpdate();
            System.out.println("Donnees Hotels inserer avec succes");
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
            String query = "UPDATE Hotels SET id_partenaire = ? ,nom_hotel = ? ,description = ? ,id_categorie_hotel = ? ,evaluation = ? ,id_ville = ? ,adresse = ? ,date_insertion = ?  WHERE id_hotel = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_partenaire());
            statement.setString(2, getNom_hotel());
            statement.setString(3, getDescription());
            statement.setString(4, getId_categorie_hotel());
            statement.setDouble(5, getEvaluation());
            statement.setString(6, getId_ville());
            statement.setString(7, getAdresse());
            statement.setDate(8, getDate_insertion());
            statement.setString(9, getId_hotel());
            statement.executeUpdate();
            System.out.println("Donnees Hotels mise a jour avec succes");
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

	public void delete(String id_hotel)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Hotels WHERE id_hotel = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_hotel);
            statement.executeUpdate();
            System.out.println("Donnees Hotels supprimee avec succes");
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
