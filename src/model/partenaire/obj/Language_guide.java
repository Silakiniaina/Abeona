package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Language_guide{
<<<<<<< Updated upstream
	String id_language;
	String id_guide;
=======
	String id_guide;
    String id_language;
	
>>>>>>> Stashed changes

	public Language_guide() throws Exception {
	}

<<<<<<< Updated upstream
	public Language_guide(String id_language, String id_guide) throws Exception {
		this.id_language = id_language;
		this.id_guide = id_guide;
=======
	public Language_guide(String id_guide, String id_language) throws Exception {
		this.id_guide = id_guide;
        this.id_language = id_language;
>>>>>>> Stashed changes
	}

	public void setId_language(String newId_language) throws Exception {
		this.id_language = newId_language;
	}

	public void setId_guide(String newId_guide) throws Exception {
		this.id_guide = newId_guide;
	}

	public String getId_language(){
		return this.id_language;
	}

	public String getId_guide(){
		return this.id_guide;
	}

	public List<Language_guide> getAll() throws Exception {
		List<Language_guide> language_guides = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Language_guide";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Language_guide obj = new Language_guide(
<<<<<<< Updated upstream
				resultSet.getString("id_language"),
				resultSet.getString("id_guide")
=======
				resultSet.getString("id_guide"),
                resultSet.getString("id_language")
>>>>>>> Stashed changes
				);
				language_guides.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Language_guides: " + e.getMessage());
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
		if (language_guides.isEmpty()) {
			throw new Exception("No Language_guides found");
		}

=======
>>>>>>> Stashed changes
		return language_guides;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "INSERT INTO Language_guide (id_language,id_guide) VALUES (?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_language());
            statement.setString(2, getId_guide());
=======
            String query = "INSERT INTO Language_guide (id_guide,id_language) VALUES (?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_guide());
            statement.setString(2, getId_language());
>>>>>>> Stashed changes
            statement.executeUpdate();
            System.out.println("Donnees Language_guide inserer avec succes");
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
            String query = "UPDATE Language_guide SET id_guide = ?  WHERE id_language = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_guide());
            statement.setString(2, getId_language());
            statement.executeUpdate();
            System.out.println("Donnees Language_guide mise a jour avec succes");
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
            String query = "DELETE  FROM Language_guide WHERE id_language = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_language);
            statement.executeUpdate();
            System.out.println("Donnees Language_guide supprimee avec succes");
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
