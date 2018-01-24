package Test_DBUnit;

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

import Model.OrdineModel;

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
		java.util.Date utilDate = new java.util.Date();
		stm.setDate(3, new Date(utilDate.getTime()));
		stm.setDouble(4, 155.30);
		stm.setInt(5, 0);
		stm.setInt(6, 0);
		stm.setString(7, "in preparazione");
		stm.setNull(8, java.sql.Types.VARCHAR);
		stm.setNull(9, java.sql.Types.VARCHAR);
	    stm.setNull(10, java.sql.Types.DATE);

		stm.executeUpdate();

		ITable actualTable = connection.createDataSet().getTable("ordine");

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/insert_ordine_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public void testDoRetrieveByKey() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE NUM_ORDINE = ?");
		stm.setInt(1,1);

		ITable actualTable = connection.createTable("retrieve_ordine_by_codice", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/retrieve_ordine_by_codice_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public void testDoRetrieveByStato() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE STATO = ?");
		stm.setString(1, "consegnato");

		ITable actualTable = connection.createTable("retrieve_ordine_by_stato", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/retrieve_ordine_by_stato_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public void testOrdiniUtente() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE UTENTE = ?");
		stm.setString(1, "Antonio");

		ITable actualTable = connection.createTable("retrieve_ordine_by_utente", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/retrieve_ordine_by_utente_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public void testListaOrdini() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE UTENTE = ?");
		stm.setString(1, "Antonio");

		ITable actualTable = connection.createTable("retrieve_ordini", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/retrieve_ordini_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("ordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}
	
	


}
