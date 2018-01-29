package Test_Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import Bean.ClienteBean;

public class ClienteModel_jdbc {
	private static final String TABLE_NAME = "cliente";
	private static Statement stmt;
	  private static Connection con;
	 static {
		    //Inizia una connessione
		    String db = "dbtest";
		    String user = "root";
		    String pass = "antonio@"; 
		    
		    try {
		      // jdbs:mysql://indirizzo dell'host/nome del database
		      String url = "jdbc:mysql://127.0.0.1/" + db;
		     
		      //Nome utente, password per la connessione al database
		      con = (Connection) DriverManager.getConnection(url, user, pass);
		      stmt = (Statement) con.createStatement();
		    } catch (Exception e) {
		      e.printStackTrace();
		      System.exit(0);
		    }
		  }
	 public synchronized ClienteBean leggi(String nick, String password) throws SQLException{
			if(nick == null || password == null) {
				return null;
			}
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			connection = con;
			String selectSQL = "SELECT * FROM CLIENTE WHERE NICKNAME = ? AND PWD = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nick);
			preparedStatement.setString(2, password);
			
			ResultSet res = preparedStatement.executeQuery();
			
			try{
			while(res.next()){
					ClienteBean bean = new ClienteBean();
					bean.setNickName(res.getString("NICKNAME"));
					bean.setEmail(res.getString("EMAIL"));
					bean.setPassword(res.getString("PWD"));
					bean.setNome(res.getString("NOME"));
					bean.setCognome(res.getString("COGNOME"));
					return bean;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

}
