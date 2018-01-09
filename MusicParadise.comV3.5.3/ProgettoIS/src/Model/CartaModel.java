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
	 * @param cliente
	 * @throws SQLException
	 */
	public synchronized void doSave(CartaBean carta, ClienteBean cliente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "INSERT INTO " + CartaModel.TABLE_NAME_CARTA
				+ " (cod, scadenza, numero, nomeProprietario, cliente) VALUES (?, ?, ?, ?, ?)";


		try {
			connection =ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLProd);

			
			preparedStatement.setInt(1, carta.getCodice());
			preparedStatement.setString(2, carta.getScadenza());
			preparedStatement.setString(3, carta.getNumCarta());
			preparedStatement.setString(4, carta.getNomeProprietario());
			preparedStatement.setString(5, cliente.getNickName());
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
	 * Metodo che genera il codice della carta di credito nel database
	 * @return rowCount
	 * @throws SQLException
	 */
	public synchronized int generaCodice() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM "+ CartaModel.TABLE_NAME_CARTA;
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
	 * Metodo che legge le carte di credito presenti nel database collegate al nickname passato come parametro
	 * @param nickname
	 * @return carte
	 * @throws SQLException
	 */
public synchronized ArrayList<CartaBean> leggi(String nickname) throws SQLException {
		
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
}
