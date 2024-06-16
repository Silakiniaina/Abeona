package model.partenaire;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import model.Connection.Connexion;

public class Partenaire {
    String id_partenaire;
    String nom_partenaire;
    String email_partenaire;
    String mot_de_passe;
    Timestamp date_insertion;
    int status;
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getId_partenaire() {
        return id_partenaire;
    }
    public void setId_partenaire(String id_partenaire) {
        this.id_partenaire = id_partenaire;
    }
    public String getNom_partenaire() {
        return nom_partenaire;
    }
    public void setNom_partenaire(String nom_partenaire) {
        this.nom_partenaire = nom_partenaire;
    }
    public String getEmail_partenaire() {
        return email_partenaire;
    }
    public void setEmail_partenaire(String email_partenaire) {
        this.email_partenaire = email_partenaire;
    }
    public String getMot_de_passe() {
        return mot_de_passe;
    }
    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    public Timestamp getDate_insertion() {
        return date_insertion;
    }
    public void setDate_insertion(Timestamp date_insertion) {
        this.date_insertion = date_insertion;
    }

    public Partenaire(){}
    public Partenaire(String id_partenaire,String nom_partenaire,String email_partenaire,String mot_de_passe,Timestamp date_insertion,int status){
        this.setId_partenaire(id_partenaire);
        this.setNom_partenaire(nom_partenaire);
        this.setEmail_partenaire(email_partenaire);
        this.setMot_de_passe(mot_de_passe);
        this.setDate_insertion(date_insertion);
        this.setStatus(status);
    }

   public HashMap<String, Integer> getNombrePartenaire() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, Integer> result = new HashMap<>();
        
        try {
            String query = "SELECT * FROM v_nombre_partenaire";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                result.put("mois", rs.getInt("mois"));
                result.put("annee", rs.getInt("annee"));
                result.put("jour", rs.getInt("jour"));
                result.put("total", rs.getInt("total"));
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
    

    public void validerInscription()throws Exception{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE partenaire SET status = 1 WHERE id_partenaire = ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1, this.getId_partenaire());
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
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
    }

    public ArrayList<Partenaire> getAllPartenaire() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Partenaire> allTypes = new ArrayList<>();
        try {
            String query = "select * from partenaire";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Partenaire type = new Partenaire(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getInt(6));
                allTypes.add(type);
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
        return allTypes;
    }

    public ArrayList<Partenaire> getPartenaireAvecPagination(int debut,int fin) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Partenaire> allTypes = new ArrayList<>();
        try {
            String query = "select * from partenaire offset ? limit ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setInt(1, debut);
            ps.setInt(2, fin);
            rs = ps.executeQuery();
            while (rs.next()) {
                Partenaire type = new Partenaire(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getInt(6));
                allTypes.add(type);
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
        return allTypes;
    }

    
}
