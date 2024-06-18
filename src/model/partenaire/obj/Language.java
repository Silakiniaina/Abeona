package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Language{
	String id_language;
<<<<<<< Updated upstream
	String nom_language;
=======
	String libelle;
>>>>>>> Stashed changes

	public Language() throws Exception {
	}

<<<<<<< Updated upstream
	public Language(String id_language, String nom_language) throws Exception {
		this.id_language = id_language;
		this.nom_language = nom_language;
=======
	public Language(String id_language, String libelle) throws Exception {
		this.id_language = id_language;
		this.libelle = libelle;
>>>>>>> Stashed changes
	}

	public void setId_language(String newId_language) throws Exception {
		this.id_language = newId_language;
	}

<<<<<<< Updated upstream
	public void setNom_language(String newNom_language) throws Exception {
		this.nom_language = newNom_language;
=======
	public void setlibelle(String newlibelle) throws Exception {
		this.libelle = newlibelle;
>>>>>>> Stashed changes
	}

	public String getId_language(){
		return this.id_language;
	}

<<<<<<< Updated upstream
	public String getNom_language(){
		return this.nom_language;
=======
	public String getlibelle(){
		return this.libelle;
>>>>>>> Stashed changes
	}

	public List<Language> getAll() throws Exception {
		List<Language> languages = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Language";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Language obj = new Language(
				resultSet.getString("id_language"),
<<<<<<< Updated upstream
				resultSet.getString("nom_language")
=======
				resultSet.getString("libelle")
>>>>>>> Stashed changes
				);
				languages.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Languages: " + e.getMessage());
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

		if (languages.isEmpty()) {
			throw new Exception("No Languages found");
		}

		return languages;
	}

	public Language getById(String id_language) throws Exception {
		Language language = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Language WHERE id_language = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_language);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				language = new Language(
				resultSet.getString("id_language"),
<<<<<<< Updated upstream
				resultSet.getString("nom_language")
=======
				resultSet.getString("libelle")
>>>>>>> Stashed changes
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Languages: " + e.getMessage());
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

		return language;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "INSERT INTO Language (id_language,nom_language) VALUES (generate_id_language(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_language());
=======
            String query = "INSERT INTO Language (id_language,libelle) VALUES (generate_id_language(),?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getlibelle());
>>>>>>> Stashed changes
            statement.executeUpdate();
            System.out.println("Donnees Language inserer avec succes");
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
            String query = "UPDATE Language SET nom_language = ?  WHERE id_language = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_language());
=======
            String query = "UPDATE Language SET libelle = ?  WHERE id_language = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getlibelle());
>>>>>>> Stashed changes
            statement.setString(2, getId_language());
            statement.executeUpdate();
            System.out.println("Donnees Language mise a jour avec succes");
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

	public void delete(String id_language)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Language WHERE id_language = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_language);
            statement.executeUpdate();
            System.out.println("Donnees Language supprimee avec succes");
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
