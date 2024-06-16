package model.client;

import java.sql.Timestamp;

public class Evaluation {
    private Utilisateur utilisateur;
    private double evaluation;
    private Timestamp date_insertion;

    /* Constructor */
    public Evaluation(String id_usr, double evaluation, Timestamp date)throws Exception{
        this.set_utilisateur(Utilisateur.get_utilisateur_par_id(null,id_usr));
        this.set_evaluation(evaluation);
        this.set_date_insertion(date);
    }
    

    /* Getters */
    public Utilisateur get_utilisateur() {
        return utilisateur;
    }
    public double get_evaluation() {
        return evaluation;
    }
    public Timestamp get_date_insertion() {
        return date_insertion;
    }

    /* Setters */
    public void set_utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public void set_evaluation(double ev) {
        this.evaluation = ev;
    }
    public void set_date_insertion(Timestamp date_insertion) {
        this.date_insertion = date_insertion;
    }
}
