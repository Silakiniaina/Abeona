package model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    public static Connection getConnection(String dtb,String user,String baseName) throws Exception{
		Connection c=null;
		String url="jdbc:postgresql://localhost:5432/"+baseName;
		try{
            Class.forName("org.postgresql.Driver");
			c=DriverManager.getConnection(url,user,"Tsiory24");
			c.setAutoCommit(false);
			return c;
		}
		catch(Exception e){
			throw e;
		}
	}
}