package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;
public class Attraction extends Partenaire{
    private String id_categorie_attraction;
    private String id_ville;
    private double tarif; 

    /* Constructor */
    public Attraction(String name,String desc, double tarif,String id_a, String id_v,double ev){
        this.set_nom(name);
        this.set_description(desc);
        this.set_tarif(tarif);
        this.set_id_categorie_attraction(id_a);
        this.set_id_ville(id_v);
        this.set_evaluation(ev);
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
                prsmt = c.prepareStatement("SELECT * FROM v_attraction_with_evaluation WHERE lower(nom_attraction) LIKE ? ");
                prsmt.setString(1, "%" +dest.toLowerCase()+ "%");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Attraction a = new Attraction(rs.getString(2), rs.getString(3),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getDouble(8));
                    a.set_id(rs.getString(1));
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
                    Attraction a = new Attraction(rs.getString(2), rs.getString(3),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getDouble(8));
                    a.set_id(rs.getString(1));
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
    
    /* Fonction pour avoir tous les attractions */
    public static ArrayList<Attraction> get_liste_attraction()throws Exception{
        ArrayList<Attraction> resultat = new ArrayList<Attraction>();
        Connection c = null;
        PreparedStatement prsmt  = null;
        ResultSet rs = null; 

        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM v_attraction_with_evaluation");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Attraction a = new Attraction(rs.getString(2), rs.getString(3),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getDouble(8));
                    a.set_id(rs.getString(1));
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
    
    /* Fonction pour avoir une categorie id par son Id */
    public static Attraction get_attraction_par_id(Connection con , String id)throws Exception{
        Attraction resultat = null;
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
            prsmt = c.prepareStatement("SELECT * FROM v_evaluation_attraction WHERE id_attraction = ? ");
            prsmt.setString(1,id);
            rs = prsmt.executeQuery();
            if (rs.next()) {
                resultat = new Attraction(rs.getString(2), rs.getString(3),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getDouble(8));
                resultat.set_id(rs.getString(1));
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

    /* Fonction pour filtrer  */
    /* Getters */
    public String get_description(){
        return description;
    }
    public String get_id_categorie_attraction(){
        return id_categorie_attraction;
    }
    public String get_id_ville(){
        return id_ville;
    }
    public double get_tarif(){
        return this.tarif;
    }

    /* Setters */
    public void set_description(String str){
        this.description = str;
    }
    public void set_id_categorie_attraction(String str){
        this.id_categorie_attraction = str;
    }
    public void set_id_ville(String str){
        this.id_ville = str;
    }
    public void set_tarif(double d){
        this.tarif = d;
    }

    @Override
    public String get_categorie_avis() {
        return "CAV4";
    }

    @Override
    public String get_categorie_evaluation() {
        return "CEV4";
    }

    @Override
    public String get_categorie_reservation(){
        return "CRS4";
    }

    /* Test */
    public static void main(String[] args) {
        try {
            Attraction a = Attraction.get_attraction_par_id(null, "ATT1");
            double eval = a.get_evaluation();
            System.out.println("Evaluation : "+eval);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
