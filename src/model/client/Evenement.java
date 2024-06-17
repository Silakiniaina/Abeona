package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.print.DocFlavor.STRING;

import model.shared.Database;

public class Evenement{
    private String id_evenement;
    private String nom_evenement;
    private Date date_debut_evenement;
    private Date date_fin_evenement;
    private String lieu_evenement;
    private String description;
    private String ville; 
    private String id_hotel;
    private String id_categorie_evenement;

    public static int EVENEMENT_CALENDRIER_PASSE = 0;
    public static int EVENEMENT_CALENDRIER_FUTUR = 1;
    public static int EVENEMENT_CALENDRIER_ENCOURS = 2;
    public static int EVENEMENT_ALL = -1;
    public static int EVENEMENT_CET_ANNEE = 5;
    public static int EVENEMENT_CET_MOIS = 6;
    public static int EVENEMENT_AUJOURDHUI = 7;

    /* Constructor */
    public Evenement(String titre, String desc, String lieu, Date date_debut, Date date_fin, String id_hotel, String id_categ, String id_ville){
        this.set_nom_evenement(titre);
        this.set_description(desc);
        this.set_lieu_evenement(lieu);
        this.set_date_debut_evenement(date_debut);
        this.set_date_fin_evenement(date_fin);
        this.set_id_hotel(id_hotel);
        this.set_id_categorie_evenement(id_categ);
        this.set_id_ville(id_ville);
    }

    /* Fonction pour avoir la liste des evenement du calendrier */
    public static ArrayList<Evenement> get_liste_evenement_calendrier(int type)throws Exception{
        ArrayList<Evenement> resultat = new ArrayList<Evenement>();
        Connection c = null;
        PreparedStatement prsmt  = null;
        ResultSet rs = null; 

        try{
            c = Database.get_connection();
            if(c != null){
                if(type == EVENEMENT_CALENDRIER_PASSE) prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier_passe");
                else if( type == EVENEMENT_CALENDRIER_FUTUR) prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier_futur");
                else if(type == EVENEMENT_CALENDRIER_ENCOURS) prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier_en_cours");
                else if(type == EVENEMENT_ALL) prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Evenement ev = new Evenement(rs.getString(5), rs.getString(2), rs.getString(3), rs.getDate(9), rs.getDate(10), rs.getString(6), rs.getString(8), rs.getString(7));
                    ev.set_id_evenement(rs.getString(1));
                    resultat.add(ev);
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

    /* Fonction pour avoir une categorie evenement par son Id */
    public static Evenement get_evenement_par_id(Connection con, String id)throws Exception{
        Evenement resultat = null;
        Connection c = null; 
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        boolean est_nouvelle_connexion = false;
        try {
            if(con == null){
                c = Database.get_connection();
                est_nouvelle_connexion = true;
            }else{
                c = con;
            }
            prsmt = c.prepareStatement("SELECT * FROM evenement WHERE id_evenement = ? ");
            prsmt.setString(1,id);
            rs = prsmt.executeQuery();
            if (rs.next()) {
                resultat = new Evenement(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(9), rs.getString(8));
                resultat.set_id_evenement(rs.getString(1));
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

    /* Fonction pour updater un evenement */
    public void update(Evenement evenement)throws Exception{
        Connection c = null;
        PreparedStatement prstm = null;
        try{
            c = Database.get_connection();
            c.setAutoCommit(false);
            prstm = c.prepareStatement("UPDATE evenement SET description = ? , lieu_evenement = ?, titre_evenement = ? , date_debut_evenement = ? , date_fin_evenement = ? , id_hotel = ?, id_ville = ? , id_categorie_evenement = ? , date_insertion = NOW( ) WHERE id_evenement = ? ");
            prstm.setString(1, evenement.get_description());
            prstm.setString(2, evenement.get_lieu_evenement());
            prstm.setString(3, evenement.get_nom_evenement());
            prstm.setDate(4, evenement.get_date_debut_evenement());
            prstm.setDate(5, evenement.get_date_fin_evenement());
            prstm.setString(6, evenement.get_id_hotel());
            prstm.setString(7, evenement.get_id_ville());
            prstm.setString(8, evenement.get_id_categorie_evenement());
            prstm.setString(9, this.get_id_evenement());
            prstm.executeUpdate();
            c.commit();
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
            if(prstm != null) prstm.close();
            if(c != null) prstm.close();
        }
    }

    /* Fonction pour inserer un evenement */
    public void inserer() throws Exception{
        Connection c = null;
        PreparedStatement prstm = null;
        try{
            c = Database.get_connection();
            c.setAutoCommit(false);
            prstm = c.prepareStatement("INSERT INTO evenement (description,lieu_evenement,titre_evenement,date_debut_evenement, date_fin_evenement,id_hotel,id_ville,id_categorie_evenement) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            prstm.setString(1, this.get_description());
            prstm.setString(2, this.get_lieu_evenement());
            prstm.setString(3, this.get_nom_evenement());
            prstm.setDate(4, this.get_date_debut_evenement());
            prstm.setDate(5, this.get_date_fin_evenement());
            prstm.setString(6, this.get_id_hotel());
            prstm.setString(7, this.get_id_ville());
            prstm.setString(8, this.get_id_categorie_evenement());
            prstm.executeUpdate();
            c.commit();
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
            if(prstm != null) prstm.close();
            if(c != null) prstm.close();
        }
    }

    /* Fonction pour supprimer un evenement */
    public void supprimer() throws Exception{
        Connection c = null;
        PreparedStatement prstm = null;
        try{
            c = Database.get_connection();
            c.setAutoCommit(false);
            prstm = c.prepareStatement("DELETE FROM evenement WHERE id_evenement  = ? ");
            prstm.setString(1, this.get_id_evenement());
            prstm.executeUpdate();
            c.commit();
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
            if(prstm != null) prstm.close();
            if(c != null) c.close();
        }
    }


    /* Fonction pour avoir les evenements ce mois ci */
    public static ArrayList<Evenement> get_evenement(int type) throws Exception{
        ArrayList<Evenement> result = new ArrayList<Evenement>();
        Connection c = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            if(type == EVENEMENT_CET_ANNEE)prstm = c.prepareStatement("SELECT * FROM v_evenement_cet_annee");
            else if(type == EVENEMENT_CET_MOIS)prstm = c.prepareStatement("SELECT * FROM v_evenement_cet_mois");
            else if(type == EVENEMENT_AUJOURDHUI)prstm = c.prepareStatement("SELECT * FROM v_evenement_aujourdhui");
            else if(type == EVENEMENT_ALL) prstm = c.prepareStatement("SELECT * FROM evenement");
            rs = prstm.executeQuery();
            while (rs.next()) {
                Evenement ev =  new Evenement(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(9), rs.getString(8));
                ev.set_id_evenement(rs.getString(1));
                result.add(ev);
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

    /* Fonction pour avoir les nombres des evenements */
    public static HashMap<String, Integer> get_nombre_evenement() throws Exception{
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM v_nombre_evenement");
            rs = prstm.executeQuery();
            if (rs.next()) {
                result.put("total", rs.getInt(1));
                result.put("annee", rs.getInt(2));
                result.put("mois", rs.getInt(3));
                result.put("jour", rs.getInt(4));
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
    /* Getters */
    public String get_id_evenement() {
        return id_evenement;
    }
    public String get_nom_evenement() {
        return nom_evenement;
    }
    public Date get_date_debut_evenement() {
        return date_debut_evenement;
    }
    public Date get_date_fin_evenement(){
        return date_fin_evenement;
    }
    public String get_description() {
        return description;
    }
    public String get_id_ville() {
        return ville;
    }
    public String get_id_hotel(){
        return id_hotel;
    }
    public String get_lieu_evenement(){
        return lieu_evenement;
    }
    public String get_id_categorie_evenement(){
        return this.id_categorie_evenement;
    }
    
    /* Setters */
    public void set_id_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }
    public void set_nom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }
    public void set_date_debut_evenement(Date date_evenement) {
        this.date_debut_evenement = date_evenement;
    }
    public void set_date_fin_evenement(Date dt){
        this.date_fin_evenement = dt;
    }
    public void set_description(String description) {
        this.description = description;
    }
    public void set_id_ville(String ville) {
        this.ville = ville;
    }
    public void set_id_hotel(String hotel) {
        this.id_hotel = hotel;
    }
    public void set_id_categorie_evenement(String id){
        this.id_categorie_evenement = id;
    }
    public void set_lieu_evenement(String l){
        this.lieu_evenement = l;
    }

    /* Test */
    public static void main(String[] args) {
        try{
            HashMap<String, Integer> ls = Evenement.get_nombre_evenement();
            System.out.println("total : "+ls.get("total"));
            System.out.println("annee : "+ls.get("annee"));
            System.out.println("mois : "+ls.get("mois"));
            System.out.println("jours : "+ls.get("jour"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}