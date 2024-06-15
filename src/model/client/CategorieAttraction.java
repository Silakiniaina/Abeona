package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class CategorieAttraction {
    private String id_categorie_attraction;
    private String libelle;

    /* Constructors */
    public CategorieAttraction(String id,String lib){
        this.set_id_categorie_attraction(id);
        this.set_libelle(lib);
    }

    public CategorieAttraction(String lib){
        this.set_libelle(lib);
    }

    /* Liste des choix pour les preferences */
    public static ArrayList<CategorieAttraction> get_list_categorie_attraction()throws Exception{
        ArrayList<CategorieAttraction> resultat = new ArrayList<CategorieAttraction>();
        Connection c = null; 
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        try {
            c = Database.get_connection();
            prsmt = c.prepareStatement("SELECT * FROM categorie_attraction");
            rs = prsmt.executeQuery();
            while (rs.next()) {
                CategorieAttraction temp = new CategorieAttraction(rs.getString(1), rs.getString(2));
                resultat.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs != null){ rs.close(); }
            if(prsmt != null){ prsmt.close(); }
            if(c != null){ c.close(); }
        }
        return resultat;
    }

    /* Fonction pour avoir une categorie id par son Id */
    public static CategorieAttraction get_categorie_attraction_par_id(Connection con, String id)throws Exception{
        CategorieAttraction resultat = null;
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
            prsmt = c.prepareStatement("SELECT * FROM categorie_attraction WHERE id_categorie_attraction = ? ");
            prsmt.setString(1,id);
            rs = prsmt.executeQuery();
            if (rs.next()) {
                resultat = new CategorieAttraction(rs.getString(1), rs.getString(2));
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
    public String get_id_categorie_attraction() {
        return id_categorie_attraction;
    }
    public String get_libelle() {
        return libelle;
    }
    
    /* Setters */
    public void set_id_categorie_attraction(String id_categorie_attraction) {
        this.id_categorie_attraction = id_categorie_attraction;
    }
    public void set_libelle(String libelle) {
        this.libelle = libelle;
    }

    public static void main(String[] args) {
        try {
            CategorieAttraction c = CategorieAttraction.get_categorie_attraction_par_id(null,"CTA2");
            System.out.println("Nom : "+c.get_libelle());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}