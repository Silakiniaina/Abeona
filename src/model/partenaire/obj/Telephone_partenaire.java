package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Telephone_partenaire{
	String id_telephone;
	String id_partenaire;
	String numero_telephone;

	public Telephone_partenaire() throws Exception {
	}

	public Telephone_partenaire(String id_telephone, String id_partenaire, String numero_telephone) throws Exception {
		this.id_telephone = id_telephone;
		this.id_partenaire = id_partenaire;
		this.numero_telephone = numero_telephone;
	}

	public void setId_telephone(String newId_telephone) throws Exception {
		this.id_telephone = newId_telephone;
	}

	public void setId_partenaire(String newId_partenaire) throws Exception {
		this.id_partenaire = newId_partenaire;
	}

	public void setNumero_telephone(String newNumero_telephone) throws Exception {
		this.numero_telephone = newNumero_telephone;
	}

	public String getId_telephone(){
		return this.id_telephone;
	}

	public String getId_partenaire(){
		return this.id_partenaire;
	}

	public String getNumero_telephone(){
		return this.numero_telephone;
	}

	public List<Telephone_partenaire> getAll() throws Exception {
		List<Telephone_partenaire> telephone_partenaires = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Telephone_partenaire";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Telephone_partenaire obj = new Telephone_partenaire(
				resultSet.getString("id_telephone"),
				resultSet.getString("id_partenaire"),
				resultSet.getString("numero_telephone")
				);
				telephone_partenaires.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Telephone_partenaires: " + e.getMessage());
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

		if (telephone_partenaires.isEmpty()) {
			throw new Exception("No Telephone_partenaires found");
		}

		return telephone_partenaires;
	}

	public Telephone_partenaire getById(String id_telephone) throws Exception {
		Telephone_partenaire telephone_partenaire = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Telephone_partenaire WHERE id_telephone = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_telephone);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				telephone_partenaire = new Telephone_partenaire(
				resultSet.getString("id_telephone"),
				resultSet.getString("id_partenaire"),
				resultSet.getString("numero_telephone")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Telephone_partenaires: " + e.getMessage());
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

		return telephone_partenaire;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "INSERT INTO Telephone_partenaire (id_telephone,id_partenaire,numero_telephone) VALUES (generate_id_telephone(),?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_partenaire());
            statement.setString(2, getNumero_telephone());
            statement.executeUpdate();
            System.out.println("Donnees Telephone_partenaire inserer avec succes");
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
            String query = "UPDATE Telephone_partenaire SET id_partenaire = ? ,numero_telephone = ?  WHERE id_telephone = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_partenaire());
            statement.setString(2, getNumero_telephone());
            statement.setString(3, getId_telephone());
            statement.executeUpdate();
            System.out.println("Donnees Telephone_partenaire mise a jour avec succes");
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

	public void delete(String id_telephone)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Telephone_partenaire WHERE id_telephone = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_telephone);
            statement.executeUpdate();
            System.out.println("Donnees Telephone_partenaire supprimee avec succes");
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
