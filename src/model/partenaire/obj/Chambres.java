package obj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< Updated upstream
import java.sql.Types;
=======
>>>>>>> Stashed changes
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Chambres{
	String id_chambre;
<<<<<<< Updated upstream
	String id_hotel;
	String id_categorie_chambre;
	int capacite;
	double prix;
	String status;
	Date date_insertion;

	public Chambres() throws Exception {
	}

	public Chambres(String id_chambre, String id_hotel, String id_categorie_chambre, int capacite, double prix, String status, Date date_insertion) throws Exception {
		this.id_chambre = id_chambre;
		this.id_hotel = id_hotel;
		this.id_categorie_chambre = id_categorie_chambre;
		this.capacite = capacite;
		this.prix = prix;
		this.status = status;
		this.date_insertion = date_insertion;
	}

=======
	int capacite;
	double tarif;
	int status;
	Date date_insertion;
	String id_categorie_chambre;
	String id_hotel;
	
	public Chambres() throws Exception {
	}

	public Chambres(String id_chambre, int capacite, double tarif, int status, Date date_insertion,
			String id_categorie_chambre, String id_hotel) {
		this.id_chambre = id_chambre;
		this.capacite = capacite;
		this.tarif = tarif;
		this.status = status;
		this.date_insertion = date_insertion;
		this.id_categorie_chambre = id_categorie_chambre;
		this.id_hotel = id_hotel;
	}


>>>>>>> Stashed changes
	public void setId_chambre(String newId_chambre) throws Exception {
		this.id_chambre = newId_chambre;
	}

	public void setId_hotel(String newId_hotel) throws Exception {
		this.id_hotel = newId_hotel;
	}

	public void setId_categorie_chambre(String newId_categorie_chambre) throws Exception {
		this.id_categorie_chambre = newId_categorie_chambre;
	}

	public void setCapacite(int newCapacite) throws Exception {
		this.capacite = newCapacite;
	}

<<<<<<< Updated upstream
	public void setPrix(double newPrix) throws Exception {
		this.prix = newPrix;
	}

	public void setStatus(String newStatus) throws Exception {
=======
	public void setTarif(double newtarif) throws Exception {
		this.tarif = newtarif;
	}

	public void setStatus(int newStatus) throws Exception {
>>>>>>> Stashed changes
		this.status = newStatus;
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public String getId_chambre(){
		return this.id_chambre;
	}

	public String getId_hotel(){
		return this.id_hotel;
	}

	public String getId_categorie_chambre(){
		return this.id_categorie_chambre;
	}

	public int getCapacite(){
		return this.capacite;
	}

<<<<<<< Updated upstream
	public double getPrix(){
		return this.prix;
	}

	public String getStatus(){
=======
	public double getTarif(){
		return this.tarif;
	}

	public int getStatus(){
>>>>>>> Stashed changes
		return this.status;
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}

	public List<Chambres> getAll() throws Exception {
		List<Chambres> chambress = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
<<<<<<< Updated upstream
			String query = "SELECT * FROM Chambres";
=======
			String query = "SELECT * FROM Chambre";
>>>>>>> Stashed changes
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Chambres obj = new Chambres(
				resultSet.getString("id_chambre"),
<<<<<<< Updated upstream
				resultSet.getString("id_hotel"),
				resultSet.getString("id_categorie_chambre"),
				resultSet.getInt("capacite"),
				resultSet.getDouble("prix"),
				resultSet.getString("status"),
				resultSet.getDate("date_insertion")
=======
				resultSet.getInt("capacite"),
				resultSet.getDouble("tarif"),
				resultSet.getInt("status"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("id_categorie_chambre"),
				resultSet.getString("id_hotel")
>>>>>>> Stashed changes
				);
				chambress.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Chambress: " + e.getMessage());
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

		if (chambress.isEmpty()) {
			throw new Exception("No Chambress found");
		}

		return chambress;
	}

	public Chambres getById(String id_chambre) throws Exception {
		Chambres chambres = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
<<<<<<< Updated upstream
			String query = "SELECT * FROM Chambres WHERE id_chambre = ?";
=======
			String query = "SELECT * FROM Chambre WHERE id_chambre = ?";
>>>>>>> Stashed changes
			statement = connection.prepareStatement(query);
			statement.setString(1,id_chambre);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				chambres = new Chambres(
<<<<<<< Updated upstream
				resultSet.getString("id_chambre"),
				resultSet.getString("id_hotel"),
				resultSet.getString("id_categorie_chambre"),
				resultSet.getInt("capacite"),
				resultSet.getDouble("prix"),
				resultSet.getString("status"),
				resultSet.getDate("date_insertion")
				);
=======
					resultSet.getString("id_chambre"),
					resultSet.getInt("capacite"),
					resultSet.getDouble("tarif"),
					resultSet.getInt("status"),
					resultSet.getDate("date_insertion"),
					resultSet.getString("id_categorie_chambre"),
					resultSet.getString("id_hotel")
					);
>>>>>>> Stashed changes
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Chambress: " + e.getMessage());
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
		return chambres;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "INSERT INTO Chambres (id_chambre,id_hotel,id_categorie_chambre,capacite,prix,status,date_insertion) VALUES (generate_id_chambre(),?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_hotel());
            statement.setString(2, getId_categorie_chambre());
            statement.setInt(3, getCapacite());
            statement.setDouble(4, getPrix());
            statement.setObject(5, getStatus(),Types.OTHER);
            statement.setDate(6, getDate_insertion());
            statement.executeUpdate();
            System.out.println("Donnees Chambres inserer avec succes");
=======
            String query = "INSERT INTO Chambre (id_chambre,capacite,tarif,status,date_insertion,id_categorie_chambre,id_hotel) VALUES (?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_chambre());
            statement.setInt(2, getCapacite());
            statement.setDouble(3, getTarif());
            statement.setInt(4, getStatus());
            statement.setDate(5, getDate_insertion());
            statement.setString(6, getId_categorie_chambre());
            statement.setString(7, getId_hotel());
            statement.executeUpdate();
            System.out.println("Donnees Chambre inserer avec succes");
>>>>>>> Stashed changes
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
            String query = "UPDATE Chambres SET id_hotel = ? ,id_categorie_chambre = ? ,capacite = ? ,prix = ? ,status = ? ,date_insertion = ?  WHERE id_chambre = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_hotel());
            statement.setString(2, getId_categorie_chambre());
            statement.setInt(3, getCapacite());
            statement.setDouble(4, getPrix());
            statement.setObject(5, getStatus(),Types.OTHER);
            statement.setDate(6, getDate_insertion());
            statement.setString(7, getId_chambre());
            statement.executeUpdate();
            System.out.println("Donnees Chambres mise a jour avec succes");
=======
            String query = "UPDATE Chambre SET capacite = ? ,tarif = ? ,status = ? ,date_insertion = ? ,id_categorie_chambre = ? ,id_hotel = ?  WHERE id_chambre = ? ";
            statement = connection.prepareStatement(query);
            statement.setInt(1, getCapacite());
            statement.setDouble(2, getTarif());
            statement.setInt(3, getStatus());
            statement.setDate(4, getDate_insertion());
            statement.setString(5, getId_categorie_chambre());
            statement.setString(6, getId_hotel());
            statement.setString(7, getId_chambre());
            statement.executeUpdate();
            System.out.println("Donnees Chambre mise a jour avec succes");
>>>>>>> Stashed changes
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
=======

>>>>>>> Stashed changes
	public void delete(String id_chambre)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "DELETE  FROM Chambres WHERE id_chambre = ? ";
=======
            String query = "DELETE  FROM Chambre WHERE id_chambre = ? ";
>>>>>>> Stashed changes
            statement = connection.prepareStatement(query);
            statement.setString(1, id_chambre);
            statement.executeUpdate();
            System.out.println("Donnees Chambres supprimee avec succes");
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
=======
	
	public static List<Chambres> searchCritere(String nom,String status,double tarifMin,double tarifMax, Date dateDebut, Date dateFin) throws Exception {
        List<Chambres> chambres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
			connection = Connexion.getConnection();
            
            // Construction de la requête en fonction des critères fournis
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Chambre WHERE 1=1");

			if (nom != null && !nom.isEmpty()) {
                queryBuilder.append(" AND capacite LIKE ?");
            }

			if (status != null && !status.isEmpty()) {
                queryBuilder.append(" AND status LIKE ?");
            }

			if (tarifMin != 0) {
                queryBuilder.append(" AND tarif >= ?");
            }
			if (tarifMax != 0) {
                queryBuilder.append(" AND tarif <= ?");
            }

            if (dateDebut != null) {
                queryBuilder.append(" AND date_insertion >= ?");
            }

            if (dateFin != null) {
                queryBuilder.append(" AND date_insertion <= ?");
            }

            statement = connection.prepareStatement(queryBuilder.toString());

            // Remplissage des paramètres en fonction des critères
            int parameterIndex = 1;
            if (nom != null && !nom.isEmpty()) {
                statement.setString(parameterIndex++, "%" + nom + "%");
            }
			if (status != null && !status.isEmpty()) {
                statement.setString(parameterIndex++, "%" + status + "%");
            }
			
			if (tarifMin != 0) {
                statement.setDouble(parameterIndex++,tarifMin);
            }
			
			if (tarifMax != 0) {
                statement.setDouble(parameterIndex++,tarifMax);
            }
			
            if (dateDebut != null) {
                statement.setDate(parameterIndex++, dateDebut);
            }

            if (dateFin != null) {
                statement.setDate(parameterIndex++, dateFin);
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Chambres obj = new Chambres(
					resultSet.getString("id_chambre"),
					resultSet.getInt("capacite"),
					resultSet.getDouble("tarif"),
					resultSet.getInt("status"),
					resultSet.getDate("date_insertion"),
					resultSet.getString("id_categorie_chambre"),
					resultSet.getString("id_hotel")
					);
                chambres.add(obj);
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la recherche des Chambres : " + e.getMessage());
        } finally{
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

        return chambres;
    }
>>>>>>> Stashed changes

}
