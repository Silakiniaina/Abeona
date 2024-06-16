package model.partenaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Connection.Connexion;

public class Hotel {
    String id_hotel;
    String nom_hotel;
    String description;
    String adresse_hotel;
    Timestamp date_insertion;
    String id_partenaire;
    String id_categorie_hotel;
    String id_ville;
    float evaluation = 0;
    public float getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }
    public String getId_hotel() {
        return id_hotel;
    }
    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }
    public String getNom_hotel() {
        return nom_hotel;
    }
    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAdresse_hotel() {
        return adresse_hotel;
    }
    public void setAdresse_hotel(String adresse_hotel) {
        this.adresse_hotel = adresse_hotel;
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
    public String getId_categorie_hotel() {
        return id_categorie_hotel;
    }
    public void setId_categorie_hotel(String id_categorie_hotel) {
        this.id_categorie_hotel = id_categorie_hotel;
    }
    public String getId_ville() {
        return id_ville;
    }
    public void setId_ville(String id_ville) {
        this.id_ville = id_ville;
    }
    public Hotel(){}
    public Hotel(String id_hotel, String nom_hotel, String description, String adresse_hotel, Timestamp date_insertion,
            String id_partenaire, String id_categorie_hotel, String id_ville) {
        this.setId_hotel(id_hotel); 
        this.setNom_hotel(nom_hotel);
        this.setDescription(description);
        this.setAdresse_hotel(adresse_hotel);
        this.setDate_insertion(date_insertion);
        this.setId_partenaire(id_partenaire);
        this.setId_categorie_hotel(id_categorie_hotel);
        this.setId_ville(id_ville);
    }

    public Hotel getById()throws Exception{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Hotel allTypes = null;
        try {
            String query = "SELECT * FROM Hotel WHERE id_Hotel = ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1,this.getId_hotel());
            rs = ps.executeQuery();
            while (rs.next()) {
                allTypes = new Hotel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getString(6),rs.getString(7),rs.getString(7));
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


    public ArrayList<Hotel> searchHotel(String model) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Hotel> allTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Hotel WHERE nom_hotel LIKE ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1,"%"+model+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Hotel type = new Hotel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getString(6),rs.getString(7),rs.getString(8));
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
            result += " and nom_hotel like '%"+nom+"%'";
        }
        if(idVille!=null && !idVille.equals("")){
            result+= " and id_ville = '"+idVille+"'";
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

    // public static String getSuiteOfWhere(String nom , String idVille , Timestamp dateInsertionDebut,Timestamp dateInsertionFin){
    //     String result = "";
    //     if(nom!=null){
    //         result += " and nom_hotel = ?";
    //     }
    //     if(idVille!=null){
    //         result+= " and id_ville = ?";
    //     }
    //     if(dateInsertionDebut!=null){
    //         result+=" and date_insertion = ?";
    //     }
    //     if(dateInsertionFin!=null){
    //         result+=" and date_insertion = ?";
    //     }
    //     return result;
    // }

    // public static void forPreparedSt(String nom , String idVille , Timestamp dateInsertionDebut,Timestamp dateInsertionFin,PreparedStatement st)throws Exception{
    //     int counter = 1;
    //     if(nom!=null){
    //         st.setObject(counter, nom);
    //         counter++;
    //     }
    //     if(idVille!=null){
    //         st.setObject(counter, idVille);
    //         counter++;
    //     }
    //     if(dateInsertionDebut!=null){
    //         st.setObject(counter, dateInsertionDebut);
    //         counter++;
    //     }
    //     if(dateInsertionFin!=null){
    //         st.setObject(counter, dateInsertionFin);
    //         counter++;
    //     }
    // }

    public static ArrayList<Hotel> getListHotelByCriteria(String nom , String idVille ,float evaluation, Timestamp dateInsertionDebut,Timestamp dateInsertionFin)throws Exception{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Hotel> allTypes = new ArrayList<>();
        try {
             String query = "select * from v_hotel_evaluation where 1=1"+getSuiteOfWhere(nom, idVille, evaluation ,dateInsertionDebut, dateInsertionFin);
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            // forPreparedSt(nom,idVille,dateInsertionDebut,dateInsertionFin,ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                Hotel type = new Hotel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getString(6),rs.getString(7),rs.getString(8));
                        type.setEvaluation(rs.getFloat(9));
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
