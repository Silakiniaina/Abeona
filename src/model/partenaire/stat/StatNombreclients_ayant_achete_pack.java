package stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class StatNombreclients_ayant_achete_pack{
	String mois;
	int nombre_clients_ayant_achete_pack;
	int annee;

	public StatNombreclients_ayant_achete_pack() throws Exception {
	}

	public StatNombreclients_ayant_achete_pack(String mois, int nombre_clients_ayant_achete_pack, int annee) throws Exception {
		this.mois = mois;
		this.nombre_clients_ayant_achete_pack = nombre_clients_ayant_achete_pack;
		this.annee = annee;
	}

	public void setMois(String newMois) throws Exception {
		this.mois = newMois;
	}

	public void setNombre_clients_ayant_achete_pack(int newNombre_clients_ayant_achete_pack) throws Exception {
		this.nombre_clients_ayant_achete_pack = newNombre_clients_ayant_achete_pack;
	}

	public void setAnnee(int newAnnee) throws Exception {
		this.annee = newAnnee;
	}

	public String getMois(){
		return this.mois;
	}

	public int getNombre_clients_ayant_achete_pack(){
		return this.nombre_clients_ayant_achete_pack;
	}

	public int getAnnee(){
		return this.annee;
	}

	public List<StatNombreclients_ayant_achete_pack> getAll() throws Exception {
		List<StatNombreclients_ayant_achete_pack> StatNombreclients_ayant_achete_pack = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolution_nombreclientsayantachetespack";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatNombreclients_ayant_achete_pack obj = new StatNombreclients_ayant_achete_pack(
				resultSet.getString("mois"),
				resultSet.getInt("nombre_clients_ayant_achete_pack"),
				resultSet.getInt("annee")
				);
				StatNombreclients_ayant_achete_pack.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all StatNombreclients_ayant_achete_pack: " + e.getMessage());
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

		if (StatNombreclients_ayant_achete_pack.isEmpty()) {
			throw new Exception("No StatNombreclients_ayant_achete_pack found");
		}

		return StatNombreclients_ayant_achete_pack;
	}


	public List<StatNombreclients_ayant_achete_pack> getAllYear(int year) throws Exception {
		List<StatNombreclients_ayant_achete_pack> StatNombreclients_ayant_achete_pack = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolution_nombreclientsayantachetespack where annee=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,year);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatNombreclients_ayant_achete_pack obj = new StatNombreclients_ayant_achete_pack(
				resultSet.getString("mois"),
				resultSet.getInt("nombre_clients_ayant_achete_pack"),
				resultSet.getInt("annee")
				);
				StatNombreclients_ayant_achete_pack.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all StatNombreclients_ayant_achete_pack: " + e.getMessage());
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
		return StatNombreclients_ayant_achete_pack;
	}
}
