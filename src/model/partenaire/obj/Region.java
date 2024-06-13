package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Region{
	String id_region;
	String nom_region;

	public Region() throws Exception {
	}

	public Region(String id_region, String nom_region) throws Exception {
		this.id_region = id_region;
		this.nom_region = nom_region;
	}

	public void setId_region(String newId_region) throws Exception {
		this.id_region = newId_region;
	}

	public void setNom_region(String newNom_region) throws Exception {
		this.nom_region = newNom_region;
	}

	public String getId_region(){
		return this.id_region;
	}

	public String getNom_region(){
		return this.nom_region;
	}

	public List<Region> getAll() throws Exception {
		List<Region> regions = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Region";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Region obj = new Region(
				resultSet.getString("id_region"),
				resultSet.getString("nom_region")
				);
				regions.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Regions: " + e.getMessage());
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
		return regions;
	}

	public Region getById(String id_region) throws Exception {
		Region region = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Region WHERE id_region = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_region);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				region = new Region(
				resultSet.getString("id_region"),
				resultSet.getString("nom_region")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Regions: " + e.getMessage());
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

		return region;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Region (id_region,nom_region) VALUES (generate_id_region(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_region());
            statement.executeUpdate();
            System.out.println("Donnees Region inserer avec succes");
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
