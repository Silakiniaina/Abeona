package model.client;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Partenaire {
    String id;
    String nom;
    String id_partenaire;
    String description;

    public abstract String get_categorie_avis();
    public abstract String get_categorie_evaluation();

    /* Fonction permettant d'avoir la liste des avis sur une partenaire specifique */
    public abstract ArrayList<Avis> get_liste_avis() throws Exception;

    /* Fonction permettant d'avoir la liste des evaluations sur une partenaire specifique */
    public abstract ArrayList<Evaluation> get_liste_evaluation() throws Exception;

    /* Getters */
    public String get_id() {
        return id;
    }
    public String get_nom() {
        return nom;
    }
    public String get_id_partenaire() {
        return id_partenaire;
    }
    public String get_description() {
        return description;
    }

    /* Setters */
    public void set_id(String id) {
        this.id = id;
    }
    public void set_nom(String nom) {
        this.nom = nom;
    }
    public void set_id_partenaire(String id_partenaire) {
        this.id_partenaire = id_partenaire;
    }
    public void set_description(String description) {
        this.description = description;
    }

}
