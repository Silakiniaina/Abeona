package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    static int EVENEMENT_PASSE = 0;
    static int EVENEMENT_FUTUR = 1;
    static int EVENEMENT_ENCOURS = 2;
    static int EVENEMENT_ALL = -1;

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
                if(type == 0){
                    prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier_passe");
                }else if( type == 1){
                    prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier_futur");
                }else if(type == 2){
                    prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier_en_cours");
                }else if(type == -1){
                    prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier");
                }
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
                resultat = new Evenement(rs.getString(5), rs.getString(2), rs.getString(3), rs.getDate(9), rs.getDate(10), rs.getString(6), rs.getString(8), rs.getString(7));
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
            ArrayList<Evenement> ls = Evenement.get_liste_evenement_calendrier(EVENEMENT_ALL);
            for(Evenement e : ls){
                System.out.println("id : "+e.get_id_evenement());
                System.out.println("Titre : "+e.get_nom_evenement());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}