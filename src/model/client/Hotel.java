package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Hotel {
    private String id_hotel; 
    private String nom_hotel;
    private String description; 
    private String adress;
    private String id_partenaire;
    private String id_categorie_hotel;
    private String id_ville;
    private double evaluation;

    /* Constructor */
    public Hotel(String nom,String description,String adress, String id_p, String id_c, String id_v){
        this.set_nom_hotel(nom);
        this.set_description(description);
        this.set_adress(adress);
        this.set_id_partenaire(id_p);
        this.set_id_categorie_hotel(id_c);
        this.set_id_ville(id_v);
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
                resultat = new Hotel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8));
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
                    Hotel a = new Hotel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8));
                    a.set_id_hotel(rs.getString(1));
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
    public static ArrayList<Hotel> get_top_hotel(Province p)throws Exception {
        ArrayList<Hotel> resultat = new ArrayList<Hotel>();
        Connection c = null;
        PreparedStatement prsmt  = null;
        ResultSet rs = null; 
        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM v_ranking_hotel_province WHERE id_province = ? LIMIT 3");
                prsmt.setString(1, p.get_id_province());
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Hotel a =new Hotel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8));
                    a.set_id_hotel(rs.getString(1));
                    a.set_evaluation(rs.getDouble(9));
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
    public String get_description(){
        return description;
    }
    public String get_adress(){
        return adress;
    }
    public String get_id_partenaire(){
        return id_partenaire;
    }
    public String get_id_categorie_hotel(){
        return id_categorie_hotel;
    }
    public String get_id_ville(){
        return id_ville;
    }
    public double get_evaluation(){
        return evaluation;
    }

    /* Setters */
    public void set_id_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }
    public void set_nom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }
    public void set_description(String desc){
        this.description = desc;
    }
    public void set_adress(String str){
        this.adress = str;
    }
    public void set_id_partenaire(String id){
        this.id_partenaire = id;
    }
    public void set_id_categorie_hotel(String id){
        this.id_categorie_hotel = id;
    }
    public void set_id_ville(String id){
        this.id_ville = id;
    }
    public void set_evaluation(double d){
        this.evaluation = d;
    }
    /* Test */
    public static void main(String[] args) {
        try{
            ArrayList<Hotel> ls = Hotel.get_top_hotel(Province.get_province_par_id(null, "PRO1"));
            for(Hotel l : ls){
                System.out.println("id : "+l.get_id_hotel());
                System.out.println("evaluation : "+l.get_evaluation());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
