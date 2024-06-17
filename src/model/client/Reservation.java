package model.client;

import java.sql.Date;

public class Reservation {
    private String id_reservation;
    private Date date_debut_reservation;
    private Date date_fin_reservation;
    private int nombre_personne;
    private Utilisateur utilisateur;
    private String id_categorie_reservation;
    private String id_partenaire;
    
    /* Constructors */
    public Reservation(Date dd, Date df, int nb_pers, String id_user, String id_c, String id_p)throws Exception{
        this.set_date_debut_reservation(dd);
        this.set_date_fin_reservation(df);
        this.set_nombre_personne(nb_pers);
        this.set_utilisateur(Utilisateur.get_utilisateur_par_id(null, id_user));
        this.set_id_categorie_reservation(id_c);
        this.set_id_partenaire(id_p);
    }

    /* Setters */
    public void set_id_reservation(String id_reservation) {
        this.id_reservation = id_reservation;
    }
    public void set_date_debut_reservation(Date date_debut_reservation) {
        this.date_debut_reservation = date_debut_reservation;
    }
    public void set_date_fin_reservation(Date date_fin_reservation) {
        this.date_fin_reservation = date_fin_reservation;
    }
    public void set_nombre_personne(int nombre_personne) {
        this.nombre_personne = nombre_personne;
    }
    public void set_utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public void set_id_categorie_reservation(String id_categorie_reservation) {
        this.id_categorie_reservation = id_categorie_reservation;
    }
    public void set_id_partenaire(String partenaire) {
        this.id_partenaire = partenaire;
    }
    
    /* Getters */
    public String get_id_reservation() {
        return id_reservation;
    }
    public Date get_date_debut_reservation() {
        return date_debut_reservation;
    }
    public Date get_date_fin_reservation() {
        return date_fin_reservation;
    }
    public int get_nombre_personne() {
        return nombre_personne;
    }
    public Utilisateur get_utilisateur() {
        return utilisateur;
    }
    public String get_id_categorie_reservation() {
        return id_categorie_reservation;
    }
    public String get_partenaire() {
        return id_partenaire;
    }
}
