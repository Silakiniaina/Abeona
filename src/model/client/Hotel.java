package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Hotel {
    private String id_hotel; 
    private String nom_hotel;

    /* Constructor */
    public Hotel(String nom){
        this.set_nom_hotel(nom);
    }

    public Hotel(String id, String nom){
        this.set_id_hotel(id);
        this.set_nom_hotel(nom);
    }

    /* Fonction pour avoir une categorie id par son Id */
    public static Hotel get_hotel_par_id(Connection con , String id)throws Exception{
        Hotel resultat = null;
        Connection c = null; 
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        boolean est_nouvelle_connexion = false;
        try {
            if(con == null){    
                c = Database.get_connection(); 
                est_nouvelle_connexion = true;
            }
            else { c = con; }
            prsmt = c.prepareStatement("SELECT * FROM hotel WHERE id_hotel = ? ");
            prsmt.setString(1,id);
            rs = prsmt.executeQuery();
            if (rs.next()) {
                resultat = new Hotel(rs.getString(2));
                resultat.set_id_hotel(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs != null){ rs.close(); }
            if(prsmt != null){ prsmt.close(); }
            if(est_nouvelle_connexion){ c.close(); }
        }
        return resultat;
    }

    /* Fonction pour rechercher des hotels */
    public static ArrayList<Hotel> rechercher_hotel(String dest)throws Exception{
        ArrayList<Hotel> resultat = new ArrayList<Hotel>();
        Connection c = null;
        PreparedStatement prsmt  = null;
        ResultSet rs = null; 

        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM hotel WHERE nom_hotel LIKE ?");
                prsmt.setString(1, "%" +dest+ "%");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Hotel a = new Hotel(rs.getString(1), rs.getString(2));
                    resultat.add(a);
                }
            }else{
                throw new Exception("Aucune connexion ");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null){ rs.close(); }
            if(prsmt != null){ prsmt.close(); }
            if(c != null){ c.close(); }
        }
        return resultat;
    }

    /* Getters */
    public String get_id_hotel() {
        return id_hotel;
    }
    public String get_nom_hotel() {
        return nom_hotel;
    }

    /* Setters */
    public void set_id_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }
    public void set_nom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    
}
