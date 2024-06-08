package model.client;

public class Province {
    private int id_province;
    private String nom_province;
    private String description;

    /* Construteurs */
    public Province(int id, String nom, String desc){
        this.set_id_province(id);
        this.set_nom_province(nom);
        this.set_description(desc);
    }
    
    /* Getters */
    public int get_id_province() {
        return id_province;
    }
    public String get_nom_province() {
        return nom_province;
    }
    public String get_description() {
        return description;
    }
    
    /* Setters */
    public void set_id_province(int id_province) {
        this.id_province = id_province;
    }
    public void set_nom_province(String nom_province) {
        this.nom_province = nom_province;
    }
    public void set_description(String description) {
        this.description = description;
    }
    
}
