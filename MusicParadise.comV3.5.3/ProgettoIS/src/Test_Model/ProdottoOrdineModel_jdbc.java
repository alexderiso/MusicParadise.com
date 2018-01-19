package Test_Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Bean.ProdottoOrdineBean;
import Model.ProdottoOrdineModel;
import Bean.ProdottoBean;
import Bean.ProdottoCatalogoBean;
import Bean.ProdottoOrdineBean;

public class ProdottoOrdineModel_jdbc {
	private static Statement stmt;
	private static Connection con;
	static {
		//Inizia una connessione
		String db = "dbtest";
		String user = "root";
		String pass = "root"; 

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

	private static final String TABLE_NAME_PROD= "prodottoordine";
	private static final String TABLE_NAME_COMP= "composizione";

	/**
	 * Metodo che gestisce i prodotti presenti in un ordine
	 * @param codiceOrdine
	 * @return prodottiOrdine
	 * @throws SQLException
	 */
	public synchronized ArrayList<ProdottoOrdineBean> prodottiOrdine(int codiceOrdine) throws SQLException{
		ArrayList<ProdottoOrdineBean> prdoottiOrdine = new ArrayList<ProdottoOrdineBean>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM "+ ProdottoOrdineModel_jdbc.TABLE_NAME_PROD+" JOIN "+ ProdottoOrdineModel_jdbc.TABLE_NAME_COMP +" WHERE "+ ProdottoOrdineModel_jdbc.TABLE_NAME_PROD+".CODICE = "+ ProdottoOrdineModel_jdbc.TABLE_NAME_COMP+".PRODOTTO AND "+ 
				ProdottoOrdineModel_jdbc.TABLE_NAME_COMP+".ORDINE = ?";
		System.out.println(selectSQL);
		connection = con;
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setInt(1, codiceOrdine);
		ResultSet rs2 = preparedStatement.executeQuery();
		try{
			while(rs2.next()){
				ProdottoOrdineBean prodotto = new ProdottoOrdineBean();
				prodotto.setCodice(rs2.getInt("codice"));
				prodotto.setQuantità(rs2.getInt("quantita"));
				prodotto.setNome(rs2.getString("nome"));
				prodotto.setColore(rs2.getString("colore"));
				prodotto.setMarca(rs2.getString("marca"));
				prodotto.setDescrizione(rs2.getString("descrizione"));
				prodotto.setPeso(rs2.getInt("peso"));
				prodotto.setPrezzo(rs2.getDouble("prezzo"));
				prodotto.setStrumento(rs2.getString("strumento"));
				prdoottiOrdine.add(prodotto);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prdoottiOrdine;
	}
	
	
	/**
	 * Metodo che salva i prodotti nel database
	 * @param prodotti
	 * @throws SQLException
	 */
	public synchronized void doSave(ArrayList<ProdottoCatalogoBean> prodotti) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "INSERT INTO " + ProdottoOrdineModel_jdbc.TABLE_NAME_PROD
				+ " (codice, quantita, nome, colore, marca, descrizione, peso, prezzo, strumento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


		try {
			connection = con;
			preparedStatement = connection.prepareStatement(insertSQLProd);
			
			for(ProdottoCatalogoBean temp : prodotti) {
				preparedStatement.setInt(1, generaCodice());
				preparedStatement.setInt(2, temp.getQuantAgg());
				preparedStatement.setString(3, temp.getNome());
				preparedStatement.setString(4, temp.getColore());
				preparedStatement.setString(5, temp.getMarca());
				preparedStatement.setString(6, temp.getDescrizione());
				preparedStatement.setInt(7, temp.getPeso());
				preparedStatement.setDouble(8, temp.getPrezzo());
				preparedStatement.setString(9, temp.getStrumento());

				preparedStatement.executeUpdate();
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che genera un codice per i prodotti
	 * @return rowCount
	 * @throws SQLException
	 */
	public synchronized int generaCodice() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM "+ ProdottoOrdineModel_jdbc.TABLE_NAME_PROD;
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
	
}


