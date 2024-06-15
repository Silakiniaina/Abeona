package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import model.Connection.Connexion;

public class Client {
    String id_utilisateur;
    String nom_utilisateur;
    String prenom_utilisateur;
    Timestamp date_de_naissance;
    String email;
    String mot_de_passe;
    Timestamp date_insertion;
    String id_genre;
    String id_categorie_utilisateur;
    public String getId_utilisateur() {
        return id_utilisateur;
    }
    public void setId_utilisateur(String id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
    public String getNom_utilisateur() {
        return nom_utilisateur;
    }
    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }
    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }
    public void setPrenom_utilisateur(String prenom_utilisateur) {
        this.prenom_utilisateur = prenom_utilisateur;
    }
    public Timestamp getDate_de_naissance() {
        return date_de_naissance;
    }
    public void setDate_de_naissance(Timestamp date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getId_genre() {
        return id_genre;
    }
    public void setId_genre(String id_genre) {
        this.id_genre = id_genre;
    }
    public String getId_categorie_utilisateur() {
        return id_categorie_utilisateur;
    }
    public void setId_categorie_utilisateur(String id_categorie_utilisateur) {
        this.id_categorie_utilisateur = id_categorie_utilisateur;
    }
    public Client() {
    }
    public Client(String id_utilisateur, String nom_utilisateur, String prenom_utilisateur, Timestamp date_de_naissance,
            String email, String mot_de_passe, Timestamp date_insertion, String id_genre,
            String id_categorie_utilisateur) {
        this.setId_utilisateur(id_utilisateur);
        this.setNom_utilisateur(prenom_utilisateur);
        this.setPrenom_utilisateur(prenom_utilisateur);
        this.setDate_de_naissance(date_de_naissance);
        this.setEmail(email);
        this.setMot_de_passe(mot_de_passe);
        this.setDate_insertion(date_insertion);
        this.setId_genre(id_genre);
        this.setId_categorie_utilisateur(id_categorie_utilisateur);
    }

    public ArrayList<Client> getAllClient() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Client> allTypes = new ArrayList<>();
        try {
            String query = "select * from utilisateur ";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Client type = new Client(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getTimestamp(7),rs.getString(8),rs.getString(9));
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

    public ArrayList<Client> getClientThisYear() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Client> allTypes = new ArrayList<>();
        try {
            String query = "select * from utilisateur WHERE DATE_PART('year', date_insertion) = DATE_PART('year', CURRENT_DATE)";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Client type = new Client(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getTimestamp(7),rs.getString(8),rs.getString(9));
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
    public ArrayList<Client> getClientThisMonth() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Client> allTypes = new ArrayList<>();
        try {
            String query = "select * from utilisateur WHERE DATE_TRUNC('month', date_insertion) = DATE_TRUNC('month', CURRENT_DATE)";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Client type = new Client(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getTimestamp(7),rs.getString(8),rs.getString(9));
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
    public ArrayList<Client> getClientToday() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Client> allTypes = new ArrayList<>();
        try {
            String query = "select * from utilisateur WHERE DATE(date_insertion) = CURRENT_DATE";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Client type = new Client(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getTimestamp(7),rs.getString(8),rs.getString(9));
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

    public HashMap<String, Integer> getNombreClient() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, Integer> result = new HashMap<>();
        
        try {
            String query = "SELECT * FROM v_nombre_client";
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
}
