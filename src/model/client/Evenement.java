package model.client;

import java.sql.Date;

public class Evenement{
    private int id_evenement;
    private String nom_evenement;
    private Date date_evenement;
    private String description;
    private String ville; 
    private Hotel hotel;

    /* Constructor */
    public Evenement(String nom, Date dt, String desc,  String ville, Hotel h){
        this.set_nom_evenement(nom);
        this.set_date_evenement(dt);
        this.set_description(desc);
        this.set_ville(ville);
        this.set_hotel(h);
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
    
}