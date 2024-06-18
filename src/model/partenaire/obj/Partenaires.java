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
import utils.security.Encryption;

public class Partenaires{
	String id_partenaire;
<<<<<<< Updated upstream
	String email_contact;
	String nom_partenaire;
=======
	String nom_partenaire;
	String email_partenaire;
>>>>>>> Stashed changes
	String mot_de_passe;
	Date date_insertion;

	public Partenaires() throws Exception {
	}

<<<<<<< Updated upstream
	public Partenaires(String id_partenaire, String email_contact, String nom_partenaire, String mot_de_passe, Date date_insertion) throws Exception {
		this.id_partenaire = id_partenaire;
		this.email_contact = email_contact;
=======
	public Partenaires(String id_partenaire,String nom_partenaire, String email_partenaire, String mot_de_passe, Date date_insertion) throws Exception {
		this.id_partenaire = id_partenaire;
		this.email_partenaire = email_partenaire;
>>>>>>> Stashed changes
		this.nom_partenaire = nom_partenaire;
		this.mot_de_passe = mot_de_passe;
		this.date_insertion = date_insertion;
	}

	public void setId_partenaire(String newId_partenaire) throws Exception {
		this.id_partenaire = newId_partenaire;
	}

<<<<<<< Updated upstream
	public void setEmail_contact(String newEmail_contact) throws Exception {
		this.email_contact = newEmail_contact;
=======
	public void setemail_partenaire(String newemail_partenaire) throws Exception {
		this.email_partenaire = newemail_partenaire;
>>>>>>> Stashed changes
	}

	public void setNom_partenaire(String newNom_partenaire) throws Exception {
		this.nom_partenaire = newNom_partenaire;
	}

	public void setMot_de_passe(String newMot_de_passe) throws Exception {
		this.mot_de_passe = newMot_de_passe;
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public String getId_partenaire(){
		return this.id_partenaire;
	}

<<<<<<< Updated upstream
	public String getEmail_contact(){
		return this.email_contact;
=======
	public String getemail_partenaire(){
		return this.email_partenaire;
>>>>>>> Stashed changes
	}

	public String getNom_partenaire(){
		return this.nom_partenaire;
	}

	public String getMot_de_passe(){
		return this.mot_de_passe;
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}

	public List<Partenaires> getAll() throws Exception {
		List<Partenaires> partenairess = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Partenaires";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Partenaires obj = new Partenaires(
				resultSet.getString("id_partenaire"),
<<<<<<< Updated upstream
				resultSet.getString("email_contact"),
				resultSet.getString("nom_partenaire"),
=======
				resultSet.getString("nom_partenaire"),
				resultSet.getString("email_partenaire"),
>>>>>>> Stashed changes
				resultSet.getString("mot_de_passe"),
				resultSet.getDate("date_insertion")
				);
				partenairess.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Partenairess: " + e.getMessage());
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
		if (partenairess.isEmpty()) {
			throw new Exception("No Partenairess found");
		}

=======
>>>>>>> Stashed changes
		return partenairess;
	}

	public Partenaires getById(String id_partenaire) throws Exception {
		Partenaires partenaires = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Partenaires WHERE id_partenaire = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_partenaire);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				partenaires = new Partenaires(
				resultSet.getString("id_partenaire"),
<<<<<<< Updated upstream
				resultSet.getString("email_contact"),
				resultSet.getString("nom_partenaire"),
=======
				resultSet.getString("nom_partenaire"),
				resultSet.getString("email_partenaire"),
>>>>>>> Stashed changes
				resultSet.getString("mot_de_passe"),
				resultSet.getDate("date_insertion")
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Partenairess: " + e.getMessage());
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


		return partenaires;
	}


	public void insert() throws Exception {
<<<<<<< Updated upstream
        
=======
>>>>>>> Stashed changes
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "INSERT INTO Partenaires (id_partenaire,email_contact,nom_partenaire,mot_de_passe,date_insertion) VALUES (generate_id_partenaire(),?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setObject(1, getEmail_contact(),Types.OTHER);
            statement.setString(2, getNom_partenaire());
=======
            String query = "INSERT INTO Partenaires (id_partenaire,nom_partenaire,email_partenaire,mot_de_passe,date_insertion) VALUES (generate_id_partenaire(),?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setObject(1, getNom_partenaire(),Types.OTHER);
            statement.setString(2, getemail_partenaire());
>>>>>>> Stashed changes
            statement.setString(3, Encryption.toSHA256(getMot_de_passe()));
            statement.setDate(4, getDate_insertion());
            statement.executeUpdate();
            System.out.println("Donnees Partenaires inserer avec succes");
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
            String query = "UPDATE Partenaires SET email_contact = ? ,nom_partenaire = ? ,mot_de_passe = ? ,date_insertion = ?  WHERE id_partenaire = ? ";
            statement = connection.prepareStatement(query);
            statement.setObject(1, getEmail_contact(),Types.OTHER);
            statement.setString(2, getNom_partenaire());
=======
            String query = "UPDATE Partenaires SET nom_partenaire = ? ,email_partenaire = ? ,mot_de_passe = ? ,date_insertion = ?  WHERE id_partenaire = ? ";
            statement = connection.prepareStatement(query);
            statement.setObject(1, getNom_partenaire(),Types.OTHER);
            statement.setString(2, getemail_partenaire());
>>>>>>> Stashed changes
            statement.setString(3, Encryption.toSHA256(getMot_de_passe()));
            statement.setDate(4, getDate_insertion());
            statement.setString(5, getId_partenaire());
            statement.executeUpdate();
            System.out.println("Donnees Partenaires mise a jour avec succes");
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

	public void delete(String id_partenaire)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Partenaires WHERE id_partenaire = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_partenaire);
            statement.executeUpdate();
            System.out.println("Donnees Partenaires supprimee avec succes");
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
