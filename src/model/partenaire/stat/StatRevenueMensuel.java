package stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class StatRevenueMensuel {
    String mois;
	int nombre_clients;
	int nombre_clients_ayant_achete_pack;
	int[] mois_num;
	int annee;
	double revenu_mensuel;
    

    public String getMois() {
        return mois;
    }
    public void setMois(String mois) {
        this.mois = mois;
    }
    public int getNombre_clients() {
        return nombre_clients;
    }
    public void setNombre_clients(int nombre_clients) {
        this.nombre_clients = nombre_clients;
    }
    public int getNombre_clients_ayant_achete_pack() {
        return nombre_clients_ayant_achete_pack;
    }
    public void setNombre_clients_ayant_achete_pack(int nombre_clients_ayant_achete_pack) {
        this.nombre_clients_ayant_achete_pack = nombre_clients_ayant_achete_pack;
    }
    public int[] getMois_num() {
        return mois_num;
    }
    public void setMois_num(int[] mois_num) {
        this.mois_num = mois_num;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public double getRevenu_mensuel() {
        return revenu_mensuel;
    }
    public void setRevenu_mensuel(double revenu_mensuel) {
        this.revenu_mensuel = revenu_mensuel;
    }

    public StatRevenueMensuel(String mois, int nombre_clients, int nombre_clients_ayant_achete_pack,
            int annee, double revenu_mensuel) {
        this.mois = mois;
        this.nombre_clients = nombre_clients;
        this.nombre_clients_ayant_achete_pack = nombre_clients_ayant_achete_pack;
        this.annee = annee;
        this.revenu_mensuel = revenu_mensuel;
    }

    public StatRevenueMensuel() {
    }

    public List<StatRevenueMensuel> getAll() throws Exception {
		List<StatRevenueMensuel> v_revenu_mensuels = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolution_revenu_mensuel";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatRevenueMensuel obj = new StatRevenueMensuel(
				resultSet.getString("mois"),
				resultSet.getInt("nombre_clients"),
				resultSet.getInt("nombre_clients_ayant_achete_pack"),
				resultSet.getInt("annee"),
				resultSet.getDouble("revenu_mensuel")
				);
				v_revenu_mensuels.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all V_revenu_mensuels: " + e.getMessage());
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

		

		return v_revenu_mensuels;
	}

    public List<StatRevenueMensuel> getAllYear(int year) throws Exception {
		List<StatRevenueMensuel> v_revenu_mensuels = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolution_revenu_mensuel WHERE annee=?";
			statement = connection.prepareStatement(query);
            statement.setInt(1,year);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatRevenueMensuel obj = new StatRevenueMensuel(
				resultSet.getString("mois"),
				resultSet.getInt("nombre_clients"),
				resultSet.getInt("nombre_clients_ayant_achete_pack"),
				resultSet.getInt("annee"),
				resultSet.getDouble("revenu_mensuel")
				);
				v_revenu_mensuels.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all V_revenu_mensuels: " + e.getMessage());
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

		return v_revenu_mensuels;
	}

    
}
