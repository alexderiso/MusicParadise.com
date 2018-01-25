package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.GestoreOrdiniBean;
/**
 * Classe che gestisce la transazione del gestore ordini
 *
 */
public class GestoreOrdineModel {
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

	private static final String TABLE_NAME = "gestoreordini";
	/**
	 * Metodo che legge dal database i dati del gestore ordini
	 * @param nick
	 * @param password
	 * @pre nick != null && password != null
	 * @post Restituisce l'oggetto gestore != null se è presente nel database. Restituisce gestore == null se non è presente nel database
	 * @return bean
	 * @return null
	 * @throws SQLException
	 */
	public synchronized GestoreOrdiniBean leggi(String nick, String password) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = ds.getConnection();
		String selectSQL = "SELECT * FROM CLIENTE WHERE NICKNAME = ? AND PWD = ?";
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, nick);
		preparedStatement.setString(2, password);

		ResultSet res = preparedStatement.executeQuery();
		try{
			while(res.next()){
				GestoreOrdiniBean bean = new GestoreOrdiniBean();
				bean.setNickName(res.getString("NICKNAME"));
				bean.setEmail(res.getString("EMAIL"));
				bean.setPassword(res.getString("PWD"));
				bean.setNome(res.getString("NOME"));
				bean.setCognome(res.getString("COGNOME"));
				bean.setMatricola(res.getString("MATRICOLA"));
				return bean;
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
		return null;
	}


}
