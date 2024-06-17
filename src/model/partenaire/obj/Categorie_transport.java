package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Categorie_transport{
	String id_categorie_transport;
	String libelle;

	public Categorie_transport() throws Exception {
	}

	public Categorie_transport(String id_categorie_transport, String libelle) throws Exception {
		this.id_categorie_transport = id_categorie_transport;
		this.libelle = libelle;
	}

	public void setId_categorie_transport(String newId_categorie_transport) throws Exception {
		this.id_categorie_transport = newId_categorie_transport;
	}

	public void setLibelle(String newLibelle) throws Exception {
		this.libelle = newLibelle;
	}

	public String getId_categorie_transport(){
		return this.id_categorie_transport;
	}

	public String getLibelle(){
		return this.libelle;
	}

	public List<Categorie_transport> getAll() throws Exception {
		List<Categorie_transport> categorie_transports = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_transport";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Categorie_transport obj = new Categorie_transport(
				resultSet.getString("id_categorie_transport"),
				resultSet.getString("libelle")
				);
				categorie_transports.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_transports: " + e.getMessage());
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

		if (categorie_transports.isEmpty()) {
			throw new Exception("No Categorie_transports found");
		}

		return categorie_transports;
	}

	public Categorie_transport getById(String id_categorie_transport) throws Exception {
		Categorie_transport categorie_transport = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_transport WHERE id_categorie_transport = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_categorie_transport);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				categorie_transport = new Categorie_transport(
				resultSet.getString("id_categorie_transport"),
				resultSet.getString("libelle")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_transports: " + e.getMessage());
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


		return categorie_transport;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Categorie_transport (id_categorie_transport,libelle) VALUES (generate_id_categorie_transport(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_transport inserer avec succes");
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
            String query = "UPDATE Categorie_transport SET libelle = ?  WHERE id_categorie_transport = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.setString(2, getId_categorie_transport());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_transport mise a jour avec succes");
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

	public void delete(String id_categorie_transport)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Categorie_transport WHERE id_categorie_transport = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_categorie_transport);
            statement.executeUpdate();
            System.out.println("Donnees Categorie_transport supprimee avec succes");
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
