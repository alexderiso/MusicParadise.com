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
		    String db = "db";
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

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQL = "INSERT INTO " + IndirizzoModel_jdbc.TABLE_NAME_IND
					+ " (codice, indirizzo, citta, cap, nome, cognome, Telefono, cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


			try {
				connection = con;
				preparedStatement = connection.prepareStatement(insertSQL);

				preparedStatement.setInt(1, indirizzo.getCodice());
				preparedStatement.setString(2, indirizzo.getIndirizzo());
				preparedStatement.setString(3, indirizzo.getCittà());
				preparedStatement.setInt(4, indirizzo.getCap());
				preparedStatement.setString(5, indirizzo.getNome());
				preparedStatement.setString(6, indirizzo.getCognome());
				preparedStatement.setString(7,indirizzo.getTelefono());
				preparedStatement.setString(8,nickname);


				preparedStatement.executeUpdate();
				


			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * Genera il codice dell'indirizzo
		 * @return rowCount
		 * @throws SQLException
		 */
		public synchronized int generaCodice() throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String sql = "SELECT COUNT(*) AS TOTAL FROM "+ IndirizzoModel_jdbc.TABLE_NAME_IND;
			connection =con;
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int rowCount = 0;
			try{
				while(rs.next()){
					rowCount = rs.getInt("total");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}

			return rowCount;
		}
		
		/**
		 * Metodo che legge gli indirizzi dal database
		 * @param nickname
		 * @return indirizzi
		 * @return null
		 * @throws SQLException
		 */
	public synchronized ArrayList<IndirizzoBean> leggi(String nickname) throws SQLException {
			
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
		
}
