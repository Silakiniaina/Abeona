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
    private Date date_evenement;
    private String description;
    private String ville; 
    private Hotel hotel;

    static int EVENEMENT_PASSE = 0;
    static int EVENEMENT_FUTUR = 1;

    /* Constructor */
    public Evenement(String nom, Date dt, String desc,  String ville){
        this.set_nom_evenement(nom);
        this.set_date_evenement(dt);
        this.set_description(desc);
        this.set_ville(ville);
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
                }else{
                    prsmt = c.prepareStatement("SELECT * FROM v_evenement_calendrier");
                }
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Evenement ev = new Evenement(rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5));
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

    /* Fonction pour avoir une categorie id par son Id */
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
                resultat = new Evenement(rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5));
                if(rs.getInt(6) != 0){
                    resultat.set_hotel(Hotel.get_hotel_par_id(c,rs.getString(6)));
                }
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
    public Date get_date_evenement() {
        return date_evenement;
    }
    public String get_description() {
        return description;
    }
    public String get_ville() {
        return ville;
    }
    public Hotel get_hotel() {
        return hotel;
    }
    
    /* Setters */
    public void set_id_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }
    public void set_nom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }
    public void set_date_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }
    public void set_description(String description) {
        this.description = description;
    }
    public void set_ville(String ville) {
        this.ville = ville;
    }
    public void set_hotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /* Test */
    public static void main(String[] args) {
        try{
            ArrayList<Attraction> ls = Attraction.rechercher_attraction("Tour ");
            for(Attraction ev : ls){
                System.out.println("nom : "+ev.get_nom_attraction());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}