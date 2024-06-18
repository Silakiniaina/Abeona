package stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class StatReservationMensuelTransport {
    String mois;
	int annee;
	int nombre_reservations;
    public String getMois() {
        return mois;
    }
    public void setMois(String mois) {
        this.mois = mois;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public int getNombre_reservations() {
        return nombre_reservations;
    }
    public void setNombre_reservations(int nombre_reservations) {
        this.nombre_reservations = nombre_reservations;
    }
    public StatReservationMensuelTransport() {
    }
    public StatReservationMensuelTransport(String mois, int annee, int nombre_reservations) {
        this.mois = mois;
        this.annee = annee;
        this.nombre_reservations = nombre_reservations;
    }

    public List<StatReservationMensuelTransport> getAll() throws Exception {
		List<StatReservationMensuelTransport> statReservationMensuels = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolution_reservations_transport";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatReservationMensuelTransport obj = new StatReservationMensuelTransport(
				resultSet.getString("mois"),
				resultSet.getInt("annee"),
				resultSet.getInt("nombre_reservations")
				);
				statReservationMensuels.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all statReservationMensuels: " + e.getMessage());
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

		return statReservationMensuels;
	}

	public List<StatReservationMensuelTransport> getAllByYear(int year) throws Exception {
		List<StatReservationMensuelTransport> statReservationMensuels = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolution_reservations_transport WHERE annee = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,year);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatReservationMensuelTransport obj = new StatReservationMensuelTransport(
				resultSet.getString("mois"),
				resultSet.getInt("annee"),
				resultSet.getInt("nombre_reservations")
				);
				statReservationMensuels.add(obj);
			}
		} catch (SQLException e) {
			throw new Exception("Error while finding all statReservationMensuels: " + e.getMessage());
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

		return statReservationMensuels;
	}
}
