package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Province {
    private String id_province;
    private String nom_province;
    private String description;

    /* Construteurs */
    public Province(String id, String nom, String desc){
        this.set_id_province(id);
        this.set_nom_province(nom);
        this.set_description(desc);
    }

    /* Fonction pour avoir la liste de toutes les provinces */
    public static ArrayList<Province> get_liste_provinces()throws Exception{
        ArrayList<Province> resultat = new ArrayList<Province>();
        Connection c = null;
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM province");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Province p = new Province(rs.getString(1), rs.getString(2), rs.getString(3));
                    resultat.add(p);
                }
            }else{
                throw new Exception("Aucune connexion");
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

    /* Fonction pour avoir la liste des point d'interets dans une province */
    public ArrayList<PointInteret> get_point_interets()throws Exception{
        ArrayList<PointInteret> resultat = new ArrayList<PointInteret>();
        Connection c = null;
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM v_point_interet_province WHERE id_province = ?");
                prsmt.setString(1, this.get_id_province());
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    PointInteret p = new PointInteret(rs.getString(2), rs.getString(3));
                    resultat.add(p);
                }
            }else{
                throw new Exception("Aucune connexion");
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
    public static Province get_province_par_id(Connection con, String id)throws Exception{
        Province resultat = null;
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
            prsmt = c.prepareStatement("SELECT * FROM province WHERE id_province = ? ");
            prsmt.setString(1,id);
            rs = prsmt.executeQuery();
            if (rs.next()) {
                resultat = new Province(rs.getString(1), rs.getString(2),rs.getString(3));
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
    public String get_id_province() {
        return id_province;
    }
    public String get_nom_province() {
        return nom_province;
    }
    public String get_description() {
        return description;
    }
    
    /* Setters */
    public void set_id_province(String id_province) {
        this.id_province = id_province;
    }
    public void set_nom_province(String nom_province) {
        this.nom_province = nom_province;
    }
    public void set_description(String description) {
        this.description = description;
    }

    public static void main(String[] args) {
        try {  
            ArrayList<Province> ls = Province.get_liste_provinces();
            for(Province p : ls){
                ArrayList<PointInteret> point = p.get_point_interets();
                for(PointInteret pp : point){
                    System.out.println("Province : "+p.get_nom_province()+ " point : "+pp.get_libelle());
                }
            }
            System.out.println(ls.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
