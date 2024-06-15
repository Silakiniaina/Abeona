package model.partenaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Connection.Connexion;

public class Guide {
    String id_guide;
    String nom_guide;
    String description;
    int disponibilite;
    Timestamp date_insertion;
    String id_partenaire;
    public String getId_guide() {
        return id_guide;
    }
    public void setId_guide(String id_guide) {
        this.id_guide = id_guide;
    }
    public String getNom_guide() {
        return nom_guide;
    }
    public void setNom_guide(String nom_guide) {
        this.nom_guide = nom_guide;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }
    public Timestamp getDate_insertion() {
        return date_insertion;
    }
    public void setDate_insertion(Timestamp date_insertion) {
        this.date_insertion = date_insertion;
    }
    public String getId_partenaire() {
        return id_partenaire;
    }
    public void setId_partenaire(String id_partenaire) {
        this.id_partenaire = id_partenaire;
    }

    public Guide(){}
    public Guide(String id_guide,String nom_guide,String description,int disponibilite,Timestamp date_insertion,String id_partenaire){
        this.setId_guide(id_guide);
        this.setNom_guide(nom_guide);
        this.setDescription(description);
        this.setDisponibilite(disponibilite);
        this.setDate_insertion(date_insertion);
        this.setId_partenaire(id_partenaire);
    }

    public Guide getById()throws Exception{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Guide allTypes = null;
        try {
            String query = "SELECT * FROM Guide WHERE id_guide = ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1,this.getId_guide());
            rs = ps.executeQuery();
            while (rs.next()) {
                allTypes = new Guide(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getString(6));
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

    public ArrayList<Guide> searchGuide(String model) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Guide> allTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Guide WHERE nom_guide LIKE ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1,"%"+model+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Guide type = new Guide(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getString(6));
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
