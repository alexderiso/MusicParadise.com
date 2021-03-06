package Test_Model;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Bean.CartaBean;
import Bean.IndirizzoBean;
import Bean.OrdineBean;
import Model.OrdineModel;

public class OrdineModel_jdbc {

	private static Statement stmt;
	private static Connection con;
	static {
		//Inizia una connessione
		String db = "dbtest";
		String user = "root";
		String pass = "antonio@"; 

		try {
			// jdbs:mysql://indirizzo dell'host/nome del database
			String url = "jdbc:mysql://127.0.0.1/" + db;

			//Nome utente, password per la connessione al database
			con = (Connection) DriverManager.getConnection(url, user, pass);
			stmt = (Statement) con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private static final String TABLE_NAME_ORD= "ordine";
	private static final String TABLE_NAME_IND = "indirizzo";
	private static final String TABLE_NAME_CARTA = "carta";
	
	public synchronized void doSave(IndirizzoBean indirizzo, CartaBean carta, String stato,String corriere, String tracking, double totale, String cliente) throws SQLException {
		if(indirizzo == null || carta == null || stato == null || corriere == null || tracking == null || totale < 0 || cliente == null) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLOrd = "INSERT INTO " + OrdineModel_jdbc.TABLE_NAME_ORD
				+ " (utente, data_ordine, totale, indirizzo, carta, stato, numero_traking, corriere, data_consegna) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		java.util.Date utilDate = new java.util.Date();
		Date data = new Date(utilDate.getTime());
		try {
			connection =  con;
			preparedStatement = connection.prepareStatement(insertSQLOrd);
			preparedStatement.setString(1,cliente);
			preparedStatement.setDate(2, data);
			preparedStatement.setDouble(3, totale);
			preparedStatement.setInt(4,indirizzo.getCodice());
			preparedStatement.setInt(5,carta.getCodice());
			preparedStatement.setString(6,stato);
			preparedStatement.setString(7,tracking);
			preparedStatement.setString(8,corriere);
			preparedStatement.setDate(9, null);

			preparedStatement.executeUpdate();



		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	


	
	public synchronized OrdineBean doRetrieveByKey(int code)  throws SQLException {
		if(code < 0) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = null;

		String selectSQL = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_ORD + " WHERE NUM_ORDINE = ?";

		try {
			connection =con;
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

				String selectSQL2 = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_IND+ " WHERE CODICE = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,cod);
				ResultSet rs2 = preparedStatement.executeQuery();

				IndirizzoBean indirizzo = new IndirizzoBean();
				while(rs2.next()){
					indirizzo.setCodice(rs2.getInt("codice"));
					indirizzo.setIndirizzo(rs2.getString("indirizzo"));
					indirizzo.setCitt�(rs2.getString("citta"));
					indirizzo.setCap(rs2.getInt("cap"));
					indirizzo.setNome(rs2.getString("nome"));
					indirizzo.setCognome(rs2.getString("cognome"));
					indirizzo.setTelefono(rs2.getString("telefono"));
					bean.setIndirizzo(indirizzo);
				}

				String codCarta = rs.getString("carta");

				selectSQL2 = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_CARTA+ " WHERE COD = ?";
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

		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public synchronized ArrayList<OrdineBean> ordiniUtente(String nickname) throws SQLException{
		if(nickname == null) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordiniUtente = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM "+ OrdineModel_jdbc.TABLE_NAME_ORD+" WHERE UTENTE = ?";

		try {
			connection = con;
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

				String selectSQL2 = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_IND+ " WHERE CODICE = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,cod);
				ResultSet rs2 = preparedStatement.executeQuery();

				IndirizzoBean indirizzo = new IndirizzoBean();
				while(rs2.next()){
					indirizzo.setCodice(rs2.getInt("codice"));
					indirizzo.setIndirizzo(rs2.getString("indirizzo"));
					indirizzo.setCitt�(rs2.getString("citta"));
					indirizzo.setCap(rs2.getInt("cap"));
					indirizzo.setNome(rs2.getString("nome"));
					indirizzo.setCognome(rs2.getString("cognome"));
					indirizzo.setTelefono(rs2.getString("telefono"));
					bean.setIndirizzo(indirizzo);
				}

				String codCarta = rs.getString("carta");

				selectSQL2 = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_CARTA+ " WHERE COD = ?";
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

		}catch(Exception e) {
			e.printStackTrace();
		}
		return ordiniUtente;

	}
	
	public synchronized void aggiorna(int cod, String num_tracking, Date dataConsegna, String corriere) throws SQLException{
		if(cod < 0 || num_tracking == null || dataConsegna == null || corriere == null) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {


			String selectSQL = "UPDATE " + TABLE_NAME_ORD + " SET  numero_traking = ? WHERE NUM_ORDINE= ?";
			connection =  con;
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, num_tracking);
			preparedStatement.setInt(2, cod);
			preparedStatement.executeUpdate();
			
			String selectSQL2 = "UPDATE " + TABLE_NAME_ORD + " SET  corriere = ? WHERE NUM_ORDINE= ?";
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL2);
			preparedStatement.setString(1, corriere);
			preparedStatement.setInt(2, cod);
			preparedStatement.executeUpdate();
			
			String selectSQL3 = "UPDATE " + TABLE_NAME_ORD + " SET  data_consegna = ? WHERE NUM_ORDINE= ?";
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL3);
			preparedStatement.setDate(1, dataConsegna);
			preparedStatement.setInt(2, cod);
			preparedStatement.executeUpdate();
			
			String selectSQL4 = "UPDATE " + TABLE_NAME_ORD + " SET  stato = ? WHERE NUM_ORDINE= ?";
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL4);
			preparedStatement.setString(1, "spedito");
			preparedStatement.setInt(2, cod);
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void aggiornaConsegnato(int cod) throws SQLException{
		if(cod < 0) {
			return;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			String selectSQL4 = "UPDATE " + TABLE_NAME_ORD + " SET  stato = ? WHERE NUM_ORDINE= ?";
			connection = con;
			preparedStatement = connection.prepareStatement(selectSQL4);
			preparedStatement.setString(1, "consegnato");
			preparedStatement.setInt(2, cod);
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized Collection<OrdineBean> listaOrdini() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM "+ OrdineModel_jdbc.TABLE_NAME_ORD;

		try {
			connection = con;
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

				String selectSQL2 = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_IND+ " WHERE CODICE = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,cod);
				ResultSet rs2 = preparedStatement.executeQuery();

				IndirizzoBean indirizzo = new IndirizzoBean();
				while(rs2.next()){
					indirizzo.setCodice(rs2.getInt("codice"));
					indirizzo.setIndirizzo(rs2.getString("indirizzo"));
					indirizzo.setCitt�(rs2.getString("citta"));
					indirizzo.setCap(rs2.getInt("cap"));
					indirizzo.setNome(rs2.getString("nome"));
					indirizzo.setCognome(rs2.getString("cognome"));
					indirizzo.setTelefono(rs2.getString("telefono"));
					bean.setIndirizzo(indirizzo);
				}

				String codCarta = rs.getString("carta");

				selectSQL2 = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_CARTA+ " WHERE COD = ?";
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

		} catch(Exception e) {
			e.printStackTrace();
		}
		return ordini;

	}
	public synchronized ArrayList<OrdineBean> doRetrieveByStato(String stato) throws SQLException{
		if(stato == null) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM "+ OrdineModel_jdbc.TABLE_NAME_ORD+" WHERE STATO = ?";

		try {
			connection = con;
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

				String selectSQL2 = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_IND+ " WHERE CODICE = ?";
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1,cod);
				ResultSet rs2 = preparedStatement.executeQuery();

				IndirizzoBean indirizzo = new IndirizzoBean();
				while(rs2.next()){
					indirizzo.setCodice(rs2.getInt("codice"));
					indirizzo.setIndirizzo(rs2.getString("indirizzo"));
					indirizzo.setCitt�(rs2.getString("citta"));
					indirizzo.setCap(rs2.getInt("cap"));
					indirizzo.setNome(rs2.getString("nome"));
					indirizzo.setCognome(rs2.getString("cognome"));
					indirizzo.setTelefono(rs2.getString("telefono"));
					bean.setIndirizzo(indirizzo);
				}

				String codCarta = rs.getString("carta");

				selectSQL2 = "SELECT * FROM " + OrdineModel_jdbc.TABLE_NAME_CARTA+ " WHERE COD = ?";
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

		} catch(Exception e) {
			e.printStackTrace();
		}
		return ordini;

	}


}
