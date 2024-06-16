package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import model.Connection.Connexion;
import model.partenaire.*;

public class Benefice {
    int mois;
    int annee;
    double total_prix;
    public Benefice(int annee, int mois, double total_prix) {
        this.setMois(mois);
        this.setAnnee(annee);
        this.setTotal_prix(total_prix);
    }

    public Benefice(){}

    public int getMois() {
        return mois;
    }


    public void setMois(int mois) {
        this.mois = mois;
    }


    public int getAnnee() {
        return annee;
    }


    public void setAnnee(int annee) {
        this.annee = annee;
    }


    public double getTotal_prix() {
        return total_prix;
    }


    public void setTotal_prix(double total_prix) {
        this.total_prix = total_prix;
    }


    /**
     * suite de la condition where pour getBenefice
     * @param annee
     * @param mois
     * @return
     */
    public String getSuiteOfQuery(int annee,int mois){
        String result = "";
        if (annee!=0) {
            result +=" and annee = "+annee;
        }
        if(annee==0){
            result +=" and mois = "+mois;
        }
        return result;
    }

    /**
     * Récupère les données de bénéfice pour une année et un mois spécifiques depuis la vue v_benefice.
     * Si annee = 0, alors l'année n'est pas incluse dans la condition WHERE de meme pour mois.
     * 
     * @param annee L'année pour filtrer les données (0 pour ignorer cette condition).
     * @param mois Le mois pour filtrer les données.
     * @return Un HashMap contenant les données de bénéfice récupérées depuis la base de données :
     *         - "mois" : Le mois des données de bénéfice.
     *         - "annee" : L'année des données de bénéfice.
     *         - "id_categorie_reservation" : L'identifiant de la catégorie de réservation.
     *         - "total_prix" : Le total des prix calculé pour cette catégorie de réservation.
     * @throws Exception Si une erreur se produit lors de l'exécution de la requête SQL ou de la connexion à la base de données.
     */
    public ArrayList<Benefice> getBenefice(int annee,int mois) throws Exception {  
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Benefice> result = new ArrayList<>();
        String suite = getSuiteOfQuery(annee, mois);
        try {
            String query = "SELECT * FROM v_benefice where 1=1"+suite;
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Benefice type = new Benefice(rs.getInt(1),rs.getInt(2),rs.getDouble(4));
                        
                result.add(type);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
}
}
