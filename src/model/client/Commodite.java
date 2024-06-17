package model.client;

public class Commodite {
    private String id_commodite;
    private String libelle;
    
    /* Constructors */
    public Commodite(String lib){
        this.set_libelle(lib);
    }
    
    /* Getters */
    public void set_id_commodite(String id_commodite) {
        this.id_commodite = id_commodite;
    }
    public void set_libelle(String libelle) {
        this.libelle = libelle;
    }
    
    /* Setters */
    public String get_id_commodite() {
        return id_commodite;
    }
    public String get_libelle() {
        return libelle;
    }
}
