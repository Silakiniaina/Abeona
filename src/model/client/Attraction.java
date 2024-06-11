package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;
public class Attraction {
    private String id_attraction;
    private String nom_attraction;

    /* Constructor */
    public Attraction(String id, String name){
        this.set_id_attraction(id);
        this.set_nom_attraction(name);
    }
    
    /* Fonction pour rechercher des attraction */
    public static ArrayList<Attraction> rechercher_attraction(String dest)throws Exception{
        ArrayList<Attraction> resultat = new ArrayList<Attraction>();
        Connection c = null;
        PreparedStatement prsmt  = null;
        ResultSet rs = null; 

        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM attraction WHERE nom_attraction LIKE '%?%'");
                prsmt.setString(1, dest);
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Attraction a = new Attraction(rs.getString(1), rs.getString(2));
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
    public String get_id_attraction() {
        return id_attraction;
    }
    public String get_nom_attraction() {
        return nom_attraction;
    }

    /* Setters */
    public void set_id_attraction(String id_attraction) {
        this.id_attraction = id_attraction;
    }
    public void set_nom_attraction(String nom_attraction) {
        this.nom_attraction = nom_attraction;
    }
}
