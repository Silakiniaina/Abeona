package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.shared.Database;
import model.shared.PartenaireUtils;

public class Guide extends Partenaire{
    private int disponibilite;


    /* Constructor */
    public Guide(String nom, String description, int disponibilite, String id_partenaire,double ev){
        this.set_nom(nom);
        this.set_disponibilite(disponibilite);
        this.set_description(description);
        this.set_id_partenaire(id_partenaire);
        this.set_evaluation(ev);
    }

    /* Fonction pour avoir tous les guides */
    public static ArrayList<Guide> get_liste_guide() throws Exception{
        ArrayList<Guide> resultat = new ArrayList<Guide>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM v_evaluation_guide");
            rs = prstm.executeQuery();
            while ( rs.next()) {
                Guide g = new Guide(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(6),rs.getDouble(7));
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
            prstm = c.prepareStatement("SELECT * FROM v_evaluation_guide WHERE id_guide = ?");
            prstm.setString(1, id);
            rs = prstm.executeQuery();
            if( rs.next()) {
                resultat = new Guide(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(6),rs.getDouble(7));
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

    /* Fonction pour rechercher multicritere des tranports */
    public static ArrayList<Guide> rechercher_transport(String nom, String evaluation,String id_ville, Date dateInsertionDebut,Date dateInsertionFin) throws Exception{
        ArrayList<Guide> result = new ArrayList<Guide>();
        Connection c = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        try{
            c = Database.get_connection();
            int paramIndex = 1;
            prstm = c.prepareStatement(PartenaireUtils.get_recherche_query("guide",nom,null, evaluation, dateInsertionDebut, dateInsertionFin));
            if(nom != null) prstm.setString(paramIndex++,nom);
            if(dateInsertionDebut != null) prstm.setDate(paramIndex++, dateInsertionDebut);
            if(dateInsertionFin != null)prstm.setDate(paramIndex++, dateInsertionFin);
            if(evaluation != null){
                String[] split = evaluation.split("-");
                prstm.setDouble(paramIndex++, Double.valueOf(split[0]));
                prstm.setDouble(paramIndex++, Double.valueOf(split[1]));
            }
            rs = prstm.executeQuery();
            while(rs.next()){
                Guide g = new Guide(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(6),rs.getDouble(7));
                g.set_id(rs.getString(1));
                result.add(g);
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(rs != null) rs.close();
            if(prstm != null) prstm.close();
            if(c != null )c.close();
        }
        return result;
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

    /* Test */
    public static void main(String[] args) {
        try {
            ArrayList<Guide> ls = Guide.rechercher_transport("fgsra", "1-5", null, null, null);
            System.out.println(ls.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
