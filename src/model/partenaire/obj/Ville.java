package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Ville{
	String id_ville;
<<<<<<< Updated upstream
	String id_region;
	String nom_ville;
=======
	String nom_ville;
	String id_region;
>>>>>>> Stashed changes

	public Ville() throws Exception {
	}

<<<<<<< Updated upstream
	public Ville(String id_ville, String id_region, String nom_ville) throws Exception {
		this.id_ville = id_ville;
		this.id_region = id_region;
		this.nom_ville = nom_ville;
=======
	public Ville(String id_ville, String nom_ville, String id_region) throws Exception {
		this.id_ville = id_ville;
		this.nom_ville = nom_ville;
		this.id_region = id_region;
>>>>>>> Stashed changes
	}

	public void setId_ville(String newId_ville) throws Exception {
		this.id_ville = newId_ville;
	}

<<<<<<< Updated upstream
	public void setId_region(String newId_region) throws Exception {
		this.id_region = newId_region;
	}

=======
>>>>>>> Stashed changes
	public void setNom_ville(String newNom_ville) throws Exception {
		this.nom_ville = newNom_ville;
	}

<<<<<<< Updated upstream
=======
	public void setId_region(String newId_region) throws Exception {
		this.id_region = newId_region;
	}

>>>>>>> Stashed changes
	public String getId_ville(){
		return this.id_ville;
	}

<<<<<<< Updated upstream
	public String getId_region(){
		return this.id_region;
	}

=======
>>>>>>> Stashed changes
	public String getNom_ville(){
		return this.nom_ville;
	}

<<<<<<< Updated upstream
=======
	public String getId_region(){
		return this.id_region;
	}

>>>>>>> Stashed changes
	public List<Ville> getAll() throws Exception {
		List<Ville> villes = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Ville";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Ville obj = new Ville(
				resultSet.getString("id_ville"),
<<<<<<< Updated upstream
				resultSet.getString("id_region"),
				resultSet.getString("nom_ville")
=======
				resultSet.getString("nom_ville"),
				resultSet.getString("id_region")
>>>>>>> Stashed changes
				);
				villes.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Villes: " + e.getMessage());
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
		if (villes.isEmpty()) {
			throw new Exception("No Villes found");
		}

		return villes;
	}

	public Ville getById(String id_ville) throws Exception {
=======
		return villes;
	}

	public Ville getById(int id) throws Exception {
>>>>>>> Stashed changes
		Ville ville = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Ville WHERE id_ville = ?";
			statement = connection.prepareStatement(query);
<<<<<<< Updated upstream
			statement.setString(1,id_ville);
=======
			statement.setInt(1,id);
>>>>>>> Stashed changes
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				ville = new Ville(
				resultSet.getString("id_ville"),
<<<<<<< Updated upstream
				resultSet.getString("id_region"),
				resultSet.getString("nom_ville")
=======
				resultSet.getString("nom_ville"),
				resultSet.getString("id_region")
>>>>>>> Stashed changes
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Villes: " + e.getMessage());
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

		return ville;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "INSERT INTO Ville (id_ville,id_region,nom_ville) VALUES (generate_id_ville(),?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_region());
            statement.setString(2, getNom_ville());
=======
            String query = "INSERT INTO Ville (id_ville,nom_ville,id_region) VALUES (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_ville());
            statement.setString(2, getNom_ville());
            statement.setString(3, getId_region());
>>>>>>> Stashed changes
            statement.executeUpdate();
            System.out.println("Donnees Ville inserer avec succes");
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
<<<<<<< Updated upstream
            String query = "UPDATE Ville SET id_region = ? ,nom_ville = ?  WHERE id_ville = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_region());
            statement.setString(2, getNom_ville());
=======
            String query = "UPDATE Ville SET nom_ville = ? ,id_region = ?  WHERE id_ville = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_ville());
            statement.setString(2, getId_region());
>>>>>>> Stashed changes
            statement.setString(3, getId_ville());
            statement.executeUpdate();
            System.out.println("Donnees Ville mise a jour avec succes");
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

<<<<<<< Updated upstream
	public void delete(int id_ville)throws Exception{
=======
	public void delete(String id_ville)throws Exception{
>>>>>>> Stashed changes
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Ville WHERE id_ville = ? ";
            statement = connection.prepareStatement(query);
<<<<<<< Updated upstream
            statement.setInt(1, id_ville);
=======
            statement.setString(1, id_ville);
>>>>>>> Stashed changes
            statement.executeUpdate();
            System.out.println("Donnees Ville supprimee avec succes");
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
