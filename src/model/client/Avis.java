package model.client;

import java.sql.Timestamp;

public class Avis {
    private Utilisateur utilisateur;
    private String avis_utilisateur;
    private Timestamp date_insertion;

    /* Constructor */
    public Avis(String id_usr, String avis, Timestamp date)throws Exception{
        this.set_utilisateur(Utilisateur.get_utilisateur_par_id(null,id_usr));
        this.set_avis_utilisateur(avis);
        this.set_date_insertion(date);
    }

    /* Getters */
    public Utilisateur get_utilisateur() {
        return utilisateur;
    }
    public String get_avis_utilisateur() {
        return avis_utilisateur;
    }
    public Timestamp get_date_insertion() {
        return date_insertion;
    }

    /* Setters */
    public void set_utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public void set_avis_utilisateur(String avis_utilisateur) {
        this.avis_utilisateur = avis_utilisateur;
    }
    public void set_date_insertion(Timestamp date_insertion) {
        this.date_insertion = date_insertion;
    }
}
