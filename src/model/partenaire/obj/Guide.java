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

public class Guide{
	String id_guide;
	String nom_guide;
	String biographie;
	String disponiblite;
	Date date_insertion;
	String id_partenaire;

	public Guide() throws Exception {
	}

	public Guide(String id_guide, String nom_guide, String biographie, String disponiblite, Date date_insertion, String id_partenaire) throws Exception {
		this.id_guide = id_guide;
		this.nom_guide = nom_guide;
		this.biographie = biographie;
		this.disponiblite = disponiblite;
		this.date_insertion = date_insertion;
		this.id_partenaire = id_partenaire;
	}

	public void setId_guide(String newId_guide) throws Exception {
		this.id_guide = newId_guide;
	}

	public void setNom_guide(String newNom_guide) throws Exception {
		this.nom_guide = newNom_guide;
	}

	public void setBiographie(String newBiographie) throws Exception {
		this.biographie = newBiographie;
	}

	public void setDisponiblite(String newDisponiblite) throws Exception {
		this.disponiblite = newDisponiblite;
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public void setId_partenaire(String newId_partenaire) throws Exception {
		this.id_partenaire = newId_partenaire;
	}

	public String getId_guide(){
		return this.id_guide;
	}

	public String getNom_guide(){
		return this.nom_guide;
	}

	public String getBiographie(){
		return this.biographie;
	}

	public String getDisponiblite(){
		return this.disponiblite;
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}

	public String getId_partenaire(){
		return this.id_partenaire;
	}

	public List<Guide> getAll() throws Exception {
		List<Guide> guides = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Guide";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Guide obj = new Guide(
				resultSet.getString("id_guide"),
				resultSet.getString("nom_guide"),
				resultSet.getString("biographie"),
				resultSet.getString("disponiblite"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("id_partenaire")
				);
				guides.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Guides: " + e.getMessage());
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

		if (guides.isEmpty()) {
			throw new Exception("No Guides found");
		}

		return guides;
	}

	public Guide getById(String id_guide) throws Exception {
		Guide guide = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Guide WHERE id_guide = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_guide);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				guide = new Guide(
				resultSet.getString("id_guide"),
				resultSet.getString("nom_guide"),
				resultSet.getString("biographie"),
				resultSet.getString("disponiblite"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("id_partenaire")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Guides: " + e.getMessage());
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

		return guide;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Guide (id_guide,nom_guide,biographie,disponiblite,date_insertion,id_partenaire) VALUES (generate_id_guide(),?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_guide());
            statement.setString(2, getBiographie());
            statement.setObject(3, getDisponiblite(),Types.OTHER);
            statement.setDate(4, getDate_insertion());
            statement.setString(5, getId_partenaire());
            statement.executeUpdate();
            System.out.println("Donnees Guide inserer avec succes");
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
            String query = "UPDATE Guide SET nom_guide = ? ,biographie = ? ,disponiblite = ? ,date_insertion = ? ,id_partenaire = ?  WHERE id_guide = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_guide());
            statement.setString(2, getBiographie());
            statement.setObject(3, getDisponiblite());
            statement.setDate(4, getDate_insertion());
            statement.setString(5, getId_partenaire());
            statement.setString(6, getId_guide());
            statement.executeUpdate();
            System.out.println("Donnees Guide mise a jour avec succes");
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

	public void delete(String id_guide)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Guide WHERE id_guide = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_guide);
            statement.executeUpdate();
            System.out.println("Donnees Guide supprimee avec succes");
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
