package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.ClienteBean;
/**
 * Classe che gestisce la transazione dei clienti
 *
 */
public class ClienteModel {
	private static final String TABLE_NAME = "cliente";
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
	/**
	 * Metodo che salva un nuovo cliente all'inteno del database
	 * @param utente
	 * @throws SQLException
	 */
	
	public synchronized void doSave(ClienteBean utente) throws SQLException {

		if(utente == null) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ClienteModel.TABLE_NAME
				+ " (NICKNAME, PWD, EMAIL, NOME, COGNOME) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getNickName());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.setString(4, utente.getNome());
			preparedStatement.setString(5, utente.getCognome());
			

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
	/**
	 * Metodo che restiuisce il cliente presente nel database se presente
	 * @param nick
	 * @param password
	 * @return bean
	 * @return null
	 * @throws SQLException
	 */
	public synchronized ClienteBean leggi(String nick, String password) throws SQLException{
		if(nick == null || password == null) {
			return null;
		}
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
				ClienteBean bean = new ClienteBean();
				bean.setNickName(res.getString("NICKNAME"));
				bean.setEmail(res.getString("EMAIL"));
				bean.setPassword(res.getString("PWD"));
				bean.setNome(res.getString("NOME"));
				bean.setCognome(res.getString("COGNOME"));
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
