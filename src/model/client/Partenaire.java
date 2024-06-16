package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public abstract class Partenaire {
    String id;
    String nom;
    String id_partenaire;
    String description;

    public abstract String get_categorie_avis();
    public abstract String get_categorie_evaluation();
    public abstract double get_evaluation()throws Exception;

    /* Fonction permettant d'avoir la liste des avis sur une partenaire specifique */
    public ArrayList<Avis> get_liste_avis() throws Exception {
        ArrayList<Avis> resultat = new ArrayList<Avis>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM avis WHERE id_categorie_avis = ? AND id_partenaire = ?");
            prstm.setString(1, this.get_categorie_avis());
            prstm.setString(2, this.get_id());
            rs = prstm.executeQuery();
            while(rs.next()){
                Avis a = new Avis(rs.getString(5), rs.getString(2), rs.getTimestamp(4));
                resultat.add(a);
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

    /* Fonction permettant d'avoir la liste des evaluations sur une partenaire specifique */
    public ArrayList<Evaluation> get_liste_evaluation() throws Exception{
        ArrayList<Evaluation> resultat = new ArrayList<Evaluation>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM evaluation WHERE id_categorie_evaluation = ? AND id_partenaire = ?");
            prstm.setString(1, this.get_categorie_evaluation());
            prstm.setString(2, this.get_id());
            rs = prstm.executeQuery();
            while(rs.next()){
                Evaluation a = new Evaluation(rs.getString(5), rs.getDouble(2), rs.getTimestamp(4));
                resultat.add(a);
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null){ rs.close(); }
            if(prstm != null){ prstm.close(); }
            if(c != null){ c.close(); }
        }
        return resultat;
    };

    /* Getters */
    public String get_id() {
        return id;
    }
    public String get_nom() {
        return nom;
    }
    public String get_id_partenaire() {
        return id_partenaire;
    }
    public String get_description() {
        return description;
    }

    /* Setters */
    public void set_id(String id) {
        this.id = id;
    }
    public void set_nom(String nom) {
        this.nom = nom;
    }
    public void set_id_partenaire(String id_partenaire) {
        this.id_partenaire = id_partenaire;
    }
    public void set_description(String description) {
        this.description = description;
    }

}
