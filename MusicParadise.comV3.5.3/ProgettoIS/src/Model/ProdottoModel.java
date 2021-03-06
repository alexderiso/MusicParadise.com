package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.ProdottoBean;
import Bean.ProdottoCatalogoBean;


/**
 * Classe che gestisce la transazione inerente al prodotto

 *
 */
public class ProdottoModel{
	private static DataSource ds;
	/**
	 * Connessione al Database
	 */
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/db");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static final String TABLE_NAME_PROD= "prodottoInCatalogo";
	private static final String TABLE_NAME_FOTO = "foto";
	/**
	 * Metodo che salva un prodotto nel database
	 * @param codice
	 * @param numDisp
	 * @param nome
	 * @param colore
	 * @param marca
	 * @param descrizione
	 * @param peso
	 * @param prezzo
	 * @param data
	 * @param strumento
	 * @pre codice!=null && numDisp>0 && nome!=null && colore!=null && marca!=null && descrizione!=null && peso>0, prezzo>0, data!=null && strumento!=null 
	 * @post memorizza i prodotti nel database
	 * @throws  SQLException
	 */
	
	public synchronized void doSave(int codice,int numDisp, String nome, String colore,String marca, String descrizione, int peso, double prezzo, java.sql.Date data, String strumento) throws SQLException {
		if(codice < 0 || nome == null || colore == null || descrizione == null || peso <0 || prezzo < 0 || data == null || strumento == null) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "INSERT INTO " + ProdottoModel.TABLE_NAME_PROD
				+ " (codice, num_disponibilitÓ, nome, colore, marca, descrizione, peso, prezzo, data_inserimento, strumento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLProd);


			preparedStatement.setInt(1, codice);
			preparedStatement.setInt(2, numDisp);
			preparedStatement.setString(3, nome);
			preparedStatement.setString(4, colore);
			preparedStatement.setString(5, marca);
			preparedStatement.setString(6, descrizione);
			preparedStatement.setInt(7, peso);
			preparedStatement.setDouble(8, prezzo);
			preparedStatement.setDate(9, data);
			preparedStatement.setString(10, strumento);


			preparedStatement.executeUpdate();



		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	
	public synchronized int generaCodice() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM "+ ProdottoModel.TABLE_NAME_PROD;
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		int rowCount = 0;
		try{
			while(rs.next()){
				rowCount = rs.getInt("total");
			}
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}

		return rowCount;
	}
	
	
	
	
	
	/**
	 * Metodo che elimina un prodotto dal catalogo
	 * @param codice
	 * @pre codice != null
	 * @post il prodotto viene rimosso dal databse
	 * @throws SQLException
	 */
	public synchronized void doDelete(int codice) throws SQLException {
		if(codice < 0) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "DELETE FROM " + ProdottoModel.TABLE_NAME_PROD + " WHERE CODICE = ?";
		try{
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, codice);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	

	/**
	 * Metodo che restituisce tutti i prodotti presenti nel database
	 * @post Prodotti.size() > 0 se ci sono presenti dei prodotti memorizzati nel database
	 * || Prodotti.size() == 0 se non ci sono dei prodotti memorizzati nel database
	 * @return products
	 * @throws SQLException
	 */
	public synchronized ArrayList<ProdottoCatalogoBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProdottoCatalogoBean> products = new ArrayList<ProdottoCatalogoBean>();

		String selectSQL = "SELECT * FROM "+ ProdottoModel.TABLE_NAME_PROD;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoCatalogoBean bean = new ProdottoCatalogoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNumDisp(rs.getInt("num_disponibilitÓ"));
				bean.setNome(rs.getString("nome"));
				bean.setColore(rs.getString("colore"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPeso(rs.getInt("peso"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setData(rs.getDate("data_inserimento"));
				bean.setStrumento(rs.getString("strumento"));
				String selectSQL2 = "SELECT * FROM " + ProdottoModel.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
	
	
	/**
	 * Metodo che restituisce i prodotto in base al tipo di strumento
	 * @param strumento
	 * @pre strumento != null
	 * @post Prodotti.size() > 0 se ci sono presenti dei prodotti memorizzati nel database del tipo passato come parametro
	 * || Prodotti.size() == 0 se non ci sono dei prodotti memorizzati nel database del tipo passato come parametro 
	 * @return products
	 * @throws SQLException
	 */
	public synchronized Collection<ProdottoCatalogoBean> doRetrieveByInstruments(String strumento) throws SQLException {
		if(strumento == null) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoCatalogoBean> products = new LinkedList<ProdottoCatalogoBean>();

		String selectSQL = "SELECT * FROM "+ ProdottoModel.TABLE_NAME_PROD+" WHERE STRUMENTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,strumento);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoCatalogoBean bean = new ProdottoCatalogoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNumDisp(rs.getInt("num_disponibilitÓ"));
				bean.setNome(rs.getString("nome"));
				bean.setColore(rs.getString("colore"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPeso(rs.getInt("peso"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setData(rs.getDate("data_inserimento"));
				bean.setStrumento(rs.getString("strumento"));
				String selectSQL2 = "SELECT * FROM " + ProdottoModel.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}

	
	/**
	 * Metodo che restituisce i prodotti in base al codice
	 * @param code
	 * @pre code != null
	 * @post Prodotto != null se Ŕ prodotto.getCodice() == codice
	 * || Prodotto == null se nel database non ci sono prodotti con quel codice
	 * @return bean
	 * @throws SQLException
	 */
	public synchronized ProdottoCatalogoBean doRetrieveByKey(int code) throws SQLException {
		if(code < 0) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoCatalogoBean bean = null;

		String selectSQL = "SELECT * FROM " + ProdottoModel.TABLE_NAME_PROD + " WHERE CODICE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean = new ProdottoCatalogoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNumDisp(rs.getInt("num_disponibilitÓ"));
				bean.setNome(rs.getString("nome"));
				bean.setColore(rs.getString("colore"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPeso(rs.getInt("peso"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setData(rs.getDate("data_inserimento"));
				bean.setStrumento(rs.getString("strumento"));
			}

			String selectSQL2 = "SELECT * FROM " + ProdottoModel.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
			preparedStatement = connection.prepareStatement(selectSQL2);
			preparedStatement.setInt(1,code);
			ResultSet rs2 = preparedStatement.executeQuery();
			while(rs2.next()){
				bean.aggiungiFoto(rs2.getString("immagine"));
			}


		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	/**
	 * metodo che aggiorna i prodotti
	 * @param prodotti
	 * @pre prodotti.size() > 0
	 * @post i prodotti sono aggiornati
	 * @throws SQLException
	 */
	public synchronized void aggiorna(ArrayList<ProdottoCatalogoBean> prodotti) throws SQLException{
		if(prodotti == null) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		for(int i=0; i<prodotti.size();i++){
			String selectSQL = "UPDATE " + TABLE_NAME_PROD + " SET  num_disponibilitÓ = num_disponibilitÓ - ? WHERE CODICE= ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, prodotti.get(i).getQuantAgg());
			preparedStatement.setInt(2, prodotti.get(i).getCodice());
			preparedStatement.executeUpdate();
		}

	}
	
	/**
	 * Metodo che restituisce i prodotti in base al nome
	 * @param nome
	 * @pre nome != null
	 * @post Prodotti.size() > 0 se ci sono presenti dei prodotti memorizzati nel database con il nome passato come parametro
	 * || Prodotti.size() == 0 se non ci sono dei prodotti memorizzati nel database con il nome passato come parametro
	 * @return bean
	 * @throws SQLException
	 */
	public synchronized Collection<ProdottoCatalogoBean> doRetrieveByName(String nome) throws SQLException {
		if(nome == null) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoCatalogoBean> products = new LinkedList<ProdottoCatalogoBean>();

		String selectSQL = "SELECT * FROM "+ ProdottoModel.TABLE_NAME_PROD+" WHERE NOME like ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, "%" + nome + "%");

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoCatalogoBean bean = new ProdottoCatalogoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNumDisp(rs.getInt("num_disponibilitÓ"));
				bean.setNome(rs.getString("nome"));
				bean.setColore(rs.getString("colore"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPeso(rs.getInt("peso"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setData(rs.getDate("data_inserimento"));
				bean.setStrumento(rs.getString("strumento"));
				String selectSQL2 = "SELECT * FROM " + ProdottoModel.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;

	}
	/**
	 * Metodo che restituisce i prodotti in base alla marca
	 * @param marca
	 * @pre marca != null
	 * @post Prodotti.size() > 0 se ci sono presenti dei prodotti memorizzati nel database della marca passato come parametro
	 *	|| Prodotti.size() == 0 se non ci sono dei prodotti memorizzati nel database della marca passato come parametro
	 * @return products
	 * @throws SQLException
	 */
	public synchronized Collection<ProdottoCatalogoBean> doRetrieveByMarca(String marca) throws SQLException {
		if(marca == null) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoCatalogoBean> products = new LinkedList<ProdottoCatalogoBean>();

		String selectSQL = "SELECT * FROM "+ ProdottoModel.TABLE_NAME_PROD+" WHERE MARCA = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,marca);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoCatalogoBean bean = new ProdottoCatalogoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setNumDisp(rs.getInt("num_disponibilitÓ"));
				bean.setNome(rs.getString("nome"));
				bean.setColore(rs.getString("colore"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPeso(rs.getInt("peso"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setData(rs.getDate("data_inserimento"));

				bean.setStrumento(rs.getString("strumento"));
				String selectSQL2 = "SELECT * FROM " + ProdottoModel.TABLE_NAME_FOTO+ " WHERE CODICE_PRODOTTO = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setInt(1,bean.getCodice());
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()){
					bean.aggiungiFoto(rs2.getString("immagine"));
				}
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;

	}
	
	

}
