package Test_DBUnit.ordine;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;

import Model.OrdineModel;
import Test_DBUnit.DatabaseProperty;

public class OrdineTestCase extends DBTestCase{

	public OrdineTestCase(String name) {
		super(name);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				DatabaseProperty.DATABASE_DRIVER);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				DatabaseProperty.DATABASE_URL);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				DatabaseProperty.DATABASE_USERNAME);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				DatabaseProperty.DATABASE_PASSWORD);
	}

	@SuppressWarnings("deprecation")
	public void testDoSave() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("INSERT INTO ordine (utente, num_ordine, data_ordine, totale, indirizzo, carta, stato, numero_traking, corriere, data_consegna) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		stm.setString(1,"Ivan");
		stm.setInt(2, 3);
		stm.setDate(3, new Date(118,0,24));
		stm.setDouble(4, 155.30);
		stm.setInt(5, 0);
		stm.setInt(6, 0);
		stm.setString(7, "in preparazione");
		stm.setNull(8, java.sql.Types.VARCHAR);
		stm.setNull(9, java.sql.Types.VARCHAR);
	    stm.setNull(10, java.sql.Types.DATE);

		stm.executeUpdate();

		ITable actualTable = connection.createDataSet().getTable("ordine");

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/ordine/insert_ordine_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public void testDoRetrieveByKey() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE NUM_ORDINE = ?");
		stm.setInt(1,1);

		ITable actualTable = connection.createTable("retrieve_ordine_by_codice", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/ordine/retrieve_ordine_by_codice_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public void testDoRetrieveByStato() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE STATO = ?");
		stm.setString(1, "consegnato");

		ITable actualTable = connection.createTable("retrieve_ordine_by_stato", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/ordine/retrieve_ordine_by_stato_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public void testOrdiniUtente() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE UTENTE = ?");
		stm.setString(1, "Antonio");

		ITable actualTable = connection.createTable("retrieve_ordine_by_utente", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/ordine/retrieve_ordine_by_utente_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public void testListaOrdini() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE UTENTE = ?");
		stm.setString(1, "Antonio");

		ITable actualTable = connection.createTable("retrieve_ordini", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/ordine/retrieve_ordini_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testAggiorna() throws Exception{
		 IDatabaseConnection connection = getConnection();
		  
			PreparedStatement stm = connection.getConnection().prepareStatement("UPDATE ordine SET  numero_traking = ? ,  corriere = ? ,  data_consegna = ? ,  stato = ? WHERE NUM_ORDINE= ?");
			stm.setString(1, "2787615673");
			stm.setString(2, "GLS");
			stm.setDate(3, new Date(118,0,24));
			stm.setString(4, "spedito");
			stm.setInt(5, 2);
			stm.executeUpdate();
			
			ITable actualTable = connection.createDataSet().getTable("ordine");
	        
	       
	        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/ordine/update_ordine_oracle.xml"));
	        ITable expectedTable = expectedDataSet.getTable("ordine");

	        Assertion.assertEquals(expectedTable, actualTable);
	}
    
	public void testAggiornaConsegnato() throws Exception{
		 IDatabaseConnection connection = getConnection();
		  
			PreparedStatement stm = connection.getConnection().prepareStatement("UPDATE ordine SET stato = ? WHERE NUM_ORDINE= ?");
			stm.setString(1, "consegnato");
			stm.setInt(2, 0);
			stm.executeUpdate();
			
			ITable actualTable = connection.createDataSet().getTable("ordine");
	        
	       
	        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/ordine/update_ordine_consegnato_oracle.xml"));
	        ITable expectedTable = expectedDataSet.getTable("ordine");

	        Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testGeneraCodice() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT COUNT(*) AS TOTAL FROM ordine ");
		
		ITable actualTable = connection.createTable("cod_ordine", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/ordine/cod_ordine_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("cod_ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}
	
	


}
