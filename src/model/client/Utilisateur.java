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
    public Utilisateur(String nom, String prenom, String mail, Date dtn, String id_g){
        this.set_nom(nom);
        this.set_prenom(prenom);
        this.set_email(mail);
        this.set_date_de_naissance(dtn);
        this.set_id_genre(id_g);
    }

    /* Fonction pour avoir un utilisateur via son id */
    public static Utilisateur get_utilisateur_par_id(Connection con, String id)throws Exception{
        Utilisateur resultat = null;
        Connection c = null;
        PreparedStatement prsmt = null;
        ResultSet rs = null;
        boolean est_nouvelle_connexion = false;
        try{
            if(con == null){    
                c = Database.get_connection(); 
                est_nouvelle_connexion = true;
            }
            else { c = con; }
            prsmt = c.prepareStatement("SELECT id_utilisateur,nom_utilisateur,prenom_utilisateur,email,date_de_naissance,id_genre FROM utilisateur WHERE id_utilisateur = ? ");
            prsmt.setString(1, id);
            rs = prsmt.executeQuery();
            if(rs.next()){
                resultat = new Utilisateur(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6));
                resultat.set_id(rs.getString(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs != null){ rs.close(); }
            if(prsmt != null){ prsmt.close(); }
            if(est_nouvelle_connexion){ c.close(); }
        }
        return resultat;
    }

    /* Fonction pour se connecter un utilisateur */
    public static Utilisateur login(String email, String password)throws Exception{
        Utilisateur resultat = null;
        Connection c = null;
        PreparedStatement prsmt = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prsmt = c.prepareStatement("SELECT id_utilisateur,nom_utilisateur,prenom_utilisateur,email,date_de_naissance,id_genre FROM utilisateur WHERE email = ? AND mot_de_passe = ? ");
            prsmt.setString(1,email);
            prsmt.setString(2, Database.encoder(password));
            rs = prsmt.executeQuery();
            if(rs.next()){
                resultat = new Utilisateur(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6));
                resultat.set_id(rs.getString(1));
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
    
    /* Fonction pour donner un avis a propos d'une partenaire */
    public void donner_avis(Partenaire p, String avis)throws Exception{
        Connection c = null;
        PreparedStatement prsmt = null; 
        try{
            if(avis == null){
                throw new Exception("Avis null");
            }
            c = Database.get_connection();
            c.setAutoCommit(false);
            prsmt = c.prepareStatement("INSERT INTO avis(id_utilisateur,avis_utilisateur,id_categorie_avis,id_partenaire) VALUES (?,?,?,?)");
            prsmt.setString(1, this.get_id());
            prsmt.setString(2, avis);
            prsmt.setString(3, p.get_categorie_avis());
            prsmt.setString(4, p.get_id());
            prsmt.executeUpdate();
            c.commit();
            System.out.println("Don avis fait :)");
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
            if(prsmt != null){ prsmt.close(); }
            if(c != null){ c.close(); }
        }
    }

    /* Fonction pour donner une evaluation a une partenaire */
    public void donner_evaluation(Partenaire p, double evaluation)throws Exception{
        Connection c = null;
        PreparedStatement prsmt = null; 
        try{
            if(evaluation < 0 || evaluation > 5){
                throw new Exception("Valeur evaluation invalide");
            }
            c = Database.get_connection();
            c.setAutoCommit(false);
            prsmt = c.prepareStatement("INSERT INTO evaluation(id_utilisateur,evaluation,id_categorie_evaluation,id_partenaire) VALUES (?,?,?,?)");
            prsmt.setString(1, this.get_id());
            prsmt.setDouble(2, evaluation);
            prsmt.setString(3, p.get_categorie_evaluation());
            prsmt.setString(4, p.get_id());
            prsmt.executeUpdate();
            c.commit();
            System.out.println("Don evaluation fait :)");
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
            if(prsmt != null){ prsmt.close(); }
            if(c != null){ c.close(); }
        }
    }

    /* Fonction pour reserver  */
    public void reserver(Partenaire p,Date dtb, Date dtf, int nb_pers)throws Exception{
        Connection c = null;
        PreparedStatement prstm = null;
        try {
            c = Database.get_connection();
            c.setAutoCommit(false);
            prstm = c.prepareStatement("INSERT INTO reservation(date_debut_reservation,date_fin_reservation,nombre_personne,id_utilisateur,id_categorie_reservation,id_partenaire) VALUES (?, ?, ?, ?, ?, ?)");
            prstm.setDate(1, dtb);
            prstm.setDate(2, dtf);
            prstm.setInt(3, nb_pers);
            prstm.setString(4,this.get_id());
            prstm.setString(5, p.get_categorie_reservation());
            prstm.setString(6, p.get_id());
            prstm.executeUpdate();
            c.commit();
            System.out.println("Rerervation fait");
        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            if(prstm != null){ prstm.close(); }
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
            Utilisateur u = Utilisateur.login("pierre.martin1@example.com", "12345");
            if(u != null){
                Attraction a = Attraction.get_attraction_par_id(null, "ATT1");
                u.reserver(a, Date.valueOf("2023-06-12"), Date.valueOf("2023-06-24"), 5);
            }else{
                System.out.println("User null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}