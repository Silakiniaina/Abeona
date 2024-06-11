package model.client;

public class Attraction {
    private String id_attraction;
    private String nom_attraction;

    /* Constructor */
    public Attraction(String id, String name){
        this.set_id_attraction(id);
        this.set_nom_attraction(name);
    }
    
    /* Fonction pour rechercher des attraction */
    public static ArrayList<Attraction> rechercher_attraction(String dest){
        
    }
    /* Getters */
    public String get_id_attraction() {
        return id_attraction;
    }
    public String get_nom_attraction() {
        return nom_attraction;
    }

    /* Setters */
    public void set_id_attraction(String id_attraction) {
        this.id_attraction = id_attraction;
    }
    public void set_nom_attraction(String nom_attraction) {
        this.nom_attraction = nom_attraction;
    }
}
