package obj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class Attractions{
	String id_attraction;
	String nom_attraction;
<<<<<<< Updated upstream
	String description_attraction;
	String id_ville;
	String id_categorie_attraction;
	Date date_insertion;
=======
	String description;
	Double prix;
	Date date_insertion;
	String id_categorie_attraction;
	String id_ville;
	
>>>>>>> Stashed changes

	public Attractions() throws Exception {
	}

<<<<<<< Updated upstream
	public Attractions(String id_attraction, String nom_attraction, String description_attraction, String id_ville, String id_categorie_attraction, Date date_insertion) throws Exception {
		this.id_attraction = id_attraction;
		this.nom_attraction = nom_attraction;
		this.description_attraction = description_attraction;
		this.id_ville = id_ville;
		this.id_categorie_attraction = id_categorie_attraction;
		this.date_insertion = date_insertion;
	}

	public void setId_attraction(String newId_attraction) throws Exception {
		this.id_attraction = newId_attraction;
	}

	public void setNom_attraction(String newNom_attraction) throws Exception {
		this.nom_attraction = newNom_attraction;
	}

	public void setDescription_attraction(String newDescription_attraction) throws Exception {
		this.description_attraction = newDescription_attraction;
	}

	public void setId_ville(String newId_ville) throws Exception {
		this.id_ville = newId_ville;
	}

	public void setId_categorie_attraction(String newId_categorie_attraction) throws Exception {
		this.id_categorie_attraction = newId_categorie_attraction;
	}

	public void setDate_insertion(Date newDate_insertion) throws Exception {
		this.date_insertion = newDate_insertion;
	}

	public String getId_attraction(){
		return this.id_attraction;
	}

	public String getNom_attraction(){
		return this.nom_attraction;
	}

	public String getDescription_attraction(){
		return this.description_attraction;
	}

	public String getId_ville(){
		return this.id_ville;
	}

	public String getId_categorie_attraction(){
		return this.id_categorie_attraction;
	}

	public Date getDate_insertion(){
		return this.date_insertion;
	}
=======
	
	
	public Attractions(String id_attraction, String nom_attraction, String description, Double prix,
		Date date_insertion, String id_categorie_attraction, String id_ville) {
		this.id_attraction = id_attraction;
		this.nom_attraction = nom_attraction;
		this.description = description;
		this.prix = prix;
		this.date_insertion = date_insertion;
		this.id_categorie_attraction = id_categorie_attraction;
		this.id_ville = id_ville;
	}


	public String getId_attraction() {
		return id_attraction;
	}
	public void setId_attraction(String id_attraction) {
		this.id_attraction = id_attraction;
	}
	public String getNom_attraction() {
		return nom_attraction;
	}
	public void setNom_attraction(String nom_attraction) {
		this.nom_attraction = nom_attraction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Date getDate_insertion() {
		return date_insertion;
	}
	public void setDate_insertion(Date date_insertion) {
		this.date_insertion = date_insertion;
	}
	public String getId_categorie_attraction() {
		return id_categorie_attraction;
	}
	public void setId_categorie_attraction(String id_categorie_attraction) {
		this.id_categorie_attraction = id_categorie_attraction;
	}
	public String getId_ville() {
		return id_ville;
	}
	public void setId_ville(String id_ville) {
		this.id_ville = id_ville;
	}


>>>>>>> Stashed changes

	public List<Attractions> getAll() throws Exception {
		List<Attractions> attractionss = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
<<<<<<< Updated upstream
			String query = "SELECT * FROM Attractions";
=======
			String query = "SELECT * FROM Attraction";
>>>>>>> Stashed changes
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Attractions obj = new Attractions(
				resultSet.getString("id_attraction"),
				resultSet.getString("nom_attraction"),
<<<<<<< Updated upstream
				resultSet.getString("description_attraction"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_categorie_attraction"),
				resultSet.getDate("date_insertion")
=======
				resultSet.getString("description"),
				resultSet.getDouble("prix"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_categorie_attraction")

				
>>>>>>> Stashed changes
				);
				attractionss.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Attractionss: " + e.getMessage());
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
		if (attractionss.isEmpty()) {
			throw new Exception("No Attractionss found");
		}

=======
>>>>>>> Stashed changes
		return attractionss;
	}

	public Attractions getById(String id_attraction) throws Exception {
		Attractions attractions = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
<<<<<<< Updated upstream
			String query = "SELECT * FROM Attractions WHERE id_attraction = ?";
=======
			String query = "SELECT * FROM Attraction WHERE id_attraction = ?";
>>>>>>> Stashed changes
			statement = connection.prepareStatement(query);
			statement.setString(1,id_attraction);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				attractions = new Attractions(
				resultSet.getString("id_attraction"),
				resultSet.getString("nom_attraction"),
<<<<<<< Updated upstream
				resultSet.getString("description_attraction"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_categorie_attraction"),
				resultSet.getDate("date_insertion")
=======
				resultSet.getString("description"),
				resultSet.getDouble("prix"),
				resultSet.getDate("date_insertion"),
				resultSet.getString("id_ville"),
				resultSet.getString("id_categorie_attraction")
				
>>>>>>> Stashed changes
				);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all Attractionss: " + e.getMessage());
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

		return attractions;
	}

	public void insert() throws Exception {
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "INSERT INTO Attractions (id_attraction,nom_attraction,description_attraction,id_ville,id_categorie_attraction,date_insertion) VALUES (generate_id_attraction(),?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_attraction());
            statement.setString(2, getDescription_attraction());
            statement.setString(3, getId_ville());
            statement.setString(4, getId_categorie_attraction());
            statement.setDate(5, getDate_insertion());
            statement.executeUpdate();
            System.out.println("Donnees Attractions inserer avec succes");
=======
            String query = "INSERT INTO Attraction (id_attraction,nom_attraction,description,date_insertion,prix,id_categorie_attraction,id_ville) VALUES (?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, getId_attraction());
            statement.setString(2, getNom_attraction());
            statement.setString(3, getDescription());
            statement.setDate(4, getDate_insertion());
            statement.setDouble(5, getPrix());
            statement.setString(6, getId_categorie_attraction());
            statement.setString(7, getId_ville());
            statement.executeUpdate();
            System.out.println("Donnees Attraction inserer avec succes");
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
            String query = "UPDATE Attractions SET nom_attraction = ? ,description_attraction = ? ,id_ville = ? ,id_categorie_attraction = ? ,date_insertion = ?  WHERE id_attraction = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_attraction());
            statement.setString(2, getDescription_attraction());
            statement.setString(3, getId_ville());
            statement.setString(4, getId_categorie_attraction());
            statement.setDate(5, getDate_insertion());
            statement.setString(6, getId_attraction());
            statement.executeUpdate();
            System.out.println("Donnees Attractions mise a jour avec succes");
=======
            String query = "UPDATE Attraction SET nom_attraction = ? ,description = ? ,date_insertion = ? ,prix = ? ,id_categorie_attraction = ? ,id_ville = ?  WHERE id_attraction = ? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, getNom_attraction());
            statement.setString(2, getDescription());
            statement.setDate(3, getDate_insertion());
            statement.setDouble(4, getPrix());
            statement.setString(5, getId_categorie_attraction());
            statement.setString(6, getId_ville());
            statement.setString(7, getId_attraction());
            statement.executeUpdate();
            System.out.println("Donnees Attraction mise a jour avec succes");
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

	public void delete(String id_attraction)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
<<<<<<< Updated upstream
            String query = "DELETE  FROM Attractions WHERE id_attraction = ? ";
=======
            String query = "DELETE  FROM Attraction WHERE id_attraction = ? ";
>>>>>>> Stashed changes
            statement = connection.prepareStatement(query);
            statement.setString(1, id_attraction);
            statement.executeUpdate();
            System.out.println("Donnees Attractions supprimee avec succes");
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
	public static List<Attractions> searchCritere(String nom, Double prixMin, Double prixMax, Date dateDebut, Date dateFin, String categorie, String ville) throws Exception {
		List<Attractions> attractions = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
	
		try {
			connection = Connexion.getConnection();
			
			// Construction de la requête en fonction des critères fournis
			StringBuilder queryBuilder = new StringBuilder("SELECT a.*, v.*, c.* FROM Attraction a " +
															"INNER JOIN categorie_attraction c ON a.id_categorie_attraction = c.id_categorie_attraction " +
															"INNER JOIN ville v ON a.id_ville = v.id_ville " +
															"WHERE 1=1");
	
			if (nom != null && !nom.isEmpty()) {
				queryBuilder.append(" AND a.nom_attraction LIKE ?");
			}
	
			if (prixMin != null && prixMin != 0) {
				queryBuilder.append(" AND a.prix >= ?");
			}
	
			if (prixMax != null && prixMax != 0) {
				queryBuilder.append(" AND a.prix <= ?");
			}
	
			if (dateDebut != null) {
				queryBuilder.append(" AND a.date_insertion >= ?");
			}
	
			if (dateFin != null) {
				queryBuilder.append(" AND a.date_insertion <= ?");
			}
	
			if (categorie != null && !categorie.isEmpty()) {
				queryBuilder.append(" AND c.libelle LIKE ?");
			}
	
			if (ville != null && !ville.isEmpty()) {
				queryBuilder.append(" AND v.nom_ville LIKE ?");
			}
	
			statement = connection.prepareStatement(queryBuilder.toString());
	
			// Remplissage des paramètres en fonction des critères
			int parameterIndex = 1;
			if (nom != null && !nom.isEmpty()) {
				statement.setString(parameterIndex++, "%" + nom + "%");
			}
	
			if (prixMin != null && prixMin != 0) {
				statement.setDouble(parameterIndex++, prixMin);
			}
	
			if (prixMax != null && prixMax != 0) {
				statement.setDouble(parameterIndex++, prixMax);
			}
	
			if (dateDebut != null) {
				statement.setDate(parameterIndex++, dateDebut);
			}
	
			if (dateFin != null) {
				statement.setDate(parameterIndex++, dateFin);
			}
	
			if (categorie != null && !categorie.isEmpty()) {
				statement.setString(parameterIndex++, "%"+categorie+"%");
			}
	
			if (ville != null && !ville.isEmpty()) {
				statement.setString(parameterIndex++, "%"+ville+"%");
			}
	
			resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				Attractions obj = new Attractions(
						resultSet.getString("id_attraction"),
						resultSet.getString("nom_attraction"),
						resultSet.getString("description"),
						resultSet.getDouble("prix"),
						resultSet.getDate("date_insertion"),
						resultSet.getString("id_ville"),
						resultSet.getString("id_categorie_attraction")
				);
				attractions.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Erreur lors de la recherche des attractions : " + e.getMessage());
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
				e.printStackTrace();
			}
		}
	
		return attractions;
	}
	

>>>>>>> Stashed changes
}
