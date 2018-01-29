package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.ProdottoBean;
import Bean.ProdottoCatalogoBean;
import Bean.ProdottoOrdineBean;

/**
 * Classe che gestisce la transazione dei prodotti di un ordine 
 *
 */
public class ProdottoOrdineModel {
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
	
	private static final String TABLE_NAME_PROD= "prodottoordine";
	private static final String TABLE_NAME_COMP= "composizione";

	/**
	 * Metodo che salva i prodotti nel database
	 * @param prodotti
	 * @pre prodotti != null
	 * @post memorizza i prodotti di un ordine nel database
	 * @throws SQLException
	 */
	public synchronized void doSave(ArrayList<ProdottoCatalogoBean> prodotti) throws SQLException {
		if(prodotti == null) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "INSERT INTO " + ProdottoOrdineModel.TABLE_NAME_PROD
				+ " (quantita, nome, colore, marca, descrizione, peso, prezzo, strumento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLProd);
			
			for(ProdottoCatalogoBean temp : prodotti) {

				preparedStatement.setInt(1, temp.getQuantAgg());
				preparedStatement.setString(2, temp.getNome());
				preparedStatement.setString(3, temp.getColore());
				preparedStatement.setString(4, temp.getMarca());
				preparedStatement.setString(5, temp.getDescrizione());
				preparedStatement.setInt(6, temp.getPeso());
				preparedStatement.setDouble(7, temp.getPrezzo());
				preparedStatement.setString(8, temp.getStrumento());

				preparedStatement.executeUpdate();
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
	}

	/**
	 * Metodo che restiuisce i prodotti presenti in un ordine
	 * @param codiceOrdine
	 * @pre codiceOrdine != null
	 * @post prodottiOrdine.size() > 0 se ci sono prodotti correlati all'ordine identidicato dal codice passato come parametro
	 * presenti nel database || prodottiOrdine.size() == 0 se non ci sono prodotti correlati all'ordine 
	 * identidicato dal codice passato come parametro
	 * @return prodottiOrdine
	 * @throws SQLException
	 */
	public synchronized ArrayList<ProdottoOrdineBean> prodottiOrdine(int codiceOrdine) throws SQLException{
		if(codiceOrdine < 0) {
			return null;
		}
		ArrayList<ProdottoOrdineBean> prdoottiOrdine = new ArrayList<ProdottoOrdineBean>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM "+ ProdottoOrdineModel.TABLE_NAME_PROD+" JOIN "+ ProdottoOrdineModel.TABLE_NAME_COMP +" WHERE "+ ProdottoOrdineModel.TABLE_NAME_PROD+".CODICE = "+ ProdottoOrdineModel.TABLE_NAME_COMP+".PRODOTTO AND "+ 
				ProdottoOrdineModel.TABLE_NAME_COMP+".ORDINE = ?";
		System.out.println(selectSQL);
		connection = ds.getConnection();
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
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return prdoottiOrdine;
		
	}

}
