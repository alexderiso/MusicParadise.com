package Test_DBUnit.gestoreOrdini;

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

public class GestoreOrdiniTestCase extends DBTestCase {



	public GestoreOrdiniTestCase(String name) {
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
	
	public void testLeggi() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM gestoreordini WHERE NICKNAME = ? AND PWD = ?");
		stm.setString(1,"paolo10");
		stm.setString(2, "1234567@");

		ITable actualTable = connection.createTable("retrieve_gestoreordini_by_nickname&passowrd", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/gestoreOrdini/retrieve_gestoreordini_by_nickname&passowrd_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("gestoreordini");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}



}
