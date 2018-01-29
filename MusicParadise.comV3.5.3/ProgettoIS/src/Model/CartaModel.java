package Model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.CartaBean;
import Bean.ClienteBean;

/**
 * Classe che gestisce la transazione delle carte di credito
 *
 */
public class CartaModel {
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
	
	
	private static final String TABLE_NAME_CARTA= "carta";
	/**
	 * Metodo che salva la carta di credito nel database
	 * @param carta
	 * @pre carta!=null && cliente!=null
	 * @psot Memorizza la carta di credito nel database 
	 * @param cliente
	 * @throws SQLException
	 */
	public synchronized void doSave(String numCarta,String scadenza,String nomProprietario, ClienteBean cliente) throws SQLException {
		
		
		if(numCarta == null || scadenza == null || nomProprietario == null || cliente == null) {
			return;
		}
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "INSERT INTO " + CartaModel.TABLE_NAME_CARTA
				+ " (scadenza, numero, nomeProprietario, cliente) VALUES (?, ?, ?, ?)";


		try {
			connection =ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLProd);

			
			preparedStatement.setString(1, scadenza);
			preparedStatement.setString(2, numCarta);
			preparedStatement.setString(3, nomProprietario);
			preparedStatement.setString(4, cliente.getNickName());
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
	 * Metodo che legge le carte di credito presenti nel database collegate al nickname passato come parametro
	 * @param nickname
	 * @pre nickname != null
	 * @post carte.size() > 0 se il cliente ha associato delle carte
		|| carte.size() == 0 se non ci sono associate delle carte al cliente
	 * @return carte
	 * @throws SQLException
	 */
public synchronized ArrayList<CartaBean> leggi(String nickname) throws SQLException {
	
		if(nickname == null) {
			return null;
		}
		
		ArrayList<CartaBean> carte = new ArrayList<CartaBean>();
	
		
		

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "SELECT * FROM " + CartaModel.TABLE_NAME_CARTA +" WHERE CLIENTE = ?";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLProd);
			preparedStatement.setString(1,nickname);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				CartaBean bean = new CartaBean();
				bean.setCodice(rs.getInt("cod"));
				bean.setScadenza(rs.getString("scadenza"));
				bean.setNumCarta(rs.getString("numero"));
				bean.setNomeProprietario(rs.getString("nomeProprietario"));
				
				carte.add(bean);
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
		return carte;
	}
/**
 * Metodo che rimuove una carta dal database
 * @param codice
 * @pre codice >= 0
 * @post la carta associata al codice passato come parametro viene rimossa dal database
 * @throws SQLException
 */
public synchronized void rimuoviCarta(int codice) throws SQLException {
	if(codice < 0) {
		return;
	}
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String insertSQL = "DELETE FROM " + CartaModel.TABLE_NAME_CARTA + " WHERE COD = ?";
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


}
