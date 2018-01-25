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

import Bean.IndirizzoBean;
/**
 * Classe che gestisce la transazione degli indirizzi
 *
 */
public class IndirizzoModel {
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
	
	private static final String TABLE_NAME_IND = "indirizzo";
	
	/**
	 * Metodo che salva l'indirizzo collegato al nickname nel database
	 * @param indirizzo
	 * @param nickname
	 * @pre indirizzo != null && nickname != null
	 * @post memorizza l'indirizzo nel database
	 * @throws SQLException
	 */
	public synchronized void doSave(IndirizzoBean indirizzo, String nickname) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + IndirizzoModel.TABLE_NAME_IND
				+ " (codice, indirizzo, citta, cap, nome, cognome, Telefono, cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, indirizzo.getCodice());
			preparedStatement.setString(2, indirizzo.getIndirizzo());
			preparedStatement.setString(3, indirizzo.getCitt�());
			preparedStatement.setInt(4, indirizzo.getCap());
			preparedStatement.setString(5, indirizzo.getNome());
			preparedStatement.setString(6, indirizzo.getCognome());
			preparedStatement.setString(7,indirizzo.getTelefono());
			preparedStatement.setString(8,nickname);


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
	 * Genera il codice dell'indirizzo
	 * @return rowCount
	 * @throws SQLException
	 */
	public synchronized int generaCodice() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM "+ IndirizzoModel.TABLE_NAME_IND;
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
	 * Metodo che legge gli indirizzi dal database
	 * @param nickname
	 * @pre Nickname != null
	 * @post Ritorna indirizzi.size() > 0 se il cliente ha degli indirizzi memorizzati 
	 * || Ritorna indirizzi.size() == 0 se non ci sono indirizzi memorizzati per il cliente
	 * @return indirizzi
	 * @return null
	 * @throws SQLException
	 */
public synchronized ArrayList<IndirizzoBean> leggi(String nickname) throws SQLException {
		
		ArrayList<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
	
		
		

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLProd = "SELECT * FROM " + IndirizzoModel.TABLE_NAME_IND + " WHERE CLIENTE = ?";
		System.out.println(insertSQLProd);

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLProd);
			preparedStatement.setString(1, nickname);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				IndirizzoBean bean = new IndirizzoBean();
				bean.setCodice(rs.getInt("codice"));
				bean.setCap(rs.getInt("cap"));
				bean.setCitt�(rs.getString("citta"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setTelefono(rs.getString("Telefono"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				indirizzi.add(bean);
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
		return indirizzi;
	}

/**
 * Metodo che rimuove un indirizzo dal database
 * @param codice
 * @pre codice != null
 * @post l'indirizzo associato al codice passato come parametro viene rimosso dal database
 * @throws SQLException
 */
public synchronized void rimuoviIndirizzo(int codice) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String insertSQL = "DELETE FROM " + IndirizzoModel.TABLE_NAME_IND + " WHERE CODICE = ?";
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
