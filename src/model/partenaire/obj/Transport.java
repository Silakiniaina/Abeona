package obj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Transport{
	String id_transport;
<<<<<<< Updated upstream
	String id_partenaire;
	String nom_transport;
	String id_categorie_transport;
	double tarif_par_jour;
	Date date_insertion;
=======
	String nom_transport;
	String description;
	double tarif;
	Date date_insertion;
	String id_categorie_transport;
	String id_partenaire;
>>>>>>> Stashed changes

	public Transport() throws Exception {
	}

<<<<<<< Updated upstream
	public Transport(String id_transport, String id_partenaire, String nom_transport, String id_categorie_transport, double tarif_par_jour, Date date_insertion) throws Exception {
		this.id_transport = id_transport;
		this.id_partenaire = id_partenaire;
		this.nom_transport = nom_transport;
		this.id_categorie_transport = id_categorie_transport;
		this.tarif_par_jour = tarif_par_jour;
		this.date_insertion = date_insertion;
=======
	public Transport(String id_transport, String nom_transport, String description, double tarif, Date date_insertion, String id_categorie_transport, String id_partenaire) throws Exception {
		this.id_transport = id_transport;
		this.nom_transport = nom_transport;
		this.description = description;
		this.tarif = tarif;
		this.date_insertion = date_insertion;
		this.id_categorie_transport = id_categorie_transport;
		this.id_partenaire = id_partenaire;
>>>>>>> Stashed changes
	}

	public void setId_transport(String newId_transport) throws Exception {
		this.id_transport = newId_transport;
	}

<<<<<<< Updated upstream
	public void setId_partenaire(String newId_partenaire) throws Exception {
		this.id_partenaire = newId_partenaire;
	}

=======
>>>>>>> Stashed changes
	public void setNom_transport(String newNom_transport) throws Exception {
		this.nom_transport = newNom_transport;
	}

<<<<<<< Updated upstream
	public void setId_categorie_transport(String newId_categorie_transport) throws Exception {
		this.id_categorie_transport = newId_categorie_transport;
	}

	public void setTarif_par_jour(double newTarif_par_jour) throws Exception {
		this.tarif_par_jour = newTarif_par_jour;
=======
	public void setDescription(String newDescription) throws Exception {
		this.description = newDescription;
	}

	public void setTarif(double newTarif) throws Exception {
		this.tarif = newTarif;
>>>>>>> Stashed changes
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

<<<<<<< Updated upstream
	public String getId_transport(){
		return this.id_transport;
	}

	public String getId_partenaire(){
		return this.id_partenaire;
=======
	public void setId_categorie_transport(String newId_categorie_transport) throws Exception {
		this.id_categorie_transport = newId_categorie_transport;
	}

	public void setId_partenaire(String newId_partenaire) throws Exception {
		this.id_partenaire = newId_partenaire;
	}

	public String getId_transport(){
		return this.id_transport;
>>>>>>> Stashed changes
	}

	public String getNom_transport(){
		return this.nom_transport;
	}

<<<<<<< Updated upstream
	public String getId_categorie_transport(){
		return this.id_categorie_transport;
	}

	public double getTarif_par_jour(){
		return this.tarif_par_jour;
=======
	public String getDescription(){
		return this.description;
	}

	public double getTarif(){
		return this.tarif;
>>>>>>> Stashed changes
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}

<<<<<<< Updated upstream
=======
	public String getId_categorie_transport(){
		return this.id_categorie_transport;
	}

	public String getId_partenaire(){
		return this.id_partenaire;
	}

>>>>>>> Stashed changes
	public List<Transport> getAll() throws Exception {
		List<Transport> transports = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Transport";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Transport obj = new Transport(
				resultSet.getString("id_transport"),
<<<<<<< Updated upstream
				resultSet.getString("id_partenaire"),
				resultSet.getString("nom_transport"),
				resultSet.getString("id_categorie_transport"),
				resultSet.getDouble("tarif_par_jour"),
				resultSet.getDate("date_insertion")
=======
				resultSet.getString("nom_transport"),
				resultSet.getString("description"),
				resultSet.getDouble("tarif"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("id_categorie_transport"),
				resultSet.getString("id_partenaire")
>>>>>>> Stashed changes
				);
				transports.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Transports: " + e.getMessage());
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
		if (transports.isEmpty()) {
			throw new Exception("No Transports found");
		}

		return transports;
	}

	public Transport getById(String id_transport) throws Exception {
=======
		return transports;
	}

	public Transport getById(int id) throws Exception {
>>>>>>> Stashed changes
		Transport transport = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Transport WHERE id_transport = ?";
			statement = connection.prepareStatement(query);
<<<<<<< Updated upstream
			statement.setString(1,id_transport);
=======
			statement.setInt(1,id);
>>>>>>> Stashed changes
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				transport = new Transport(
				resultSet.getString("id_transport"),
<<<<<<< Updated upstream
				resultSet.getString("id_partenaire"),
				resultSet.getString("nom_transport"),
				resultSet.getString("id_categorie_transport"),
				resultSet.getDouble("tarif_par_jour"),
				resultSet.getDate("date_insertion")
=======
				resultSet.getString("nom_transport"),
				resultSet.getString("description"),
				resultSet.getDouble("tarif"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("id_categorie_transport"),
				resultSet.getString("id_partenaire")
>>>>>>> Stashed changes
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Transports: " + e.getMessage());
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

		return transport;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "INSERT INTO Transport (id_transport,id_partenaire,nom_transport,id_categorie_transport,tarif_par_jour,date_insertion) VALUES (generate_id_transport(),?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_partenaire());
            statement.setString(2, getNom_transport());
            statement.setString(3, getId_categorie_transport());
            statement.setDouble(4, getTarif_par_jour());
            statement.setDate(5, getDate_insertion());
=======
            String query = "INSERT INTO Transport (id_transport,nom_transport,description,tarif,date_insertion,id_categorie_transport,id_partenaire) VALUES (?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_transport());
            statement.setString(2, getNom_transport());
            statement.setString(3, getDescription());
            statement.setDouble(4, getTarif());
            statement.setDate(5, getDate_insertion());
            statement.setString(6, getId_categorie_transport());
            statement.setString(7, getId_partenaire());
>>>>>>> Stashed changes
            statement.executeUpdate();
            System.out.println("Donnees Transport inserer avec succes");
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
            String query = "UPDATE Transport SET id_partenaire = ? ,nom_transport = ? ,id_categorie_transport = ? ,tarif_par_jour = ? ,date_insertion = ?  WHERE id_transport = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_partenaire());
            statement.setString(2, getNom_transport());
            statement.setString(3, getId_categorie_transport());
            statement.setDouble(4, getTarif_par_jour());
            statement.setDate(5, getDate_insertion());
            statement.setString(6, getId_transport());
=======
            String query = "UPDATE Transport SET nom_transport = ? ,description = ? ,tarif = ? ,date_insertion = ? ,id_categorie_transport = ? ,id_partenaire = ?  WHERE id_transport = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_transport());
            statement.setString(2, getDescription());
            statement.setDouble(3, getTarif());
            statement.setDate(4, getDate_insertion());
            statement.setString(5, getId_categorie_transport());
            statement.setString(6, getId_partenaire());
            statement.setString(7, getId_transport());
>>>>>>> Stashed changes
            statement.executeUpdate();
            System.out.println("Donnees Transport mise a jour avec succes");
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

	public void delete(String id_transport)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Transport WHERE id_transport = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_transport);
            statement.executeUpdate();
            System.out.println("Donnees Transport supprimee avec succes");
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
