package model.client;

public class Hotel {
    private int id_hotel; 
    private String nom_hotel;

    /* Constructor */
    public Hotel(String nom){
        this.set_nom_hotel(nom);
    }

    public Hotel(int id, String nom){
        this.set_id_hotel(id);
        this.set_nom_hotel(nom);
    }

    /* Getters */
    public int get_id_hotel() {
        return id_hotel;
    }
    public String get_nom_hotel() {
        return nom_hotel;
    }

    /* Setters */
    public void set_id_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }
    public void set_nom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    
}
