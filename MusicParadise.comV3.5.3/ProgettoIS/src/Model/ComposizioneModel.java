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

import Bean.ProdottoCatalogoBean;
/**
 * Classe che gestisce la composizione dell'ordine
 *
 */
public class ComposizioneModel {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/db");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME_COMP= "composizione";
	
	/**
	 * Metodo che salva la coppia ordine-prodotto
	 * @param ArrayList<ProdottoCatalogoBean> prodotti
	 * @param int codOrdine
	 * @pre prodotti != null && codOrdine >= 0
	 * @post Il sistema memorizza la coppia ordine e prodottoOrdine nel database.
	 * @throws SQLException
	 */
	
	public synchronized void doSave(ArrayList<ProdottoCatalogoBean> prodotti,int codOrdine) throws SQLException {
		
		if(prodotti == null || codOrdine < 0 ) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "INSERT INTO " + ComposizioneModel.TABLE_NAME_COMP
				+ " (ordine) VALUES (?)";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLProd);
			for(ProdottoCatalogoBean temp : prodotti) {
				preparedStatement.setInt(1, codOrdine);

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
	
	


}
