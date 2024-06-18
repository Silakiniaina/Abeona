package obj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Evenement{
	String id_evenement;
<<<<<<< Updated upstream
	String nom_evenement;
	Date date_evenement;
	Date date_insertion;
	String description;
	String id_categorie_evenement;
	String id_ville;
	String id_hotel;
=======
	String titre_evenement;
	String description;
	String lieu_evenement;
	Date date_debut_evenement;
	Date date_fin_evenement;
	String id_hotel;
	String id_ville;
	String id_categorie_evenement;
	Date date_insertion;
	
>>>>>>> Stashed changes

	public Evenement() throws Exception {
	}

<<<<<<< Updated upstream
	public Evenement(String id_evenement, String nom_evenement, Date date_evenement, Date date_insertion, String description, String id_categorie_evenement, String id_ville, String id_hotel) throws Exception {
		this.id_evenement = id_evenement;
		this.nom_evenement = nom_evenement;
		this.date_evenement = date_evenement;
		this.date_insertion = date_insertion;
		this.description = description;
		this.id_categorie_evenement = id_categorie_evenement;
		this.id_ville = id_ville;
		this.id_hotel = id_hotel;
	}

=======
	

	public Evenement(String id_evenement, String titre_evenement, String description, String lieu_evenement, Date date_debut_evenement, Date date_fin_evenement, String id_hotel, String id_ville, String id_categorie_evenement, Date date_insertion) throws Exception {
		this.id_evenement = id_evenement;
		this.titre_evenement = titre_evenement;
		this.description = description;
		this.lieu_evenement = lieu_evenement;
		this.date_debut_evenement = date_debut_evenement;
		this.date_fin_evenement = date_fin_evenement;
		this.id_hotel = id_hotel;
		this.id_ville = id_ville;
		this.id_categorie_evenement = id_categorie_evenement;
		this.date_insertion = date_insertion;
	}



>>>>>>> Stashed changes
	public void setId_evenement(String newId_evenement) throws Exception {
		this.id_evenement = newId_evenement;
	}

<<<<<<< Updated upstream
	public void setNom_evenement(String newNom_evenement) throws Exception {
		this.nom_evenement = newNom_evenement;
	}

	public void setDate_evenement(Date newDate_evenement) throws Exception {
		this.date_evenement = newDate_evenement;
=======
	public void setTitre_evenement(String newTitre_evenement) throws Exception {
		this.titre_evenement = newTitre_evenement;
	}


	public void setDate_debut_evenement(Date newDate_debut_evenement) throws Exception {
		this.date_debut_evenement = newDate_debut_evenement;
	}

	public void setDate_fin_evenement(Date newDate_fin_evenement) throws Exception {
		this.date_fin_evenement = newDate_fin_evenement;
>>>>>>> Stashed changes
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public void setDescription(String newDescription) throws Exception {
		this.description = newDescription;
	}
<<<<<<< Updated upstream
=======
	

	public void setLieu_evenement(String lieu_evenement) {
		this.lieu_evenement = lieu_evenement;
	}
>>>>>>> Stashed changes

	public void setId_categorie_evenement(String newId_categorie_evenement) throws Exception {
		this.id_categorie_evenement = newId_categorie_evenement;
	}

	public void setId_ville(String newId_ville) throws Exception {
		this.id_ville = newId_ville;
	}

	public void setId_hotel(String newId_hotel) throws Exception {
		this.id_hotel = newId_hotel;
	}

	public String getId_evenement(){
		return this.id_evenement;
	}

<<<<<<< Updated upstream
	public String getNom_evenement(){
		return this.nom_evenement;
	}

	public Date getDate_evenement(){
		return this.date_evenement;
	}

=======
	public Date getDate_debut_evenement(){
		return this.date_debut_evenement;
	}

	public Date getDate_fin_evenement(){
		return this.date_fin_evenement;
	}


>>>>>>> Stashed changes
	public Date getDate_insertion(){
		return this.date_insertion;
	}

	public String getDescription(){
		return this.description;
	}

<<<<<<< Updated upstream
=======
	public String getTitre_evenement() {
		return titre_evenement;
	}



	public String getLieu_evenement() {
		return lieu_evenement;
	}



>>>>>>> Stashed changes
	public String getId_categorie_evenement(){
		return this.id_categorie_evenement;
	}

	public String getId_ville(){
		return this.id_ville;
	}

	public String getId_hotel(){
		return this.id_hotel;
	}

	public List<Evenement> getAll() throws Exception {
		List<Evenement> evenements = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Evenement";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Evenement obj = new Evenement(
<<<<<<< Updated upstream
				resultSet.getString("id_evenement"),
				resultSet.getString("nom_evenement"),
				resultSet.getDate("date_evenement"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("description"),
				resultSet.getString("id_categorie_evenement"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_hotel")
=======
					resultSet.getString("id_evenement"),
					resultSet.getString("titre_evenement"),
					resultSet.getString("description"),
					resultSet.getString("lieu_evenement"),
					resultSet.getDate("date_debut_evenement"),
					resultSet.getDate("date_fin_evenement"),
					resultSet.getString("id_hotel"),
					resultSet.getString("id_ville"),
					resultSet.getString("id_categorie_evenement"),
					resultSet.getDate("date_insertion")
>>>>>>> Stashed changes
				);
				evenements.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Evenements: " + e.getMessage());
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

		if (evenements.isEmpty()) {
			throw new Exception("No Evenements found");
		}

		return evenements;
	}

	public Evenement getById(String id_evenement) throws Exception {
		Evenement evenement = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM Evenement WHERE id_evenement = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,id_evenement);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				evenement = new Evenement(
<<<<<<< Updated upstream
				resultSet.getString("id_evenement"),
				resultSet.getString("nom_evenement"),
				resultSet.getDate("date_evenement"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("description"),
				resultSet.getString("id_categorie_evenement"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_hotel")
=======
					resultSet.getString("id_evenement"),
					resultSet.getString("titre_evenement"),
					resultSet.getString("description"),
					resultSet.getString("lieu_evenement"),
					resultSet.getDate("date_debut_evenement"),
					resultSet.getDate("date_fin_evenement"),
					resultSet.getString("id_hotel"),
					resultSet.getString("id_ville"),
					resultSet.getString("id_categorie_evenement"),
					resultSet.getDate("date_insertion")
>>>>>>> Stashed changes
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Evenements: " + e.getMessage());
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

		return evenement;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "INSERT INTO Evenement (id_evenement,nom_evenement,date_evenement,date_insertion,description,id_categorie_evenement,id_ville,id_hotel) VALUES (generate_id_evenement(),?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_evenement());
            statement.setDate(2, getDate_evenement());
            statement.setDate(3, getDate_insertion());
            statement.setString(4, getDescription());
            statement.setString(5, getId_categorie_evenement());
            statement.setString(6, getId_ville());
            statement.setString(7, getId_hotel());
=======
            String query = "INSERT INTO Evenement (id_evenement,titre_evenement,description,lieu_evenement,date_debut_evenement,date_fin_evenement,id_hotel,id_ville,id_categorie_evenement,date_insertion) VALUES (?,?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_evenement());
            statement.setString(2, getTitre_evenement());
            statement.setString(3, getDescription());
            statement.setString(4, getLieu_evenement());
            statement.setDate(5, getDate_debut_evenement());
            statement.setDate(6, getDate_fin_evenement());
            statement.setString(7, getId_hotel());
            statement.setString(8, getId_ville());
            statement.setString(9, getId_categorie_evenement());
            statement.setDate(10, getDate_insertion());
>>>>>>> Stashed changes
            statement.executeUpdate();
            System.out.println("Donnees Evenement inserer avec succes");
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
            String query = "UPDATE Evenement SET nom_evenement = ? ,date_evenement = ? ,date_insertion = ? ,description = ? ,id_categorie_evenement = ? ,id_ville = ? ,id_hotel = ?  WHERE id_evenement = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_evenement());
            statement.setDate(2, getDate_evenement());
            statement.setDate(3, getDate_insertion());
            statement.setString(4, getDescription());
            statement.setString(5, getId_categorie_evenement());
            statement.setString(6, getId_ville());
            statement.setString(7, getId_hotel());
            statement.setString(8, getId_evenement());
=======
            String query = "UPDATE Evenement SET titre_evenement = ? ,description = ? ,lieu_evenement = ? ,date_debut_evenement = ? ,date_fin_evenement = ? ,id_hotel = ? ,id_ville = ? ,id_categorie_evenement = ? ,date_insertion = ?  WHERE id_evenement = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getTitre_evenement());
            statement.setString(2, getDescription());
            statement.setString(3, getLieu_evenement());
            statement.setDate(4, getDate_debut_evenement());
            statement.setDate(5, getDate_fin_evenement());
            statement.setString(6, getId_hotel());
            statement.setString(7, getId_ville());
            statement.setString(8, getId_categorie_evenement());
            statement.setDate(9, getDate_insertion());
            statement.setString(10, getId_evenement());
>>>>>>> Stashed changes
            statement.executeUpdate();
            System.out.println("Donnees Evenement mise a jour avec succes");
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

	public void delete(String id_evenement)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query = "DELETE  FROM Evenement WHERE id_evenement = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, id_evenement);
            statement.executeUpdate();
            System.out.println("Donnees Evenement supprimee avec succes");
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
	public static List<Evenement> searchCritere(String nom, Date dateDebut, Date dateFin, String nomHotel, String nomVille, String nomCategorieEvenement, String lieuEvenement) throws Exception {
		List<Evenement> evenements = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
	
		try {
			connection = Connexion.getConnection();
			
			// Construction de la requête en fonction des critères fournis
			StringBuilder queryBuilder = new StringBuilder(
				"SELECT e.*, h.nom_hotel, v.nom_ville, c.libelle " +
				"FROM Evenement e " +
				"JOIN Hotel h ON e.id_hotel = h.id_hotel " +
				"JOIN Ville v ON e.id_ville = v.id_ville " +
				"JOIN categorie_evenement c ON e.id_categorie_evenement = c.id_categorie_evenement " +
				"WHERE 1=1"
			);
	
			if (nom != null && !nom.isEmpty()) {
				queryBuilder.append(" AND e.titre_evenement LIKE ?");
			}
	
			if (dateDebut != null) {
				queryBuilder.append(" AND e.date_debut_evenement >= ?");
			}
	
			if (dateFin != null) {
				queryBuilder.append(" AND e.date_fin_evenement <= ?");
			}
	
			if (nomHotel != null && !nomHotel.isEmpty()) {
				queryBuilder.append(" AND h.nom_hotel LIKE ?");
			}
	
			if (nomVille != null && !nomVille.isEmpty()) {
				queryBuilder.append(" AND v.nom_ville LIKE ?");
			}
	
			if (nomCategorieEvenement != null && !nomCategorieEvenement.isEmpty()) {
				queryBuilder.append(" AND c.libelle LIKE ?");
			}
	
			if (lieuEvenement != null && !lieuEvenement.isEmpty()) {
				queryBuilder.append(" AND e.lieu_evenement LIKE ?");
			}
	
			statement = connection.prepareStatement(queryBuilder.toString());
	
			// Remplissage des paramètres en fonction des critères
			int parameterIndex = 1;
			if (nom != null && !nom.isEmpty()) {
				statement.setString(parameterIndex++, "%" + nom + "%");
			}
	
			if (dateDebut != null) {
				statement.setDate(parameterIndex++, dateDebut);
			}
	
			if (dateFin != null) {
				statement.setDate(parameterIndex++, dateFin);
			}
	
			if (nomHotel != null && !nomHotel.isEmpty()) {
				statement.setString(parameterIndex++, "%" + nomHotel + "%");
			}
	
			if (nomVille != null && !nomVille.isEmpty()) {
				statement.setString(parameterIndex++, "%" + nomVille + "%");
			}
	
			if (nomCategorieEvenement != null && !nomCategorieEvenement.isEmpty()) {
				statement.setString(parameterIndex++, "%" + nomCategorieEvenement + "%");
			}
	
			if (lieuEvenement != null && !lieuEvenement.isEmpty()) {
				statement.setString(parameterIndex++, "%" + lieuEvenement + "%");
			}
	
			resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				Evenement obj = new Evenement(
					resultSet.getString("id_evenement"),
					resultSet.getString("titre_evenement"),
					resultSet.getString("description"),
					resultSet.getString("lieu_evenement"),
					resultSet.getDate("date_debut_evenement"),
					resultSet.getDate("date_fin_evenement"),
					resultSet.getString("id_hotel"),
					resultSet.getString("id_ville"),
					resultSet.getString("id_categorie_evenement"),
					resultSet.getDate("date_insertion"));
				evenements.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Erreur lors de la recherche des Evenement : " + e.getMessage());
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
	
		return evenements;
	}
	
>>>>>>> Stashed changes
}
