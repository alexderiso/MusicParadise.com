package Model;

import java.sql.Connection;
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
	 * @return bean
	 * @return null
	 * @throws SQLException
	 */
	public synchronized GestoreOrdiniBean leggi(String nick, String password) throws SQLException{
		Connection connection = ds.getConnection();
		java.sql.Statement statement = connection.createStatement();
		ResultSet res = statement.executeQuery("SELECT * FROM gestoreordini");
		try{
		while(res.next()){
			String nick2 = res.getString("NICKNAME");
			String pass = res.getString("PWD");
			if(((nick2.equalsIgnoreCase(nick))&&(pass.equalsIgnoreCase(password)))){
				GestoreOrdiniBean bean = new GestoreOrdiniBean();
				bean.setNickName(res.getString("NICKNAME"));
				bean.setEmail(res.getString("EMAIL"));
				bean.setPassword(res.getString("PWD"));
				bean.setNome(res.getString("NOME"));
				bean.setCognome(res.getString("COGNOME"));
				bean.setMatricola(res.getString("MATRICOLA"));
				return bean;
			}
		}
		}finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return null;
	}
	

}
