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
	
	public void testDoRetrieveByKey() throws Exception{
		IDatabaseConnection connection = getConnection();
		
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM ordine WHERE NUM_ORDINE = ?");
		stm.setInt(1,1);
		
		ITable actualTable = connection.createTable("retrieve_ordine_by_codice", stm);
		
		 IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/retrieve_ordine_by_codice_oracle.xml"));
	        ITable expectedTable = expectedDataSet.getTable("ordine");

	        Assertion.assertEquals(expectedTable, actualTable);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}
	

}
