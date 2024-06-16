package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;
public class Attraction {
    private String id_attraction;
    private String nom_attraction;
    private String description;
    private String id_categorie_attraction;
    private String id_ville; 
    private double evaluation; 

    /* Constructor */
    public Attraction(String name,String desc, String id_a, String id_v){
        this.set_nom_attraction(name);
        this.set_description(desc);
        this.set_id_categorie_attraction(id_a);
        this.set_id_ville(id_v);
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
                prsmt = c.prepareStatement("SELECT * FROM attraction WHERE lower(nom_attraction) LIKE ? ");
                prsmt.setString(1, "%" +dest.toLowerCase()+ "%");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Attraction a = new Attraction(rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5));
                    a.set_id_attraction(rs.getString(1));
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

    /* FOnction pour avoir les tops dans dans un province */
    public static ArrayList<Attraction> get_top_attraction(Province p)throws Exception{
        ArrayList<Attraction> resultat = new ArrayList<Attraction>();
        Connection c = null;
        PreparedStatement prsmt  = null;
        ResultSet rs = null; 

        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM v_ranking_attraction_province LIMIT 3");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Attraction a = new Attraction(rs.getString(2), rs.getString(3),rs.getString(5),rs.getString(6));
                    a.set_id_attraction(rs.getString(1));
                    a.set_evaluation(rs.getDouble(7));
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
    public String get_description(){
        return description;
    }
    public String get_id_categorie_attraction(){
        return id_categorie_attraction;
    }
    public String get_id_ville(){
        return id_ville;
    }
    public double get_evaluation(){
        return evaluation;
    }

    /* Setters */
    public void set_id_attraction(String id_attraction) {
        this.id_attraction = id_attraction;
    }
    public void set_nom_attraction(String nom_attraction) {
        this.nom_attraction = nom_attraction;
    }
    public void set_description(String str){
        this.description = str;
    }
    public void set_id_categorie_attraction(String str){
        this.id_categorie_attraction = str;
    }
    public void set_id_ville(String str){
        this.id_ville = str;
    }
    public void set_evaluation(double d){
        this.evaluation = d;
    }

    /* Test */
    public static void main(String[] args) {
        try {
            ArrayList<Attraction> ls = Attraction.get_top_attraction(Province.get_province_par_id(null, "PRO1"));
            System.out.println(ls.size());
            for(Attraction a : ls ){
                System.out.println("id : "+a.get_id_attraction());
                System.out.println("evaluation : "+a.get_evaluation());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
