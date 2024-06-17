package model.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Database{

    /* Fonction pour se connecter au database */
    public static Connection get_connection(){
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost/abeona", "sanda", "huhu2005!!");
        }catch(Exception e){
            System.out.println("Erreur");
            e.printStackTrace();
        }
        return c;
    }

    /* Encodage du mot de passe */
        public static String encoder(String str){
        String result = "";
        Connection c =null; 
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            c = Database.get_connection();
            st = c.prepareStatement("SELECT digest(?,'sha1') AS encoded");
            st.setString(1, str);
            rs = st.executeQuery();
            while(rs.next()){
                result += rs.getString("encoded");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
                if(st != null) st.close();
                if(c != null) c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
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