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

public class CartaTestCase extends DBTestCase{

	public CartaTestCase(String name) {
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
		PreparedStatement stm = connection.getConnection().prepareStatement("INSERT INTO carta (cod, scadenza, numero, nomeProprietario, cliente) VALUES (?, ?, ?, ?, ?)");
		stm.setInt(1, 3);
		stm.setString(2, "1/18");
		stm.setString(3, "1278367165782986");
		stm.setString(4,"Ivan Esposito");
		stm.setString(5, "Ivan");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("carta");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/insert_carta_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("carta");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testLeggi()throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM carta WHERE cliente = ?");
		stm.setString(1,"Antonio");

		ITable actualTable = connection.createTable("retrieve_carta_by_nickname", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/retrieve_carta_by_nickname_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("carta");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}
	
	
	
	

}
