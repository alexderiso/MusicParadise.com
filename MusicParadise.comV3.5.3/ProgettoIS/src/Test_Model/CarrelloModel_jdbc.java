package Test_Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;

import Bean.ProdottoBean;
import Bean.ProdottoCatalogoBean;
import Bean.UtenteBean;
/**
 * Classe che gestisce la transazione del carrello
 *
 */
public class CarrelloModel_jdbc {
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
	
	private static final String TABLE_NAME_INCARRELLO= "incarrello";
	private static final String TABLE_NAME_PROD = "prodottoincatalogo";
	private static final String TABLE_NAME_FOTO = "foto";
	/**
	 * Metodo che salva il carrello nel database
	 * @param list
	 * @param utente
	 * @throws SQLException
	 */
	public synchronized void doSave(List<ProdottoCatalogoBean> list, UtenteBean utente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		remove(utente);
		String insertSQLProd = "INSERT INTO " + CarrelloModel_jdbc.TABLE_NAME_INCARRELLO
				+ " (prodotto, utente, quantita) VALUES (?, ?, ?)";


		try {
			connection = con;
			preparedStatement = connection.prepareStatement(insertSQLProd);
			
			for(ProdottoCatalogoBean bean : list) {
				preparedStatement.setInt(1, bean.getCodice());
				preparedStatement.setString(2,utente.getNickName());
				preparedStatement.setInt(3, bean.getQuantAgg());
				preparedStatement.executeUpdate();

			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo che genera il codice univoco del carrello
	 * @return rowCount
	 * @throws SQLException
	 */
	public synchronized int generaCodice() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM "+ CarrelloModel_jdbc.TABLE_NAME_INCARRELLO;
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
	 * Metodo che legge il carrello dal database in base al nickname
	 * @param nickname
	 * @return prodInCarrello
	 * @throws SQLException
	 */
	public synchronized ArrayList<ProdottoCatalogoBean> leggi(String nickname) throws SQLException {
		
		ArrayList<ProdottoCatalogoBean> prodInCarrello = new ArrayList<ProdottoCatalogoBean>();
	
		
		

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "SELECT * FROM " + CarrelloModel_jdbc.TABLE_NAME_INCARRELLO + " JOIN " + CarrelloModel_jdbc.TABLE_NAME_PROD+" WHERE "
				+  CarrelloModel_jdbc.TABLE_NAME_INCARRELLO+".PRODOTTO = "+ CarrelloModel_jdbc.TABLE_NAME_PROD+".CODICE AND "+ CarrelloModel_jdbc.TABLE_NAME_INCARRELLO+".UTENTE = ?";


		try {
			connection = con;
			preparedStatement = connection.prepareStatement(insertSQLProd);
			preparedStatement.setString(1,nickname);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ProdottoCatalogoBean bean = new ProdottoCatalogoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNumDisp(rs.getInt("num_disponibilità"));
				bean.setNome(rs.getString("nome"));
				bean.setColore(rs.getString("colore"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPeso(rs.getInt("peso"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setData(rs.getDate("data_inserimento"));
				bean.setStrumento(rs.getString("strumento"));
				bean.setQuantAgg(rs.getInt("quantita"));
				String selectSQL2 = "SELECT * FROM " + CarrelloModel_jdbc.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				prodInCarrello.add(bean);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return prodInCarrello;
	}
	/**
	 * Metodo che rimuove un carrello dal database
	 * @param utente
	 * @throws SQLException
	 */
public synchronized void remove(UtenteBean utente) throws SQLException {
		

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String insertSQL = "DELETE FROM " +CarrelloModel_jdbc.TABLE_NAME_INCARRELLO + " WHERE UTENTE = ?";
	try{
		try {
			connection = con;
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getNickName());
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
