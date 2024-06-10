package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Genre {
    private String id_genre; 
    private String libelle;

    /* Constructor */
    public Genre(String id_genre, String libelle){
        this.set_id_genre(id_genre);
        this.set_libelle(libelle);
    }

    /* Fonction pour avoir la liste des point d'interets dans une province */
    public static ArrayList<Genre> get_liste_genre()throws Exception{
        ArrayList<Genre> resultat = new ArrayList<Genre>();
        Connection c = null;
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM genre");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Genre g = new Genre(rs.getString(1), rs.getString(2));
                    resultat.add(g);
                }
            }else{
                throw new Exception("Aucune connexion");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null){ rs.close(); }
            if(prsmt != null){ prsmt.close(); }
            if(c != null){ c.close(); }
        }
        return resultat;
    }

    /* Fonction pour avoir la liste des point d'interets dans une province */
    public static Genre get_genre_par_id(Connection con, String id)throws Exception{
        Genre resultat = null;
        Connection c = null;
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        boolean est_nouvelle_connexion = false;
        try{
            if(con == null){
                c = Database.get_connection();
                est_nouvelle_connexion = true;
            }else{ c = con; }
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM genre WHERE id_genre = ?");
                prsmt.setString(1, id);
                rs = prsmt.executeQuery();
                if (rs.next()) {
                    resultat = new Genre(rs.getString(1), rs.getString(2));
                }
            }else{
                throw new Exception("Aucune connexion");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null){ rs.close(); }
            if(prsmt != null){ prsmt.close(); }
            if(est_nouvelle_connexion){ c.close(); }
        }
        return resultat;
    }
    
    /* Getters */
    public String get_id_genre() {
        return id_genre;
    }
    public String get_libelle() {
        return libelle;
    }
    
    /* Setters */
    public void set_id_genre(String id_genre) {
        this.id_genre = id_genre;
    }
    public void set_libelle(String libelle) {
        this.libelle = libelle;
    }

    public static void main(String[] args) {
        try{
            Genre g  = Genre.get_genre_par_id(null, "GEN2");
            System.out.println("libelle : "+g.get_libelle());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
