package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.shared.Database;

public class Utilisateur{
    private int id; 
    private String nom;
    private String prenom;
    private String email;
    private Date date_de_naissance;
    private int id_genre;

    /* Constructor */
    public Utilisateur(int id, String nom, String prenom, String mail, Date dtn, int id_g){
        this.set_id(id);
        this.set_nom(nom);
        this.set_prenom(prenom);
        this.set_email(mail);
        this.set_date_de_naissance(dtn);
        this.set_id_genre(id_g);
    }

    public Utilisateur(String nom, String prenom, String mail, Date dtn, int id_g){
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
                resultat = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6));
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
    

    /* Setters */
    public void set_id(int n_id){
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
    public void set_id_genre(int id){
        this.id_genre = id;
    }

    /* Getters */
    public int get_id(){
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
    public int get_id_genre(){
        return this.id_genre;
    }

    public static void main(String[] argv ){
        Utilisateur u = Utilisateur.login("sandasilakiniaina4@gmail.com","admin");
        if(u != null){
            System.out.println("Connected successfully as "+u.get_nom());
        }else{
            System.out.println("Not connected");
        }
    }
}