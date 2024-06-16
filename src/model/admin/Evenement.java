package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import model.Connection.Connexion;

public class Evenement {
    String id_evenement;
    String description;
    String lieu_evenement;
    Timestamp date_insertion;
    String titre_evenement;
    String id_hotel;
    String id_ville;
    String id_categorie_evenement;
    public String getId_evenement() {
        return id_evenement;
    }
    public void setId_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLieu_evenement() {
        return lieu_evenement;
    }
    public void setLieu_evenement(String lieu_evenement) {
        this.lieu_evenement = lieu_evenement;
    }
    public Timestamp getDate_insertion() {
        return date_insertion;
    }
    public void setDate_insertion(Timestamp date_insertion) {
        this.date_insertion = date_insertion;
    }
    public String getTitre_evenement() {
        return titre_evenement;
    }
    public void setTitre_evenement(String titre_evenement) {
        this.titre_evenement = titre_evenement;
    }
    public String getId_hotel() {
        return id_hotel;
    }
    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }
    public String getId_ville() {
        return id_ville;
    }
    public void setId_ville(String id_ville) {
        this.id_ville = id_ville;
    }
    public String getId_categorie_evenement() {
        return id_categorie_evenement;
    }
    public void setId_categorie_evenement(String id_categorie_evenement) {
        this.id_categorie_evenement = id_categorie_evenement;
    }
    public Evenement() {
    }
    public Evenement(String id_evenement, String description, String lieu_evenement, Timestamp date_insertion,
            String titre_evenement, String id_hotel, String id_ville, String id_categorie_evenement) {
        this.setId_evenement(id_evenement);
        this.setDescription(description);
        this.setLieu_evenement(lieu_evenement);
        this.setDate_insertion(date_insertion);
        this.setTitre_evenement(titre_evenement);
        this.setId_hotel(id_hotel);
        this.setId_ville(id_ville);
        this.setId_categorie_evenement(id_categorie_evenement);
    }

    public ArrayList<Evenement> getAllEvenement() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Evenement> allTypes = new ArrayList<>();
        try {
            String query = "select * from evenement";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Evenement type = new Evenement(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
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

    public Evenement getById() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Evenement allTypes = null;
        try {
            String query = "select * from evenement where id_evenement = ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1, this.getId_evenement());
            rs = ps.executeQuery();
            while (rs.next()) {
                allTypes = new Evenement(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
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

    public void update()throws Exception{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE evenement SET description = ?,lieu_evenement = ? , date_insertion = ? , titre_evenement = ? , id_hotel = ? , id_ville = ? , id_categorie_evenement = ?  WHERE id_evenement = ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1, this.getDescription());
            ps.setString(2, this.getLieu_evenement());
            ps.setTimestamp(3, this.getDate_insertion());
            ps.setString(4, this.getTitre_evenement());
            ps.setString(5, this.getId_hotel());
            ps.setString(6, this.getId_ville());
            ps.setString(7, this.getId_categorie_evenement());
            ps.setString(8, this.getId_evenement());
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

    public void delete()throws Exception{
         Connection connection = null;
        PreparedStatement ps = null;
        try {
            String query = "delete from evenement WHERE id_evenement = ?";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1, this.getId_evenement());
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

    public void insert()throws Exception{
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            String query = "insert into evenement(description,lieu_evenement , date_insertion , titre_evenement , id_hotel , id_ville , id_categorie_evenement) values(?,?,?,?,?,?,?)";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            ps.setString(1, this.getDescription());
            ps.setString(2, this.getLieu_evenement());
            ps.setTimestamp(3, this.getDate_insertion());
            ps.setString(4,this.getTitre_evenement());
            ps.setString(5,this.getId_hotel());
            ps.setString(6,this.getId_ville());
            ps.setString(7,this.getId_categorie_evenement());
            ps.executeUpdate();
            connection.commit();
        }catch(Exception e){
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
    public ArrayList<Evenement> getEvenementThisYear() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Evenement> allTypes = new ArrayList<>();
        try {
            String query = "select * from evenement WHERE DATE_PART('year', date_insertion) = DATE_PART('year', CURRENT_DATE)";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Evenement type = new Evenement(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
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
    public ArrayList<Evenement> getEvenementThisMonth() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Evenement> allTypes = new ArrayList<>();
        try {
            String query = "select * from evenement WHERE DATE_TRUNC('month', date_insertion) = DATE_TRUNC('month', CURRENT_DATE)";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Evenement type = new Evenement(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
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
    public ArrayList<Evenement> getEvenementToday() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Evenement> allTypes = new ArrayList<>();
        try {
            String query = "select * from evenement WHERE DATE(date_insertion) = CURRENT_DATE";
            connection = Connexion.getConnection("postgres", "postgres", "abeona");
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Evenement type = new Evenement(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
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
    
    public HashMap<String, Integer> getNombreEvenement() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, Integer> result = new HashMap<>();
        
        try {
            String query = "SELECT * FROM v_nombre_evenement";
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
