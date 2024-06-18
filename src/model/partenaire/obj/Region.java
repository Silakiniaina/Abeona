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
<<<<<<< Updated upstream
=======
	String id_province;
>>>>>>> Stashed changes

	public Region() throws Exception {
	}

<<<<<<< Updated upstream
	public Region(String id_region, String nom_region) throws Exception {
		this.id_region = id_region;
		this.nom_region = nom_region;
=======
	public Region(String id_region, String nom_region, String id_province) throws Exception {
		this.id_region = id_region;
		this.nom_region = nom_region;
		this.id_province = id_province;
>>>>>>> Stashed changes
	}

	public void setId_region(String newId_region) throws Exception {
		this.id_region = newId_region;
	}

	public void setNom_region(String newNom_region) throws Exception {
		this.nom_region = newNom_region;
	}

<<<<<<< Updated upstream
=======
	public void setId_province(String newId_province) throws Exception {
		this.id_province = newId_province;
	}

>>>>>>> Stashed changes
	public String getId_region(){
		return this.id_region;
	}

	public String getNom_region(){
		return this.nom_region;
	}

<<<<<<< Updated upstream
=======
	public String getId_province(){
		return this.id_province;
	}

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
				resultSet.getString("nom_region")
=======
				resultSet.getString("nom_region"),
				resultSet.getString("id_province")
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		return regions;
	}

	public Region getById(String id_region) throws Exception {
=======

		return regions;
	}

	public Region getById(int id) throws Exception {
>>>>>>> Stashed changes
		Region region = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Region WHERE id_region = ?";
			statement = connection.prepareStatement(query);
<<<<<<< Updated upstream
			statement.setString(1,id_region);
=======
			statement.setInt(1,id);
>>>>>>> Stashed changes
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				region = new Region(
				resultSet.getString("id_region"),
<<<<<<< Updated upstream
				resultSet.getString("nom_region")
=======
				resultSet.getString("nom_region"),
				resultSet.getString("id_province")
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
            String query = "INSERT INTO Region (id_region,nom_region) VALUES (generate_id_region(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_region());
=======
            String query = "INSERT INTO Region (id_region,nom_region,id_province) VALUES (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_region());
            statement.setString(2, getNom_region());
            statement.setString(3, getId_province());
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
	public void update()throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "UPDATE Region SET nom_region = ? ,id_province = ?  WHERE id_region = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_region());
            statement.setString(2, getId_province());
            statement.setString(3, getId_region());
            statement.executeUpdate();
            System.out.println("Donnees Region mise a jour avec succes");
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

	public void delete(String id_region)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Region WHERE id_region = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_region);
            statement.executeUpdate();
            System.out.println("Donnees Region supprimee avec succes");
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
>>>>>>> Stashed changes

}
