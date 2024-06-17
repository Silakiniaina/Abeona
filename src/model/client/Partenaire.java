package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public abstract class Partenaire {
    String id;
    String nom;
    String id_partenaire;
    String description;
    double evaluation;

    public abstract String get_categorie_avis();
    public abstract String get_categorie_evaluation();
    public abstract String get_categorie_reservation();

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

    /* Fonction pour avoir la liste des reservations */
    public ArrayList<Reservation> get_liste_reservation() throws Exception{
        ArrayList<Reservation> resultat = new ArrayList<Reservation>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM reservation WHERE id_categorie_reservation = ? AND id_partenaire = ?");
            prstm.setString(1, this.get_categorie_reservation());
            prstm.setString(2, this.get_id());
            rs = prstm.executeQuery();
            while(rs.next()){
                Reservation a = new Reservation(rs.getDate(2), rs.getDate(3),rs.getInt(4),rs.getString(7),rs.getString(8),rs.getString(9));
                a.set_id_reservation(rs.getString(1));
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

    /* Fonction pour voir si le partenaire est disponible entre deux dates */
    public boolean chech_disponibilite(Date debut, Date fin)throws Exception{
        boolean result = true;
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM reservation WHERE id_categorie_reservation = ? AND id_partenaire = ? AND ((date_debut_reservation BETWEEN ? AND ?) OR (date_fin_reservation BETWEEN ? AND ? ))");
            prstm.setString(1, this.get_categorie_reservation());
            prstm.setString(2, this.get_id());
            prstm.setDate(3, debut);
            prstm.setDate(4, fin);
            prstm.setDate(5, debut);
            prstm.setDate(6, fin);
            rs = prstm.executeQuery();
            if(rs.next()){
                result = false;
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null) rs.close();
            if(prstm != null) prstm.close();
            if(c != null) c.close();
        }
        return result;
    }

    /* Fonction pour accepter ou decliner une inscription partenaire via son id */
    public static void valider_inscription(String id, boolean valider) throws Exception{
        Connection c = null; 
        PreparedStatement prstm = null; 
        try{
            c = Database.get_connection();
            c.setAutoCommit(false);
            if(valider)prstm = c.prepareStatement("UPDATE partenaire SET status = 1 WHERE id_partenaire  = ? ");
            else if(!valider) prstm =  c.prepareStatement("DELETE FROM partenaire WHERE id_partenaire = ?");
            prstm.setString(1, id);
            prstm.executeUpdate();
            c.commit();
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
            if(prstm != null) prstm.close();
            if(c != null ) c.close();
        }
    }

    /* Fonction pour avoir le nombre des partenaires */
    

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
    public double get_evaluation(){
        return this.evaluation;
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
    public void set_evaluation(double ev){
        this.evaluation = ev;
    }


    /* Test */
    public static void main(String[] args) {
        try {
            Partenaire.valider_inscription("PAR1", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
