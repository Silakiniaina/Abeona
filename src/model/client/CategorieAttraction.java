package model.client;

public class CategorieAttraction {
    private int id_categorie_attraction;
    private String libelle;

    /* Constructors */
    public CategorieAttraction(int id,String lib){
        this.set_id_categorie_attraction(id);
        this.set_libelle(lib);
    }

    public CategorieAttraction(String lib){
        this.set_libelle(lib);
    }
    
    /* Getters */
    public int get_id_categorie_attraction() {
        return id_categorie_attraction;
    }
    public String get_libelle() {
        return libelle;
    }
    
    /* Setters */
    public void set_id_categorie_attraction(int id_categorie_attraction) {
        this.id_categorie_attraction = id_categorie_attraction;
    }
    public void set_libelle(String libelle) {
        this.libelle = libelle;
    }
    
}
