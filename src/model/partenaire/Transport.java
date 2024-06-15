package model.partenaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Connection.Connexion;

public class Transport {
    String id_transport;
    String nom_transport;
    String description;
    double tarif;
    Timestamp date_insertion;
    String id_categorie_tranport;
    String id_partenaire;
    float evaluation = 0;
    public float getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }
    public String getId_transport() {
        return id_transport;
    }
    public void setId_transport(String id_transport) {
        this.id_transport = id_transport;
    }
    public String getNom_transport() {
        return nom_transport;
    }
    public void setNom_transport(String nom_transport) {
        this.nom_transport = nom_transport;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getTarif() {
        return tarif;
    }
    public void setTarif(double tarif) {
        this.tarif = tarif;
    }
    public Timestamp getDate_insertion() {
        return date_insertion;
    }
    public void setDate_insertion(Timestamp date_insertion) {
        this.date_insertion = date_insertion;
    }
    public String getId_categorie_tranport() {
        return id_categorie_tranport;
    }
    public void setId_categorie_tranport(String id_categorie_tranport) {
        this.id_categorie_tranport = id_categorie_tranport;
    }
    public String getId_partenaire() {
        return id_partenaire;
    }
    public void setId_partenaire(String id_partenaire) {
        this.id_partenaire = id_partenaire;
    }
    public Transport() {
    }
    public Transport(String id_transport, String nom_transport, String description, double tarif,
            Timestamp date_insertion, String id_categorie_tranport, String id_partenaire) {
        this.setId_transport(id_transport);
        this.setNom_transport(nom_transport);
        this.setDescription(description);
        this.setTarif(tarif);
        this.setDate_insertion(date_insertion);
        this.setId_categorie_tranport(id_categorie_tranport);
        this.setId_partenaire(id_partenaire);
    }

    public Transport getById()throws Exception{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Transport allTypes = null;
        try {
            String query = "SELECT * FROM transport WHERE id_transport = ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1,this.getId_transport());
            rs = ps.executeQuery();
            while (rs.next()) {
                allTypes = new Transport(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4), rs.getTimestamp(5), rs.getString(6),rs.getString(7));
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

    public ArrayList<Transport> searchTransport(String model) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Transport> allTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM transport WHERE nom_transport LIKE ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1,"%"+model+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Transport type = new Transport(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4), rs.getTimestamp(5), rs.getString(6),rs.getString(7));
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

    public static String getSuiteOfWhere(String nom , String idVille ,float evaluation, Timestamp dateInsertionDebut,Timestamp dateInsertionFin){
        String result = "";
        if(nom!=null && !nom.equals("")){
            result += " and nom_transport like '%"+nom+"%'";
        }
        if(evaluation!=0){
            result+= " and evaluation = "+evaluation;
        }
        if(dateInsertionDebut!=null){
            result+=" and date_insertion >= '"+dateInsertionDebut+"'";
        }
        if(dateInsertionFin!=null){
            result+=" and date_insertion <= '"+dateInsertionFin+"'";
        }
        return result;
    }

    public static ArrayList<Transport> getListTransportByCriteria(String nom , String idVille ,float evaluation, Timestamp dateInsertionDebut,Timestamp dateInsertionFin) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Transport> allTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM v_transport_evaluation where 1=1"+getSuiteOfWhere(nom, idVille, evaluation, dateInsertionDebut, dateInsertionFin);
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Transport type = new Transport(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4), rs.getTimestamp(5), rs.getString(6),rs.getString(7));
                        type.setEvaluation(rs.getFloat(8));
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
