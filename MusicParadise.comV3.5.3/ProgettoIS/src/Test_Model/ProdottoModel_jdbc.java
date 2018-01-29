package Test_Model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Bean.ProdottoCatalogoBean;
import Model.ProdottoModel;

public class ProdottoModel_jdbc {
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
	
	private static final String TABLE_NAME_PROD= "prodottoInCatalogo";
	private static final String TABLE_NAME_FOTO = "foto";
	

	
	public synchronized ArrayList<ProdottoCatalogoBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProdottoCatalogoBean> products = new ArrayList<ProdottoCatalogoBean>();

		String selectSQL = "SELECT * FROM "+ ProdottoModel_jdbc.TABLE_NAME_PROD;

		try {
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL);

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
				String selectSQL2 = "SELECT * FROM " + ProdottoModel_jdbc.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				products.add(bean);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	
	/**
	 * Metodo che restituisce i prodotto in base al tipo di strumento
	 * @param strumento
	 * @return products
	 * @throws SQLException
	 */
	public synchronized Collection<ProdottoCatalogoBean> doRetrieveByInstruments(String strumento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoCatalogoBean> products = new LinkedList<ProdottoCatalogoBean>();

		String selectSQL = "SELECT * FROM "+ ProdottoModel_jdbc.TABLE_NAME_PROD+" WHERE STRUMENTO = ?";

		try {
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,strumento);

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
				String selectSQL2 = "SELECT * FROM " + ProdottoModel_jdbc.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				products.add(bean);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	
	/**
	 * Metodo che restituisce i prodotti in base al codice
	 * @param code
	 * @return bean
	 * @throws SQLException
	 */
	public synchronized ProdottoCatalogoBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoCatalogoBean bean = null;

		String selectSQL = "SELECT * FROM " + ProdottoModel_jdbc.TABLE_NAME_PROD + " WHERE CODICE = ?";

		try {
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean = new ProdottoCatalogoBean();
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
			}

			String selectSQL2 = "SELECT * FROM " + ProdottoModel_jdbc.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
			preparedStatement = connection.prepareStatement(selectSQL2);
			preparedStatement.setInt(1,code);
			ResultSet rs2 = preparedStatement.executeQuery();
			while(rs2.next()){
				bean.aggiungiFoto(rs2.getString("immagine"));
			}


		} catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * metodo che aggiorna i prodotti
	 * @param prodotti
	 * @throws SQLException
	 */
	public synchronized void aggiorna(ArrayList<ProdottoCatalogoBean> prodotti) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		for(int i=0; i<prodotti.size();i++){
			String selectSQL = "UPDATE " + TABLE_NAME_PROD + " SET  num_disponibilità = num_disponibilità - ? WHERE CODICE= ?";
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, prodotti.get(i).getQuantAgg());
			preparedStatement.setInt(2, prodotti.get(i).getCodice());
			preparedStatement.executeUpdate();
		}

	}
	
	public synchronized Collection<ProdottoCatalogoBean> doRetrieveByName(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoCatalogoBean> products = new LinkedList<ProdottoCatalogoBean>();

		String selectSQL = "SELECT * FROM "+ ProdottoModel_jdbc.TABLE_NAME_PROD+" WHERE NOME like ?";

		try {
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, "%" + nome + "%");

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
				String selectSQL2 = "SELECT * FROM " + ProdottoModel_jdbc.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				products.add(bean);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;

	}
	/**
	 * Metodo che restituisce i prodotti in base alla marca
	 * @param marca
	 * @return products
	 * @throws SQLException
	 */
	public synchronized Collection<ProdottoCatalogoBean> doRetrieveByMarca(String marca) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoCatalogoBean> products = new LinkedList<ProdottoCatalogoBean>();

		String selectSQL = "SELECT * FROM "+ ProdottoModel_jdbc.TABLE_NAME_PROD+" WHERE MARCA = ?";

		try {
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,marca);

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
				String selectSQL2 = "SELECT * FROM " + ProdottoModel_jdbc.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				products.add(bean);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;

	}
	

}
