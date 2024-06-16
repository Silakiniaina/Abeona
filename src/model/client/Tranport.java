package model.client;

public class Tranport extends Partenaire{
    private double tarif;
    private String id_categorie_transport;

    /* Constructor */
    public Tranport(String name, String desc, double tartif, String id_c,String id_p){
        this.set_nom(name);
        this.set_description(desc);
        this.set_tarif(tartif);
        this.set_id_categorie_transport(id_c);
        this.set_id_partenaire(id_p);
    }

    /* Getters */
    public double get_tarif() {
        return tarif;
    }
    public String get_id_categorie_transport() {
        return id_categorie_transport;
    }
    
    /* Setters */
    public void set_tarif(double tarif) {
        this.tarif = tarif;
    }
    public void set_id_categorie_transport(String id_categorie_transport) {
        this.id_categorie_transport = id_categorie_transport;
    }

    /* Surdefinition fonction partenaire */
    @Override
    public String get_categorie_avis() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String get_categorie_evaluation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String get_categorie_reservation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double get_evaluation() throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
