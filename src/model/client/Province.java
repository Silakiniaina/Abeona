package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Province {
    private int id_province;
    private String nom_province;
    private String description;

    /* Construteurs */
    public Province(int id, String nom, String desc){
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
                    Province p = new Province(rs.getInt(1), rs.getString(2), rs.getString(3));
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
    
    /* Getters */
    public int get_id_province() {
        return id_province;
    }
    public String get_nom_province() {
        return nom_province;
    }
    public String get_description() {
        return description;
    }
    
    /* Setters */
    public void set_id_province(int id_province) {
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
            System.out.println(ls.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
