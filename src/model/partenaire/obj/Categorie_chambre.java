package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Categorie_chambre{
	String id_categorie_chambre;
	String libelle;

	public Categorie_chambre() throws Exception {
	}

	public Categorie_chambre(String id_categorie_chambre, String libelle) throws Exception {
		this.id_categorie_chambre = id_categorie_chambre;
		this.libelle = libelle;
	}

	public void setId_categorie_chambre(String newId_categorie_chambre) throws Exception {
		this.id_categorie_chambre = newId_categorie_chambre;
	}

	public void setLibelle(String newLibelle) throws Exception {
		this.libelle = newLibelle;
	}

	public String getId_categorie_chambre(){
		return this.id_categorie_chambre;
	}

	public String getLibelle(){
		return this.libelle;
	}

	public List<Categorie_chambre> getAll() throws Exception {
		List<Categorie_chambre> categorie_chambres = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_chambre";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Categorie_chambre obj = new Categorie_chambre(
				resultSet.getString("id_categorie_chambre"),
				resultSet.getString("libelle")
				);
				categorie_chambres.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_chambres: " + e.getMessage());
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

		if (categorie_chambres.isEmpty()) {
			throw new Exception("No Categorie_chambres found");
		}

		return categorie_chambres;
	}

	public Categorie_chambre getById(String id_categorie_chambre) throws Exception {
		Categorie_chambre categorie_chambre = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Categorie_chambre WHERE id_categorie_chambre = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_categorie_chambre);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				categorie_chambre = new Categorie_chambre(
				resultSet.getString("id_categorie_chambre"),
				resultSet.getString("libelle")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Categorie_chambres: " + e.getMessage());
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

		return categorie_chambre;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Categorie_chambre (id_categorie_chambre,libelle) VALUES (generate_id_categorie_chambre(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_chambre inserer avec succes");
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
            String query = "UPDATE Categorie_chambre SET libelle = ?  WHERE id_categorie_chambre = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getLibelle());
            statement.setString(2, getId_categorie_chambre());
            statement.executeUpdate();
            System.out.println("Donnees Categorie_chambre mise a jour avec succes");
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

	public void delete(String id_categorie_chambre)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Categorie_chambre WHERE id_categorie_chambre = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_categorie_chambre);
            statement.executeUpdate();
            System.out.println("Donnees Categorie_chambre supprimee avec succes");
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
