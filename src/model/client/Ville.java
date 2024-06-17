package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Ville {
    private String id_ville;
    private String nom_ville;
    private String id_region;

    /* Constructor */
    public Ville(String nom, String id_region){
        this.set_nom_ville(nom);
        this.set_id_region(id_region);
    }

    /* Fonction pour avoir la liste des villes existantes */
    public static ArrayList<Ville> get_liste_ville()throws Exception{
        ArrayList<Ville> result  = new ArrayList<Ville>();
        Connection c = null;
        PreparedStatement prsmt = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prsmt = c.prepareStatement("SELECT * FROM ville");
            rs = prsmt.executeQuery(); 
            while(rs.next()){
                Ville v = new Ville(rs.getString(2), rs.getString(3));
                v.set_id_ville(rs.getString(1));
                result.add(v);
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null) rs.close();
            if(prsmt != null) prsmt.close();
            if(c != null) c.close();
        }
        return result;
    }

    /* Getters */
    public String get_id_ville() {
        return id_ville;
    }
    public String get_nom_ville() {
        return nom_ville;
    }
    public String get_id_region() {
        return id_region;
    }
    
    /* Setters */
    public void set_id_ville(String id_ville) {
        this.id_ville = id_ville;
    }
    public void set_nom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
    }
    public void set_id_region(String id_region) {
        this.id_region = id_region;
    }

    /* Test */
    public static void main(String[] args) {
        try{
            ArrayList<Ville> ls = Ville.get_liste_ville();
            System.out.println(ls.size());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
