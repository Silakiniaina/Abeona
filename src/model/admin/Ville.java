package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Connection.Connexion;

public class Ville {
    String id_ville;
    String nom_ville;
    String id_region;
    public String getId_ville() {
        return id_ville;
    }
    public void setId_ville(String id_ville) {
        this.id_ville = id_ville;
    }
    public String getNom_ville() {
        return nom_ville;
    }
    public void setNom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
    }
    public String getId_region() {
        return id_region;
    }
    public void setId_region(String id_region) {
        this.id_region = id_region;
    }
    public Ville() {
    }
    public Ville(String id_ville, String nom_ville, String id_region) {
        this.setId_ville(id_ville);
        this.setNom_ville(nom_ville);
        this.setId_region(id_region);
    }

    public ArrayList<Ville> getAllVille() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ville> allTypes = new ArrayList<>();
        try {
            String query = "select * from ville";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ville type = new Ville(rs.getString(1), rs.getString(2),rs.getString(3));
                allTypes.add(type);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allTypes;
    }
}
