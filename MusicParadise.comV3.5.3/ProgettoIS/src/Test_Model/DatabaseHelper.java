package Test_Model;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatabaseHelper {
    private static Connection con;
    public static void initializeDatabase() throws SQLException {

   
 		    String db = "dbtest";
 		    String user = "root";
 		    String pass = "antonio@"; 
 		    
 		    try {
 		      // jdbs:mysql://indirizzo dell'host/nome del database
 		      String url = "jdbc:mysql://127.0.0.1/" + db;
 		     
 		      //Nome utente, password per la connessione al database
 		      con = (Connection) DriverManager.getConnection(url, user, pass);
 		
 		    } catch (Exception e) {
 		      e.printStackTrace();
 		      System.exit(0);
 		    }
 		
    

        try {
            IDatabaseConnection connection = new DatabaseConnection(con);
            FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("test/full.xml"));
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

        } catch (Exception e) {
            System.err.println("Assicurati che il server sia spento e nessun altro stia usando il db\n"+"The error is " + e.getMessage());
        }


        con.close();
    }
}