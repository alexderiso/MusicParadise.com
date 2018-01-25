package Test_DBUnit.composizione;

import java.io.File;
import java.sql.PreparedStatement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import Test_DBUnit.DatabaseProperty;

public class ComposizioneTestCase extends DBTestCase {

	public ComposizioneTestCase(String name) {
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
		PreparedStatement stm = connection.getConnection().prepareStatement("INSERT INTO composizione (prodotto, ordine) VALUES (?, ?)");
		stm.setInt(1, 2);
		stm.setInt(2, 3);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("composizione");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/composizione/insert_composizione_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("composizione");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testGeneraCodice() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT COUNT(*) AS TOTAL FROM composizione ");
		
		ITable actualTable = connection.createTable("cod_composizione", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/composizione/cod_composizione_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("cod_composizione");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}
	
	

}
