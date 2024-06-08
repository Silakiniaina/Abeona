package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Evenement{
    private int id_evenement;
    private String nom_evenement;
    private Date date_evenement;
    private String description;
    private String ville; 
    private Hotel hotel;

    static int EVENEMENT_PASSE = 0;
    static int EVENEMENT_FUTUR = 1;

    /* Constructor */
    public Evenement(String nom, Date dt, String desc,  String ville, Hotel h){
        this.set_nom_evenement(nom);
        this.set_date_evenement(dt);
        this.set_description(desc);
        this.set_ville(ville);
        this.set_hotel(h);
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
                    Evenement ev = new Evenement(rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),null);
                    ev.set_id_evenement(rs.getInt(1));
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
    
    /* Getters */
    public int get_id_evenement() {
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
    public void set_id_evenement(int id_evenement) {
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
            ArrayList<Evenement> ls = Evenement.get_liste_evenement_calendrier(Evenement.EVENEMENT_FUTUR);
            for(Evenement ev : ls){
                System.out.println("nom : "+ev.get_nom_evenement()+" , date : "+ev.get_date_evenement());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}