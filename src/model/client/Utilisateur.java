package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Utilisateur{
    private String id; 
    private String nom;
    private String prenom;
    private String email;
    private Date date_de_naissance;
    private String id_genre;
    protected String mot_de_passe;

    /* Constructor */
    public Utilisateur(String id, String nom, String prenom, String mail, Date dtn, String id_g){
        this.set_id(id);
        this.set_nom(nom);
        this.set_prenom(prenom);
        this.set_email(mail);
        this.set_date_de_naissance(dtn);
        this.set_id_genre(id_g);
    }

    public Utilisateur(String nom, String prenom, String mail, Date dtn, String id_g){
        this.set_nom(nom);
        this.set_prenom(prenom);
        this.set_email(mail);
        this.set_date_de_naissance(dtn);
        this.set_id_genre(id_g);
    }

    /* Fonction pour se connecter un utilisateur */
    public static Utilisateur login(String email, String password)throws Exception{
        Utilisateur resultat = null;
        Connection c = null;
        PreparedStatement prsmt = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prsmt = c.prepareStatement("SELECT id_utilisateur,nom_utilisateur,prenom_utilisateur,email,date_naissance,id_genre FROM utilisateur WHERE email = ? AND mot_de_passe = ? ");
            prsmt.setString(1,email);
            prsmt.setString(2, Database.encoder(password));
            rs = prsmt.executeQuery();
            if(rs.next()){
                resultat = new Utilisateur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs != null){ rs.close(); }
            if(prsmt != null){ prsmt.close(); }
            if(c != null){ c.close(); }
        }
        return resultat;
    }

    /* Fonction pour inscrire un utilisateur */
    public void inscrire()throws Exception{
        Connection c = null; 
        PreparedStatement prsmt = null; 
        try{
            c = Database.get_connection();
            c.setAutoCommit(false);
            prsmt = c.prepareStatement("INSERT INTO Utilisateur(nom_utilisateur,prenom_utilisateur,email,date_naissance,id_genre,id_categorie_utilisateur,mot_de_passe) VALUES (?,?,?,?,?,?,digest(?,'sha1'))");
            prsmt.setString(1, this.get_nom());
            prsmt.setString(2, this.get_prenom());
            prsmt.setString(3, this.get_email());
            prsmt.setDate(4, this.get_date_de_naissance());
            prsmt.setString(5, this.get_id_genre());
            prsmt.setString(6, "CTU1");
            prsmt.setString(7,this.get_mot_de_passe());
            prsmt.executeUpdate();
            c.commit();
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
            if(prsmt != null){ prsmt.close(); }
            if(c != null){ c.close(); }
        }
    }

    /* Fonction pour inserer les preferences choisis */
    public void inserer_preferences(ArrayList<String>  ls)throws Exception{
        Connection c = null;
        PreparedStatement prsmt = null; 

        try{
            c = Database.get_connection();
            c.setAutoCommit(false);
            prsmt = c.prepareStatement("INSERT INTO preference_utilisateur(id_utilisateur, id_categorie_attraction) VALUES (?,?)");
            prsmt.setString(1, this.get_id());
            for(String id : ls){
                prsmt.setString(2,id);
                prsmt.executeUpdate();
            }
            c.commit();
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
            if(prsmt != null){ prsmt.close(); }
            if(c != null){ c.close(); }
        }
    }
    

    /* Setters */
    public void set_id(String n_id){
        this.id = n_id;
    }
    public void set_nom(String str){
        this.nom = str;
    }
    public void set_prenom(String str){
        this.prenom = str;
    }
    public void set_email(String mail){
        this.email = mail;
    }
    public void set_date_de_naissance(Date dt){
        this.date_de_naissance = dt;
    }
    public void set_id_genre(String id){
        this.id_genre = id;
    }
    public void set_mot_de_passe(String str){
        this.mot_de_passe = str;
    }

    /* Getters */
    public String get_id(){
        return this.id;
    }
    public String get_nom(){
        return this.nom;
    }
    public String get_prenom(){
        return this.prenom;
    }
    public String get_email(){
        return this.email;
    }
    public Date get_date_de_naissance(){
        return this.date_de_naissance;
    }
    public String get_id_genre(){
        return this.id_genre;
    }
    public String get_mot_de_passe(){
        return this.mot_de_passe;
    }

    public static void main(String[] argv ){
        try {
            // ArrayList<CategorieAttraction> ls = CategorieAttraction.get_list_categorie_attraction();
            Utilisateur u = Utilisateur.login("sandasilakiniaina4@gmail.com", "admin");
            if(u != null){
                System.out.println("Connecter en tant que : "+u.get_nom());
                System.out.println("id : "+u.get_id());
            }
            // u.inserer_preferences(ls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}