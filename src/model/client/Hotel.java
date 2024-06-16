package model.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.attribute.standard.Destination;

import model.shared.Database;

public class Hotel {
    private String id_hotel; 
    private String nom_hotel;
    private String description; 
    private String adress;
    private String id_partenaire;
    private String id_categorie_hotel;
    private String id_ville;
    private double evaluation;

    /* Constructor */
    public Hotel(String nom,String description,String adress, String id_p, String id_c, String id_v){
        this.set_nom_hotel(nom);
        this.set_description(description);
        this.set_adress(adress);
        this.set_id_partenaire(id_p);
        this.set_id_categorie_hotel(id_c);
        this.set_id_ville(id_v);
    }

    /* Fonction pour avoir une categorie id par son Id */
    public static Hotel get_hotel_par_id(Connection con , String id)throws Exception{
        Hotel resultat = null;
        Connection c = null; 
        PreparedStatement prsmt = null; 
        ResultSet rs = null; 
        boolean est_nouvelle_connexion = false;
        try {
            if(con == null){    
                c = Database.get_connection(); 
                est_nouvelle_connexion = true;
            }
            else { c = con; }
            prsmt = c.prepareStatement("SELECT * FROM hotel WHERE id_hotel = ? ");
            prsmt.setString(1,id);
            rs = prsmt.executeQuery();
            if (rs.next()) {
                resultat = new Hotel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8));
                resultat.set_id_hotel(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs != null){ rs.close(); }
            if(prsmt != null){ prsmt.close(); }
            if(est_nouvelle_connexion){ c.close(); }
        }
        return resultat;
    }

    /* Fonction pour rechercher des hotels */
    public static ArrayList<Hotel> rechercher_hotel(String dest, ArrayList<String> ville, ArrayList<String> categ,ArrayList<String> evaluation_id, ArrayList<String> commodite)throws Exception{
        ArrayList<Hotel> resultat = new ArrayList<Hotel>();
        Connection c = null;
        PreparedStatement prsmt  = null;
        ResultSet rs = null; 
        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM hotel WHERE LOWER(nom_hotel) LIKE ?");
                prsmt.setString(1, "%" +dest.toLowerCase()+ "%");
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Hotel a = new Hotel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8));
                    a.set_id_hotel(rs.getString(1));
                    resultat.add(a);
                }
            }else{
                throw new Exception("Aucune connexion ");
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

    /* FOnction pour avoir les tops dans dans un province */
    public static ArrayList<Hotel> get_top_hotel(Province p)throws Exception {
        ArrayList<Hotel> resultat = new ArrayList<Hotel>();
        Connection c = null;
        PreparedStatement prsmt  = null;
        ResultSet rs = null; 
        try{
            c = Database.get_connection();
            if(c != null){
                prsmt = c.prepareStatement("SELECT * FROM v_ranking_hotel_province WHERE id_province = ? LIMIT 3");
                prsmt.setString(1, p.get_id_province());
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Hotel a = new Hotel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8));
                    a.set_id_hotel(rs.getString(1));
                    a.set_evaluation(rs.getDouble(9));
                    resultat.add(a);
                }
            }else{
                throw new Exception("Aucune connexion ");
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

    /* Fonction pour filtrer les recherches en fonction des id recuperer */
    public static ArrayList<Hotel> filtrer_hotel(String dest,ArrayList<String> id_commodite, ArrayList<String> id_ville, ArrayList<String> evaluation) {
        ArrayList<Hotel> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = Database.get_connection();
            String sql = filter_query(dest,id_commodite, id_ville, evaluation);
            preparedStatement = connection.prepareStatement(sql);
            int paramIndex = 0;
            if(dest != null){
                preparedStatement.setString(paramIndex++, dest.toLowerCase());
            }
            if (id_commodite != null && !id_commodite.isEmpty()) {
                for (String commodite : id_commodite) {
                    preparedStatement.setString(paramIndex++, commodite);
                }
            }
            if (evaluation != null && !evaluation.isEmpty()) {
                for (String range : evaluation) {
                    String[] limits = range.split("-");
                    preparedStatement.setDouble(paramIndex++, Double.parseDouble(limits[0])); // min evaluation
                    preparedStatement.setDouble(paramIndex++, Double.parseDouble(limits[1])); // max evaluation
                }
            }
            if (id_ville != null && !id_ville.isEmpty()) {
                for (String ville : id_ville) {
                    preparedStatement.setString(paramIndex++, ville);
                }
            }
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Hotel a  = new Hotel(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8));
                a.set_id_hotel(rs.getString(1));
                a.set_evaluation(rs.getDouble(9));
                results.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    /* Function pour construire dynamiquement le requete de filtre */
    public static String filter_query(String dest,ArrayList<String> id_commodite, ArrayList<String> id_ville, ArrayList<String> evaluation) {
        StringBuilder sql = new StringBuilder("SELECT h.*, COALESCE(eh.evaluation, 0) AS evaluation FROM hotel AS h ");
        sql.append("LEFT JOIN v_evaluation_hotel AS eh ON h.id_hotel = eh.id_hotel WHERE 1=1 ");
        if(dest != null){
            sql.append(" AND LOWER(nom_hotel) LIKE ? ");
        }
        if (id_commodite != null && !id_commodite.isEmpty()) {
            sql.append("AND h.id_hotel IN (SELECT id_hotel FROM v_info_commodite_hotel WHERE id_commodite IN (");
            for (int i = 0; i < id_commodite.size(); i++) {
                sql.append("?");
                if (i < id_commodite.size() - 1) {
                    sql.append(",");
                }
            }
            sql.append(") GROUP BY id_hotel)");
        }
        if (evaluation != null && !evaluation.isEmpty()) {
            sql.append(" AND (");
            for (int i = 0; i < evaluation.size(); i++) {
                if (i > 0) {
                    sql.append(" OR ");
                }
                sql.append("evaluation BETWEEN ? AND ?");
            }
            sql.append(") ");
        }
        if (id_ville != null && !id_ville.isEmpty()) {
            sql.append("AND (");
            for (int i = 0; i < id_ville.size(); i++) {
                sql.append("id_ville = ?");
                if (i < id_ville.size() - 1) {
                    sql.append(" OR ");
                }
            }
            sql.append(")");
        }
        System.out.println(sql);
        return sql.toString();
    }

    /* Getters */
    public String get_id_hotel() {
        return id_hotel;
    }
    public String get_nom_hotel() {
        return nom_hotel;
    }
    public String get_description(){
        return description;
    }
    public String get_adress(){
        return adress;
    }
    public String get_id_partenaire(){
        return id_partenaire;
    }
    public String get_id_categorie_hotel(){
        return id_categorie_hotel;
    }
    public String get_id_ville(){
        return id_ville;
    }
    public double get_evaluation(){
        return evaluation;
    }

    /* Setters */
    public void set_id_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }
    public void set_nom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }
    public void set_description(String desc){
        this.description = desc;
    }
    public void set_adress(String str){
        this.adress = str;
    }
    public void set_id_partenaire(String id){
        this.id_partenaire = id;
    }
    public void set_id_categorie_hotel(String id){
        this.id_categorie_hotel = id;
    }
    public void set_id_ville(String id){
        this.id_ville = id;
    }
    public void set_evaluation(double d){
        this.evaluation = d;
    }
    /* Test */
    public static void main(String[] args) {
        try{
            ArrayList<String> id_commodite = new ArrayList<>();
            id_commodite.add("COM1");
            id_commodite.add("COM2");

            ArrayList<String> id_ville = new ArrayList<>();
            id_ville.add("VIL1");
            id_ville.add("VIL2");

            ArrayList<String> evaluation = new ArrayList<>();
            evaluation.add("0-2");
            evaluation.add("4-5");

            ArrayList<Hotel> results = filtrer_hotel(null,null, null ,null);

            for (Hotel dest : results) {
                System.out.println(dest.get_nom_hotel() + " - " + dest.get_evaluation());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
