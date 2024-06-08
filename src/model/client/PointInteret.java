package model.client;

public class PointInteret{
    private int id_point_interet;
    private String libelle;

    /* Constructors */
    public PointInteret(int id, String libelle){
        this.set_id_point_interet(id);
        this.set_libelle(libelle);
    }

    /* Getters */
    public int get_id_point_interet() {
        return id_point_interet;
    }
    public String get_libelle() {
        return libelle;
    }
    
    /* Setters */
    public void set_id_point_interet(int id_point_interet) {
        this.id_point_interet = id_point_interet;
    }
    public void set_libelle(String libelle) {
        this.libelle = libelle;
    }
    
}