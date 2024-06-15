package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Connection.Connexion;

public class Categorie_hotel {
    String id_categorie_hotel;
    String libelle;
    public String getId_categorie_hotel() {
        return id_categorie_hotel;
    }
    public void setId_categorie_hotel(String id_categorie_hotel) {
        this.id_categorie_hotel = id_categorie_hotel;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public Categorie_hotel(){}
    public Categorie_hotel(String id_categorie_hotel, String libelle) {
        this.setId_categorie_hotel(id_categorie_hotel);
        this.setLibelle(libelle);
    }

    public ArrayList<Categorie_hotel> getAllCategorie_hotel() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Categorie_hotel> allTypes = new ArrayList<>();
        try {
            String query = "select * from categorie_hotel";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categorie_hotel type = new Categorie_hotel(rs.getString(1), rs.getString(2));
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
