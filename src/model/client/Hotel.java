package model.client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.shared.Database;
import model.shared.PartenaireUtils;

public class Hotel extends Partenaire {
    private String adress;
    private String id_categorie_hotel;
    private String id_ville;

    /* Constructor */
    public Hotel(String nom, String description, String adress, String id_p, String id_c, String id_v, double ev) {
        this.set_nom(nom);
        this.set_description(description);
        this.set_adress(adress);
        this.set_id_partenaire(id_p);
        this.set_id_categorie_hotel(id_c);
        this.set_id_ville(id_v);
        this.set_evaluation(ev);
    }

    /* Fonction pour avoir tous les hotels */
    public static ArrayList<Hotel> get_liste_hotel() throws Exception {
        ArrayList<Hotel> resultat = new ArrayList<Hotel>();
        resultat = Hotel.filtrer_hotel(null, null, null, null);
        return resultat;
    }

    /* Fonction pour avoir une categorie id par son Id */
    public static Hotel get_hotel_par_id(Connection con, String id) throws Exception {
        Hotel resultat = null;
        Connection c = null;
        PreparedStatement prsmt = null;
        ResultSet rs = null;
        boolean est_nouvelle_connexion = false;
        try {
            if (con == null) {
                c = Database.get_connection();
                est_nouvelle_connexion = true;
            } else {
                c = con;
            }
            prsmt = c.prepareStatement("SELECT * FROM v_evaluation_hotel WHERE id_hotel = ? ");
            prsmt.setString(1, id);
            rs = prsmt.executeQuery();
            if (rs.next()) {
                resultat = new Hotel(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getDouble(9));
                resultat.set_id(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prsmt != null) {
                prsmt.close();
            }
            if (est_nouvelle_connexion) {
                c.close();
            }
        }
        return resultat;
    }

    /* Fonction pour avoir les tops dans dans un province */
    public static ArrayList<Hotel> get_top_hotel(Province p) throws Exception {
        ArrayList<Hotel> resultat = new ArrayList<Hotel>();
        Connection c = null;
        PreparedStatement prsmt = null;
        ResultSet rs = null;
        try {
            c = Database.get_connection();
            if (c != null) {
                prsmt = c.prepareStatement("SELECT * FROM v_ranking_hotel_province WHERE id_province = ? LIMIT 3");
                prsmt.setString(1, p.get_id_province());
                rs = prsmt.executeQuery();
                while (rs.next()) {
                    Hotel a = new Hotel(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getDouble(9));
                    a.set_id(rs.getString(1));
                    resultat.add(a);
                }
            } else {
                throw new Exception("Aucune connexion ");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prsmt != null) {
                prsmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return resultat;
    }

    /* Fonction pour filtrer les recherches en fonction des id recuperer */
    public static ArrayList<Hotel> filtrer_hotel(String dest, ArrayList<String> id_commodite,
            ArrayList<String> id_ville, ArrayList<String> evaluation) throws Exception {
        ArrayList<Hotel> results = new ArrayList<Hotel>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = Database.get_connection();
            String sql = filter_query(dest, id_commodite, id_ville, evaluation);
            preparedStatement = connection.prepareStatement(sql);
            int paramIndex = 1;
            if (dest != null) {
                preparedStatement.setString(paramIndex++, "%" + dest.toLowerCase() + "%");
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
                Hotel a = new Hotel(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getDouble(9));
                a.set_id(rs.getString(1));
                results.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
        return results;
    }

    /* Function pour construire dynamiquement le requete de filtre */
    public static String filter_query(String dest, ArrayList<String> id_commodite, ArrayList<String> id_ville,
            ArrayList<String> evaluation) {
        StringBuilder sql = new StringBuilder("SELECT * FROM v_evaluation_hotel WHERE 1=1");
        if (dest != null) {
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

    /* Fonction pour avoir la liste des commodites de l'hotel */
    public ArrayList<Commodite> get_liste_commodite() throws Exception {
        ArrayList<Commodite> result = new ArrayList<Commodite>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            c = Database.get_connection();
            prstm = c.prepareStatement("SELECT * FROM v_commodite_hotel WHERE id_hotel = ?");
            prstm.setString(1, this.get_id());
            rs = prstm.executeQuery();
            while (rs.next()) {
                Commodite com = new Commodite(rs.getString(3));
                result.add(com);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (prstm != null)
                prstm.close();
            if (c != null)
                c.close();
        }
        return result;
    }

    /* Fonction pour rechercher multicritere des tranports */
    public static ArrayList<Hotel> rechercher_hotel(String nom, String evaluation, String id_ville,
            Date dateInsertionDebut, Date dateInsertionFin) throws Exception {
        ArrayList<Hotel> result = new ArrayList<Hotel>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            c = Database.get_connection();
            int paramIndex = 1;
            prstm = c.prepareStatement(PartenaireUtils.get_recherche_query("hotel", nom, id_ville, evaluation,
                    dateInsertionDebut, dateInsertionFin));
            if (nom != null)
                prstm.setString(paramIndex++, nom);
            if (id_ville != null)
                prstm.setString(paramIndex++, id_ville);
            if (dateInsertionDebut != null)
                prstm.setDate(paramIndex++, dateInsertionDebut);
            if (dateInsertionFin != null)
                prstm.setDate(paramIndex++, dateInsertionFin);
            if (evaluation != null) {
                String[] split = evaluation.split("-");
                prstm.setDouble(paramIndex++, Double.valueOf(split[0]));
                prstm.setDouble(paramIndex++, Double.valueOf(split[1]));
            }
            rs = prstm.executeQuery();
            while (rs.next()) {
                Hotel h = new Hotel(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getDouble(9));
                h.set_id(rs.getString(1));
                result.add(h);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (prstm != null)
                prstm.close();
            if (c != null)
                c.close();
        }
        return result;
    }

    /* Getters */
    public String get_description() {
        return description;
    }

    public String get_adress() {
        return adress;
    }

    public String get_id_partenaire() {
        return id_partenaire;
    }

    public String get_id_categorie_hotel() {
        return id_categorie_hotel;
    }

    public String get_id_ville() {
        return id_ville;
    }

    /* Setters */
    public void set_description(String desc) {
        this.description = desc;
    }

    public void set_adress(String str) {
        this.adress = str;
    }

    public void set_id_partenaire(String id) {
        this.id_partenaire = id;
    }

    public void set_id_categorie_hotel(String id) {
        this.id_categorie_hotel = id;
    }

    public void set_id_ville(String id) {
        this.id_ville = id;
    }

    /* Surdefinition des fonctions get_categorie_avis et get_categorie_evaluation */
    @Override
    public String get_categorie_avis() {
        return "CAV1";
    }

    @Override
    public String get_categorie_evaluation() {
        return "CEV1";
    }

    @Override
    public String get_categorie_reservation() {
        return "CRS1";
    }

    /* Test */
    public static void main(String[] args) {
        try {
            ArrayList<Hotel> ls = Hotel.filtrer_hotel("luxe", null, null, null);
            System.out.println(ls.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
