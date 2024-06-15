package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Connection.Connexion;

public class Categorie_transport {
    String id_categorie_transport;
    String libelle;
    public String getId_categorie_transport() {
        return id_categorie_transport;
    }
    public void setId_categorie_transport(String id_categorie_transport) {
        this.id_categorie_transport = id_categorie_transport;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public Categorie_transport(){}
    public Categorie_transport(String id_categorie_transport, String libelle) {
        this.setId_categorie_transport(id_categorie_transport);
        this.setLibelle(libelle);
    }

    public ArrayList<Categorie_transport> getAllCategorie_transport() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Categorie_transport> allTypes = new ArrayList<>();
        try {
            String query = "select * from categorie_transport";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categorie_transport type = new Categorie_transport(rs.getString(1), rs.getString(2));
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
