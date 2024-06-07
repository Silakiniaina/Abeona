package model.shared;

import java.sql.Connection;
import java.sql.DriverManager;


public class Database{

    /* Fonction pour se connecter au database */
    public static Connection get_connection(){
       Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost/abeona", "sanda", "Sanda120705");
        }catch(Exception e){
            System.out.println("Erreur");
            e.printStackTrace();
        }
        return c;
    }

    public static void main(String[] argv ){
        Connection  c = Database.get_connection();
        if(c != null){
            System.out.println("Not null");
        }else{
            System.out.println("Utilisateur null");
        }
    }
}