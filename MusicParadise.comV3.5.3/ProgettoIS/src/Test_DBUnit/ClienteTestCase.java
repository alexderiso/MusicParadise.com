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

public class ClienteTestCase extends DBTestCase{

	public ClienteTestCase(String name) {
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
		PreparedStatement stm = connection.getConnection().prepareStatement("INSERT INTO cliente (email, nickname, nome, cognome, pwd) VALUES (?, ?, ?, ?, ?)");
		stm.setString(1,"v@gmail.com");
		stm.setString(2, "vale46");
		stm.setString(3, "Valentino");
		stm.setString(4,"Rossi");
		stm.setString(5, "12345678");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("cliente");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/insert_cliente_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("cliente");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testLeggi() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM cliente WHERE NICKNAME = ? AND PWD = ?");
		stm.setString(1,"Antonio");
		stm.setString(2, "12345678");

		ITable actualTable = connection.createTable("retrieve_cliente_by_nickname&passowrd", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/retrieve_cliente_by_nickname&passowrd_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("cliente");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}
	

}
