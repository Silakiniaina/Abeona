package stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connexion;

public class StatReservationMensuelHotel {
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
    public StatReservationMensuelHotel() {
    }
    public StatReservationMensuelHotel(String mois, int annee, int nombre_reservations) {
        this.mois = mois;
        this.annee = annee;
        this.nombre_reservations = nombre_reservations;
    }

    public List<StatReservationMensuelHotel> getAll() throws Exception {
		List<StatReservationMensuelHotel> statReservationMensuels = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolutions_reservations_hotel_mensuelles";
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatReservationMensuelHotel obj = new StatReservationMensuelHotel(
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

	public List<StatReservationMensuelHotel> getAllByYear(int year) throws Exception {
		List<StatReservationMensuelHotel> statReservationMensuels = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Connexion.getConnection();
			String query = "SELECT * FROM V_evolutions_reservations_hotel_mensuelles WHERE annee = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,year);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				StatReservationMensuelHotel obj = new StatReservationMensuelHotel(
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