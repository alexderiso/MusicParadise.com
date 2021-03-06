package Test_DBUnit;

import java.io.File;
import java.sql.PreparedStatement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class IndirizzoTestCase extends DBTestCase {
	
	public IndirizzoTestCase(String name) {
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
	
	public void testDoSave() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("INSERT INTO indirizzo (codice, indirizzo, citta, cap, nome, cognome, Telefono, cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		stm.setInt(1, 2);
		stm.setString(2, "Via Italia");
		stm.setString(3, "Napoli");
		stm.setInt(4,80031);
		stm.setString(5, "Ivan");
		stm.setString(6, "Esposito");
		stm.setString(7, "0818412252");
		stm.setString(8, "Ivan");
		
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("indirizzo");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/indirizzo/insert_indirizzo_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("indirizzo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testLeggi()throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM indirizzo WHERE cliente = ?");
		stm.setString(1,"Antonio");

		ITable actualTable = connection.createTable("retrieve_indirizzo_by_nickname", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/indirizzo/retrieve_indirizzo_by_nickname_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("indirizzo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testRimuoviIndirizzo() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("DELETE FROM indirizzo WHERE CODICE = ?");
		stm.setInt(1, 1);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("indirizzo");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/indirizzo/remove_indirizzo_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("indirizzo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		
		return new FlatXmlDataSetBuilder().build(new File("test/full.xml"));
	}
	
	
	
	
	

}
