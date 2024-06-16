package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.shared.Database;

public class Tranport extends Partenaire{
    private double tarif;
    private String id_categorie_transport;

    /* Constructor */
    public Tranport(String name, String desc, double tartif, String id_c,String id_p){
        this.set_nom(name);
        this.set_description(desc);
        this.set_tarif(tartif);
        this.set_id_categorie_transport(id_c);
        this.set_id_partenaire(id_p);
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

    
}
