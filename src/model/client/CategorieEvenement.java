package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class CategorieEvenement {
    private String id_categorie_evenement;
    private String libelle;

    /* Constructors */
    public CategorieEvenement(String id, String str){
        this.set_id_categorie_evenement(id);
        this.set_libelle(str);
    }

    /* Fonction pour avoir la liste des categorie evenement */
    public static ArrayList<CategorieEvenement> get_liste_categorie_evenement()throws Exception{
        ArrayList<CategorieEvenement> resultat = new ArrayList<CategorieEvenement>();
        Connection c = null; 
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        try {
            c = Database.get_connection();
            prsmt = c.prepareStatement("SELECT * FROM categorie_evenement");
            rs = prsmt.executeQuery();
            while (rs.next()) {
                CategorieEvenement temp = new CategorieEvenement(rs.getString(1), rs.getString(2));
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
    
    /* Getters */
    public String get_id_categorie_evenement() {
        return id_categorie_evenement;
    }
    public String get_libelle() {
        return libelle;
    }
    
    /* Setters */
    public void set_id_categorie_evenement(String id_categorie_evenement) {
        this.id_categorie_evenement = id_categorie_evenement;
    }
    public void set_libelle(String libelle) {
        this.libelle = libelle;
    }
}
