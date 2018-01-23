package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.CartaBean;
import Bean.IndirizzoBean;
import Bean.OrdineBean;
/**
 * Classe che gestisce la transazione degli ordini
 * 
 */
public class OrdineModel{
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
	
	private static final String TABLE_NAME_ORD= "ordine";
	private static final String TABLE_NAME_IND = "indirizzo";
	private static final String TABLE_NAME_CARTA = "carta";
/**
 * metodo che restituisce l'ordine tramite la chiave
 * @param code
 * @return bean
 * @return null
 * @throws SQLException
 */
	public synchronized OrdineBean doRetrieveByKey(int code)  throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = null;

		String selectSQL = "SELECT * FROM " + OrdineModel.TABLE_NAME_ORD + " WHERE NUM_ORDINE = ?";

		try {
			connection =ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean = new OrdineBean();
				bean.setNumOrdine(rs.getInt("num_ordine"));
				bean.setUser(rs.getString("utente"));
				bean.setData(rs.getDate("data_ordine"));
				bean.setCorriere(rs.getString("corriere"));
				bean.setStato(rs.getString("stato"));
				bean.setTotale(rs.getDouble("totale"));
				bean.setTracking(rs.getString("numero_traking"));
				bean.setDataConsegna(rs.getDate("data_consegna"));


				String cod = rs.getString("indirizzo");

				String selectSQL2 = "SELECT * FROM " + OrdineModel.TABLE_NAME_IND+ " WHERE CODICE = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,cod);
				ResultSet rs2 = preparedStatement.executeQuery();

				IndirizzoBean indirizzo = new IndirizzoBean();
				while(rs2.next()){
					indirizzo.setCodice(rs2.getInt("codice"));
					indirizzo.setIndirizzo(rs2.getString("indirizzo"));
					indirizzo.setCittà(rs2.getString("citta"));
					indirizzo.setCap(rs2.getInt("cap"));
					indirizzo.setNome(rs2.getString("nome"));
					indirizzo.setCognome(rs2.getString("cognome"));
					indirizzo.setTelefono(rs2.getString("telefono"));
					bean.setIndirizzo(indirizzo);
				}

				String codCarta = rs.getString("carta");

				selectSQL2 = "SELECT * FROM " + OrdineModel.TABLE_NAME_CARTA+ " WHERE COD = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,codCarta);
				rs2 = preparedStatement.executeQuery();

				CartaBean carta = new CartaBean();
				while(rs2.next()){
					carta.setCodice(rs2.getInt("cod"));
					carta.setNumCarta(rs2.getString("numero"));
					carta.setScadenza(rs2.getString("scadenza"));
					carta.setNomeProprietario(rs2.getString("nomeProprietario"));
					bean.setCarta(carta);
				}

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

	public synchronized ArrayList<OrdineBean> doRetrieveByStato(String stato) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordiniUtente = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM "+ OrdineModel.TABLE_NAME_ORD+" WHERE STATO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,stato);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setNumOrdine(rs.getInt("num_ordine"));
				bean.setUser(rs.getString("utente"));
				bean.setData(rs.getDate("data_ordine"));
				bean.setCorriere(rs.getString("corriere"));
				bean.setStato(rs.getString("stato"));
				bean.setTotale(rs.getDouble("totale"));
				bean.setTracking(rs.getString("numero_traking"));
				bean.setDataConsegna(rs.getDate("data_consegna"));

				String cod = rs.getString("indirizzo");

				String selectSQL2 = "SELECT * FROM " + OrdineModel.TABLE_NAME_IND+ " WHERE CODICE = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,cod);
				ResultSet rs2 = preparedStatement.executeQuery();

				IndirizzoBean indirizzo = new IndirizzoBean();
				while(rs2.next()){
					indirizzo.setCodice(rs2.getInt("codice"));
					indirizzo.setIndirizzo(rs2.getString("indirizzo"));
					indirizzo.setCittà(rs2.getString("citta"));
					indirizzo.setCap(rs2.getInt("cap"));
					indirizzo.setNome(rs2.getString("nome"));
					indirizzo.setCognome(rs2.getString("cognome"));
					indirizzo.setTelefono(rs2.getString("telefono"));
					bean.setIndirizzo(indirizzo);
				}

				String codCarta = rs.getString("carta");

				selectSQL2 = "SELECT * FROM " + OrdineModel.TABLE_NAME_CARTA+ " WHERE COD = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,codCarta);
				rs2 = preparedStatement.executeQuery();

				CartaBean carta = new CartaBean();
				while(rs2.next()){
					carta.setCodice(rs2.getInt("cod"));
					carta.setNumCarta(rs2.getString("numero"));
					carta.setScadenza(rs2.getString("scadenza"));
					carta.setNomeProprietario(rs2.getString("nomeProprietario"));
					bean.setCarta(carta);
				}

				ordiniUtente.add(bean);
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
		return ordiniUtente;

	}


	/**
	 * Metodo che salva un ordine all'interno del database
	 * @param indirizzo
	 * @param carta
	 * @param stato
	 * @param corriere
	 * @param tracking
	 * @param totale
	 * @param cliente
	 * @throws SQLException
	 */
	public synchronized void doSave(IndirizzoBean indirizzo, CartaBean carta, String stato,String corriere, String tracking, double totale, String cliente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLOrd = "INSERT INTO " + OrdineModel.TABLE_NAME_ORD
				+ " (utente, num_ordine, data_ordine, totale, indirizzo, carta, stato, numero_traking, corriere, data_consegna) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		java.util.Date utilDate = new java.util.Date();
		Date data = new Date(utilDate.getTime());
		int cod = generaCodice();
		try {
			connection =  ds.getConnection();
			System.out.println(connection);
			preparedStatement = connection.prepareStatement(insertSQLOrd);
			preparedStatement.setString(1,cliente);
			preparedStatement.setInt(2, cod);
			preparedStatement.setDate(3, data);
			preparedStatement.setDouble(4, totale);
			preparedStatement.setInt(5,indirizzo.getCodice());
			preparedStatement.setInt(6,carta.getCodice());
			preparedStatement.setString(7,stato);
			preparedStatement.setString(8,tracking);
			preparedStatement.setString(9,corriere);
			preparedStatement.setDate(10, null);

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
	 * Metodo che genera il codice di un ordine
	 * @return rowCount
	 * @throws SQLException
	 */
	public synchronized int generaCodice() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM "+ OrdineModel.TABLE_NAME_ORD;
		int rowCount = 0;
		try {
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


	/**
	 * Metodo che restituisce tutti gli ordini effettuati da un cliente
	 * @param nickname
	 * @return ordiniUtente
	 * @throws SQLException
	 */
	public synchronized ArrayList<OrdineBean> ordiniUtente(String nickname) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordiniUtente = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM "+ OrdineModel.TABLE_NAME_ORD+" WHERE UTENTE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,nickname);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setNumOrdine(rs.getInt("num_ordine"));
				bean.setUser(rs.getString("utente"));
				bean.setData(rs.getDate("data_ordine"));
				bean.setCorriere(rs.getString("corriere"));
				bean.setStato(rs.getString("stato"));
				bean.setTotale(rs.getDouble("totale"));
				bean.setTracking(rs.getString("numero_traking"));
				bean.setDataConsegna(rs.getDate("data_consegna"));

				String cod = rs.getString("indirizzo");

				String selectSQL2 = "SELECT * FROM " + OrdineModel.TABLE_NAME_IND+ " WHERE CODICE = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,cod);
				ResultSet rs2 = preparedStatement.executeQuery();

				IndirizzoBean indirizzo = new IndirizzoBean();
				while(rs2.next()){
					indirizzo.setCodice(rs2.getInt("codice"));
					indirizzo.setIndirizzo(rs2.getString("indirizzo"));
					indirizzo.setCittà(rs2.getString("citta"));
					indirizzo.setCap(rs2.getInt("cap"));
					indirizzo.setNome(rs2.getString("nome"));
					indirizzo.setCognome(rs2.getString("cognome"));
					indirizzo.setTelefono(rs2.getString("telefono"));
					bean.setIndirizzo(indirizzo);
				}

				String codCarta = rs.getString("carta");

				selectSQL2 = "SELECT * FROM " + OrdineModel.TABLE_NAME_CARTA+ " WHERE COD = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,codCarta);
				rs2 = preparedStatement.executeQuery();

				CartaBean carta = new CartaBean();
				while(rs2.next()){
					carta.setCodice(rs2.getInt("cod"));
					carta.setNumCarta(rs2.getString("numero"));
					carta.setScadenza(rs2.getString("scadenza"));
					carta.setNomeProprietario(rs2.getString("nomeProprietario"));
					bean.setCarta(carta);
				}

				ordiniUtente.add(bean);
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
		return ordiniUtente;

	}

	/**
	 * Metodo che restituisce la lista di tutti gli ordini 
	 * @return ordini
	 * @throws SQLException
	 */
	public synchronized Collection<OrdineBean> listaOrdini() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM "+ OrdineModel.TABLE_NAME_ORD;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setNumOrdine(rs.getInt("num_ordine"));
				bean.setUser(rs.getString("utente"));
				bean.setData(rs.getDate("data_ordine"));
				bean.setCorriere(rs.getString("corriere"));
				bean.setStato(rs.getString("stato"));
				bean.setTotale(rs.getDouble("totale"));
				bean.setTracking(rs.getString("numero_traking"));
				bean.setDataConsegna(rs.getDate("data_consegna"));

				String cod = rs.getString("indirizzo");

				String selectSQL2 = "SELECT * FROM " + OrdineModel.TABLE_NAME_IND+ " WHERE CODICE = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,cod);
				ResultSet rs2 = preparedStatement.executeQuery();

				IndirizzoBean indirizzo = new IndirizzoBean();
				while(rs2.next()){
					indirizzo.setCodice(rs2.getInt("codice"));
					indirizzo.setIndirizzo(rs2.getString("indirizzo"));
					indirizzo.setCittà(rs2.getString("citta"));
					indirizzo.setCap(rs2.getInt("cap"));
					indirizzo.setNome(rs2.getString("nome"));
					indirizzo.setCognome(rs2.getString("cognome"));
					indirizzo.setTelefono(rs2.getString("telefono"));
					bean.setIndirizzo(indirizzo);
				}

				String codCarta = rs.getString("carta");

				selectSQL2 = "SELECT * FROM " + OrdineModel.TABLE_NAME_CARTA+ " WHERE COD = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,codCarta);
				rs2 = preparedStatement.executeQuery();

				CartaBean carta = new CartaBean();
				while(rs2.next()){
					carta.setCodice(rs2.getInt("cod"));
					carta.setNumCarta(rs2.getString("numero"));
					carta.setScadenza(rs2.getString("scadenza"));
					carta.setNomeProprietario(rs2.getString("nomeProprietario"));
					bean.setCarta(carta);
				}

				ordini.add(bean);
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
		return ordini;

	}
	/**
	 * Metodo che aggiorna lo stato di un ordine
	 * @param cod
	 * @param num_tracking
	 * @param dataConsegna
	 * @param corriere
	 * @throws SQLException
	 */
	public synchronized void aggiorna(int cod, String num_tracking, Date dataConsegna, String corriere) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {


			String selectSQL = "UPDATE " + TABLE_NAME_ORD + " SET  numero_traking = ? WHERE NUM_ORDINE= ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, num_tracking);
			preparedStatement.setInt(2, cod);
			preparedStatement.executeUpdate();
			
			String selectSQL2 = "UPDATE " + TABLE_NAME_ORD + " SET  corriere = ? WHERE NUM_ORDINE= ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL2);
			preparedStatement.setString(1, corriere);
			preparedStatement.setInt(2, cod);
			preparedStatement.executeUpdate();
			
			String selectSQL3 = "UPDATE " + TABLE_NAME_ORD + " SET  data_consegna = ? WHERE NUM_ORDINE= ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL3);
			preparedStatement.setDate(1, dataConsegna);
			preparedStatement.setInt(2, cod);
			preparedStatement.executeUpdate();
			
			String selectSQL4 = "UPDATE " + TABLE_NAME_ORD + " SET  stato = ? WHERE NUM_ORDINE= ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL4);
			preparedStatement.setString(1, "spedito");
			preparedStatement.setInt(2, cod);
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
	





}
