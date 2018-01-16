package Test_Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Bean.CartaBean;
import Bean.ClienteBean;
import Model.CartaModel;

public class CartaModel_jdbc {
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
	 private static final String TABLE_NAME_CARTA= "carta";
		/**
		 * Metodo che salva la carta di credito nel database
		 * @param carta
		 * @param cliente
		 * @throws SQLException
		 */
		public synchronized void doSave(CartaBean carta, ClienteBean cliente) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQLProd = "INSERT INTO " + CartaModel_jdbc.TABLE_NAME_CARTA
					+ " (cod, scadenza, numero, nomeProprietario, cliente) VALUES (?, ?, ?, ?, ?)";


			try {
				connection =con;
				preparedStatement = connection.prepareStatement(insertSQLProd);

				
				preparedStatement.setInt(1, carta.getCodice());
				preparedStatement.setString(2, carta.getScadenza());
				preparedStatement.setString(3, carta.getNumCarta());
				preparedStatement.setString(4, carta.getNomeProprietario());
				preparedStatement.setString(5, cliente.getNickName());
				preparedStatement.executeUpdate();


			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * Metodo che genera il codice della carta di credito nel database
		 * @return rowCount
		 * @throws SQLException
		 */
		public synchronized int generaCodice() throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String sql = "SELECT COUNT(*) AS TOTAL FROM "+ CartaModel_jdbc.TABLE_NAME_CARTA;
			connection = con;
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
		 * Metodo che legge le carte di credito presenti nel database collegate al nickname passato come parametro
		 * @param nickname
		 * @return carte
		 * @throws SQLException
		 */
	public synchronized ArrayList<CartaBean> leggi(String nickname) throws SQLException {
			
			ArrayList<CartaBean> carte = new ArrayList<CartaBean>();
		
			
			

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQLProd = "SELECT * FROM " + CartaModel_jdbc.TABLE_NAME_CARTA +" WHERE CLIENTE = ?";


			try {
				connection = con;
				preparedStatement = connection.prepareStatement(insertSQLProd);
				preparedStatement.setString(1,nickname);
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					CartaBean bean = new CartaBean();
					bean.setCodice(rs.getInt("cod"));
					bean.setScadenza(rs.getString("scadenza"));
					bean.setNumCarta(rs.getString("numero"));
					bean.setNomeProprietario(rs.getString("nomeProprietario"));
					
					carte.add(bean);
				}

			} catch(Exception e) {
				e.printStackTrace();
			}
			return carte;
		}

}
