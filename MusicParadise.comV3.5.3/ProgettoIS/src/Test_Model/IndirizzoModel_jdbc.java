package Test_Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Bean.IndirizzoBean;
import Model.IndirizzoModel;

public class IndirizzoModel_jdbc {
	
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
	 
	 
	 private static final String TABLE_NAME_IND = "indirizzo";
		
		/**
		 * Metodo che salva l'indirizzo collegato al nickname nel database
		 * @param indirizzo
		 * @param nickname
		 * @throws SQLException
		 */
		public synchronized void doSave(IndirizzoBean indirizzo, String nickname) throws SQLException {
			if(indirizzo == null || nickname == null) {
				return;
			}
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQL = "INSERT INTO " + IndirizzoModel_jdbc.TABLE_NAME_IND
					+ " (indirizzo, citta, cap, nome, cognome, Telefono, cliente) VALUES (?, ?, ?, ?, ?, ?, ?)";


			try {
				connection = con;
				preparedStatement = connection.prepareStatement(insertSQL);

				preparedStatement.setString(1, indirizzo.getIndirizzo());
				preparedStatement.setString(2, indirizzo.getCittà());
				preparedStatement.setInt(3, indirizzo.getCap());
				preparedStatement.setString(4, indirizzo.getNome());
				preparedStatement.setString(5, indirizzo.getCognome());
				preparedStatement.setString(6,indirizzo.getTelefono());
				preparedStatement.setString(7,nickname);


				preparedStatement.executeUpdate();
				


			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		/**
		 * Metodo che legge gli indirizzi dal database
		 * @param nickname
		 * @return indirizzi
		 * @return null
		 * @throws SQLException
		 */
	public synchronized ArrayList<IndirizzoBean> leggi(String nickname) throws SQLException {
		if(nickname == null) {
			return null;
		}
			ArrayList<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
		
			
			

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQLProd = "SELECT * FROM " + IndirizzoModel_jdbc.TABLE_NAME_IND + " WHERE CLIENTE = ?";
			System.out.println(insertSQLProd);

			try {
				connection = con;
				preparedStatement = connection.prepareStatement(insertSQLProd);
				preparedStatement.setString(1, nickname);
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					IndirizzoBean bean = new IndirizzoBean();
					bean.setCodice(rs.getInt("codice"));
					bean.setCap(rs.getInt("cap"));
					bean.setCittà(rs.getString("citta"));
					bean.setIndirizzo(rs.getString("indirizzo"));
					bean.setTelefono(rs.getString("Telefono"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					indirizzi.add(bean);
				}

			}catch(Exception e) {
				e.printStackTrace();
			}
			return indirizzi;
		}
	public synchronized void rimuoviIndirizzo(int codice) throws SQLException {
		if(codice < 0) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "DELETE FROM " + IndirizzoModel_jdbc.TABLE_NAME_IND + " WHERE CODICE = ?";
		try{
			try {
				connection = con;
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, codice);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}
