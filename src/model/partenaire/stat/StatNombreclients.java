package stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class StatNombreclients{
	String mois;
	int nombre_clients;
	int annee;

	public StatNombreclients() throws Exception {
	}

	public StatNombreclients(String mois, int nombre_clients, int annee) throws Exception {
		this.mois = mois;
		this.nombre_clients = nombre_clients;
		this.annee = annee;
	}

	public void setMois(String newMois) throws Exception {
		this.mois = newMois;
	}

	public void setNombre_clients(int newNombre_clients) throws Exception {
		this.nombre_clients = newNombre_clients;
	}

	public void setAnnee(int newAnnee) throws Exception {
		this.annee = newAnnee;
	}

	public String getMois(){
		return this.mois;
	}

	public int getNombre_clients(){
		return this.nombre_clients;
	}

	public int getAnnee(){
		return this.annee;
	}

	public List<StatNombreclients> getAll() throws Exception {
		List<StatNombreclients> v_nombreclients = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolution_nombreclients";
								
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatNombreclients obj = new StatNombreclients(
				resultSet.getString("mois"),
				resultSet.getInt("nombre_clients"),
				resultSet.getInt("annee")
				);
				v_nombreclients.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all V_nombreclients: " + e.getMessage());
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

		if (v_nombreclients.isEmpty()) {
			throw new Exception("No V_nombreclients found");
		}

		return v_nombreclients;
	}


	public List<StatNombreclients> getAllYear(int year) throws Exception {
		List<StatNombreclients> v_nombreclients = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolution_nombreclients WHERE annee=?";
								;
			statement = connection.prepareStatement(query);
			statement.setInt(1,year);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatNombreclients obj = new StatNombreclients(
				resultSet.getString("mois"),
				resultSet.getInt("nombre_clients"),
				resultSet.getInt("annee")
				);
				v_nombreclients.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all V_nombreclients: " + e.getMessage());
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

		return v_nombreclients;
	}

}
