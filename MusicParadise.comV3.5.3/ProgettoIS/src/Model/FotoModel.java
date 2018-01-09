package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe che gestisce la transazione delle foto dei prodotti
 *
 */
public class FotoModel {
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

	private static final String TABLE_NAME_FOTO= "foto";
	
	/**
	 * Metodo che salva le foto dei prodotti all'interno del database
	 * @param url
	 * @param cod
	 * @throws SQLException
	 */
	public synchronized void doSave(String url, int cod) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
			String insertSQL = "INSERT INTO " + FotoModel.TABLE_NAME_FOTO
					+ " (codice, codice_prodotto,immagine) VALUES (?, ?, ?)";
			try{
				int codice = generaCodice();
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, codice);
				preparedStatement.setInt(2, cod);
				preparedStatement.setString(3, url);

				preparedStatement.executeUpdate();
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
 * Metodo che genera il codice di ogni foto 	
 * @return rowCount
 * @throws SQLException
 */
public synchronized int generaCodice() throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String sql = "SELECT COUNT(*) AS TOTAL FROM "+FotoModel.TABLE_NAME_FOTO;
	int rowCount = 0;
	try{
	connection = ds.getConnection();
	preparedStatement = connection.prepareStatement(sql);
	ResultSet rs = preparedStatement.executeQuery();
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
}
