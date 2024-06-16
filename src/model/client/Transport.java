package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Transport extends Partenaire{
    private double tarif;
    private String id_categorie_transport;

    /* Constructor */
    public Transport(String name, String desc, double tartif, String id_c,String id_p){
        this.set_nom(name);
        this.set_description(desc);
        this.set_tarif(tartif);
        this.set_id_categorie_transport(id_c);
        this.set_id_partenaire(id_p);
    }

    /* Fonction pour avoir toutes les transports */
    public static ArrayList<Transport> get_liste_transport()throws Exception{
        ArrayList<Transport> result = new ArrayList<Transport>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM transport");
            rs = prstm.executeQuery();
            while(rs.next()){
                Transport t = new Transport(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(6), rs.getString(7));
                t.set_id(rs.getString(1));
                result.add(t);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs != null) rs.close();
            if(prstm != null) prstm.close();
            if(c != null) c.close();
        }
        return result;
    }

    /* Fonction pour avoir un transport via son id  */
    public static Transport get_transport_par_id(Connection con, String id)throws Exception{
        Transport resultat = null;
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        boolean est_nouvelle_connexion = false;
        try{
            if(con == null){
                c = Database.get_connection();
                est_nouvelle_connexion = true;
            }else{
                c = con;
            }
            prstm = c.prepareStatement("SELECT * FROM transport WHERE id_transport = ?");
            prstm.setString(1, id);
            rs = prstm.executeQuery();
            if(rs.next()){
                resultat = new Transport(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(6), rs.getString(7));
                resultat.set_id(rs.getString(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs != null) rs.close();
            if(prstm != null) prstm.close();
            if(est_nouvelle_connexion) c.close();
        }
        return resultat;
    }

    /* Getters */
    public double get_tarif() {
        return tarif;
    }
    public String get_id_categorie_transport() {
        return id_categorie_transport;
    }
    
    /* Setters */
    public void set_tarif(double tarif) {
        this.tarif = tarif;
    }
    public void set_id_categorie_transport(String id_categorie_transport) {
        this.id_categorie_transport = id_categorie_transport;
    }

    /* Surdefinition fonction partenaire */
    @Override
    public String get_categorie_avis() {
        return "CAV3";
    }

    @Override
    public String get_categorie_evaluation() {
        return "CEV3";
    }

    @Override
    public String get_categorie_reservation() {
        return "CRS3";
    }

    @Override
    public double get_evaluation()throws Exception{
        double resultat = 0;
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM v_evaluation_transport WHERE id_transport = ?");
            prstm.setString(1, this.get_id());
            rs = prstm.executeQuery();
            if(rs.next()){
                resultat = rs.getDouble(2);
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null){ rs.close(); }
            if(prstm != null){ prstm.close(); }
            if(c != null){ c.close(); }
        }
        return resultat;
    }

    /* Test */
    public static void main(String[] args) {
        try {
            Transport t = Transport.get_transport_par_id(null, "TRN2");
            if(t != null){
                System.out.println(t.get_evaluation());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}