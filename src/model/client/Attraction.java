package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;
public class Attraction extends Partenaire{
    private String id_categorie_attraction;
    private String id_ville; 

    /* Constructor */
    public Attraction(String name,String desc, String id_a, String id_v){
        this.set_nom(name);
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
                    Attraction a = new Attraction(rs.getString(2), rs.getString(3),rs.getString(5),rs.getString(6));
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
                prsmt = c.prepareStatement("SELECT * FROM attraction");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Attraction a = new Attraction(rs.getString(2), rs.getString(3),rs.getString(5),rs.getString(6));
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
            prsmt = c.prepareStatement("SELECT * FROM attraction WHERE id_attraction = ? ");
            prsmt.setString(1,id);
            rs = prsmt.executeQuery();
            if (rs.next()) {
                resultat = new Attraction(rs.getString(2), rs.getString(3),rs.getString(5),rs.getString(6));
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

    @Override
    public String get_categorie_avis() {
        return "CAV4";
    }

    @Override
    public String get_categorie_evaluation() {
        return "CEV4";
    }

    @Override
    public double get_evaluation()throws Exception{
        double resultat = 0;
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM v_evaluation_attraction WHERE id_attraction = ?");
            prstm.setString(1, this.get_id());
            rs = prstm.executeQuery();
            if(rs.next()){
                resultat = rs.getDouble(2);
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null){ rs.close(); }
            if(prstm != null){ prstm.close(); }
            if(c != null){ c.close(); }
        }
        return resultat;
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
