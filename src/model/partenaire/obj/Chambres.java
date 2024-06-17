package obj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Chambres{
	String id_chambre;
	String id_hotel;
	String id_categorie_chambre;
	int capacite;
	double prix;
	String status;
	Date date_insertion;

	public Chambres() throws Exception {
	}

	public Chambres(String id_chambre, String id_hotel, String id_categorie_chambre, int capacite, double prix, String status, Date date_insertion) throws Exception {
		this.id_chambre = id_chambre;
		this.id_hotel = id_hotel;
		this.id_categorie_chambre = id_categorie_chambre;
		this.capacite = capacite;
		this.prix = prix;
		this.status = status;
		this.date_insertion = date_insertion;
	}

	public void setId_chambre(String newId_chambre) throws Exception {
		this.id_chambre = newId_chambre;
	}

	public void setId_hotel(String newId_hotel) throws Exception {
		this.id_hotel = newId_hotel;
	}

	public void setId_categorie_chambre(String newId_categorie_chambre) throws Exception {
		this.id_categorie_chambre = newId_categorie_chambre;
	}

	public void setCapacite(int newCapacite) throws Exception {
		this.capacite = newCapacite;
	}

	public void setPrix(double newPrix) throws Exception {
		this.prix = newPrix;
	}

	public void setStatus(String newStatus) throws Exception {
		this.status = newStatus;
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public String getId_chambre(){
		return this.id_chambre;
	}

	public String getId_hotel(){
		return this.id_hotel;
	}

	public String getId_categorie_chambre(){
		return this.id_categorie_chambre;
	}

	public int getCapacite(){
		return this.capacite;
	}

	public double getPrix(){
		return this.prix;
	}

	public String getStatus(){
		return this.status;
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}

	public List<Chambres> getAll() throws Exception {
		List<Chambres> chambress = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Chambres";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Chambres obj = new Chambres(
				resultSet.getString("id_chambre"),
				resultSet.getString("id_hotel"),
				resultSet.getString("id_categorie_chambre"),
				resultSet.getInt("capacite"),
				resultSet.getDouble("prix"),
				resultSet.getString("status"),
				resultSet.getDate("date_insertion")
				);
				chambress.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Chambress: " + e.getMessage());
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

		if (chambress.isEmpty()) {
			throw new Exception("No Chambress found");
		}

		return chambress;
	}

	public Chambres getById(String id_chambre) throws Exception {
		Chambres chambres = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Chambres WHERE id_chambre = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_chambre);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				chambres = new Chambres(
				resultSet.getString("id_chambre"),
				resultSet.getString("id_hotel"),
				resultSet.getString("id_categorie_chambre"),
				resultSet.getInt("capacite"),
				resultSet.getDouble("prix"),
				resultSet.getString("status"),
				resultSet.getDate("date_insertion")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Chambress: " + e.getMessage());
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
		return chambres;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Chambres (id_chambre,id_hotel,id_categorie_chambre,capacite,prix,status,date_insertion) VALUES (generate_id_chambre(),?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_hotel());
            statement.setString(2, getId_categorie_chambre());
            statement.setInt(3, getCapacite());
            statement.setDouble(4, getPrix());
            statement.setObject(5, getStatus(),Types.OTHER);
            statement.setDate(6, getDate_insertion());
            statement.executeUpdate();
            System.out.println("Donnees Chambres inserer avec succes");
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
            String query = "UPDATE Chambres SET id_hotel = ? ,id_categorie_chambre = ? ,capacite = ? ,prix = ? ,status = ? ,date_insertion = ?  WHERE id_chambre = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_hotel());
            statement.setString(2, getId_categorie_chambre());
            statement.setInt(3, getCapacite());
            statement.setDouble(4, getPrix());
            statement.setObject(5, getStatus(),Types.OTHER);
            statement.setDate(6, getDate_insertion());
            statement.setString(7, getId_chambre());
            statement.executeUpdate();
            System.out.println("Donnees Chambres mise a jour avec succes");
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

	public void delete(String id_chambre)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Chambres WHERE id_chambre = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_chambre);
            statement.executeUpdate();
            System.out.println("Donnees Chambres supprimee avec succes");
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
