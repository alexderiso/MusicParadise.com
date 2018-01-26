package Test_DBUnit.carrello;

import java.io.File;
import java.sql.PreparedStatement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import Model.CarrelloModel;
import Test_DBUnit.DatabaseProperty;

public class CarrelloTestCase extends DBTestCase {
	
	public CarrelloTestCase(String name) {
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
		PreparedStatement stm = connection.getConnection().prepareStatement("INSERT INTO incarrello (prodotto, utente, quantita) VALUES (?, ?, ?)");
		stm.setInt(1,1);
		stm.setString(2, "Ivan");
		stm.setInt(3, 2);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("incarrello");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/carrello/insert_carrello_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("incarrello");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	public void testLeggi() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM incarrello WHERE UTENTE = ?");
		stm.setString(1,"Antonio");

		ITable actualTable = connection.createTable("retrieve_carrello_by_nickname", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/carrello/retrieve_carrello_by_nickname_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("incarrello");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testRemove() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("DELETE FROM incarrello WHERE UTENTE = ?");
		stm.setString(1, "Antonio");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("incarrello");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/carrello/remove_carrello_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("incarrello");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}
	
	

}
