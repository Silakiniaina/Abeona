package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;

public class Guide extends Partenaire{
    private int disponibilite;

    /* Constructor */
    public Guide(String nom, String description, int disponibilite, String id_partenaire){
        this.set_nom(nom);
        this.set_disponibilite(disponibilite);
        this.set_description(description);
        this.set_id_partenaire(id_partenaire);
    }

    /* Fonction pour avoir tous les guides */
    public static ArrayList<Guide> get_liste_guide() throws Exception{
        ArrayList<Guide> resultat = new ArrayList<Guide>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM guide");
            rs = prstm.executeQuery();
            while ( rs.next()) {
                Guide g = new Guide(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(6));
                g.set_id(rs.getString(1));
                resultat.add(g);
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null) rs.close();
            if(prstm != null) prstm.close();
            if(c != null) c.close();
        }

        return resultat;
    }

    /* Fonction pour avoir un guide via son id */
    public static Guide get_guide_par_id(Connection con, String id) throws Exception{
        Guide resultat = null;
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
            prstm = c.prepareStatement("SELECT * FROM guide WHERE id_guide = ?");
            prstm.setString(1, id);
            rs = prstm.executeQuery();
            if( rs.next()) {
                resultat = new Guide(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(6));
                resultat.set_id(rs.getString(1));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null) rs.close();
            if(prstm != null) prstm.close();
            if(est_nouvelle_connexion) c.close();
        }
        return resultat;
    }

    /* Getters */
    public int get_disponibilite(){
        return this.disponibilite;
    }

    /* Setters */
    public void set_disponibilite(int d){
        this.disponibilite = d;
    }

    /* Surdefinition des fonctions en tant que partenaire */
    @Override
    public String get_categorie_avis() {
        return "CAV6";
    }

    @Override
    public String get_categorie_evaluation() {
        return "CEV6";
    }

    @Override
    public String get_categorie_reservation() {
        return "CRS6";
    }

    @Override
    public double get_evaluation() throws Exception {
        double resultat = 0;
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM v_evaluation_guide WHERE id_guide = ?");
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
            Guide g = Guide.get_guide_par_id(null, "GUI4");
            boolean disp = g.chech_disponibilite(Date.valueOf("2024-12-12"), Date.valueOf("2024-12-20"));
            System.out.println("Disponibilite : "+disp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
