package stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Connexion;

public class TotalMoyenneRevenu {
    int annee;
	double total_revenu_mensuel;
	double moyenne_revenu_mensuel;
    public int getAnnee() {
        return annee;
    }
    public double getTotal_revenu_mensuel() {
        return total_revenu_mensuel;
    }
    public double getMoyenne_revenu_mensuel() {
        return moyenne_revenu_mensuel;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public void setTotal_revenu_mensuel(double total_revenu_mensuel) {
        this.total_revenu_mensuel = total_revenu_mensuel;
    }
    public void setMoyenne_revenu_mensuel(double moyenne_revenu_mensuel) {
        this.moyenne_revenu_mensuel = moyenne_revenu_mensuel;
    }
    public TotalMoyenneRevenu(double total_revenu_mensuel, double moyenne_revenu_mensuel) {
        this.total_revenu_mensuel = total_revenu_mensuel;
        this.moyenne_revenu_mensuel = moyenne_revenu_mensuel;
    }
    public TotalMoyenneRevenu(int annee, double total_revenu_mensuel, double moyenne_revenu_mensuel) {
        this.annee = annee;
        this.total_revenu_mensuel = total_revenu_mensuel;
        this.moyenne_revenu_mensuel = moyenne_revenu_mensuel;
    }

    
    public TotalMoyenneRevenu() {
    }
    public TotalMoyenneRevenu getTotal() throws Exception {
		TotalMoyenneRevenu v_total_moyenne_revenu_mensuels = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_total_moyenne_revenu";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				v_total_moyenne_revenu_mensuels = new TotalMoyenneRevenu(
				resultSet.getDouble("total_revenu_mensuel"),
				resultSet.getDouble("moyenne_revenu_mensuel")
				);	
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all V_total_moyenne_revenu_mensuels: " + e.getMessage());
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

		return v_total_moyenne_revenu_mensuels;
	}

    public TotalMoyenneRevenu getTotalByYear(int year) throws Exception {
		TotalMoyenneRevenu v_total_moyenne_revenu_mensuels = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_total_moyenne_revenu_mensuel where annee=?";
			statement = connection.prepareStatement(query);
            statement.setInt(1,year);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				v_total_moyenne_revenu_mensuels = new TotalMoyenneRevenu(
				resultSet.getInt("annee"),
				resultSet.getDouble("total_revenu_mensuel"),
				resultSet.getDouble("moyenne_revenu_mensuel")
				);
				
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all V_total_moyenne_revenu_mensuels: " + e.getMessage());
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

		return v_total_moyenne_revenu_mensuels;
	}

}
