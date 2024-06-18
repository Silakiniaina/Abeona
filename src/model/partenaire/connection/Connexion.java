
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion 
{
    public static Connection getConnection() throws SQLException 
    {
<<<<<<< Updated upstream
        String url = "jdbc:postgresql://localhost:5432/db_abeona";
=======
        String url = "jdbc:postgresql://localhost:5432/abeonas";
>>>>>>> Stashed changes
        String utilisateur = "postgres";
        String motDePasse = "root";

        try 
        {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) 
        {
            System.out.println("Erreur lors du chargement du pilote PostgreSQL JDBC");
            e.printStackTrace();
        }

        Connection connexion = null;
        try 
        {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
        } 
        catch (SQLException e) 
        {
            System.out.println("Erreur lors de l'établissement de la connexion à PostgreSQL");
            e.printStackTrace();
            throw e; 
        }
        return connexion;
    }
}
