package Test_DBUnit;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import Model.ProdottoModel;

public class ProdottoTestCase extends DBTestCase{

	public ProdottoTestCase(String name) {
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
	
	public void testDoRetrieveAll() throws Exception{
		IDatabaseConnection connection = getConnection();

		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM prodottoInCatalogo");
		

		ITable actualTable = connection.createTable("retrieve_prodottoInCatalogo", stm);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/retrieve_prodottoInCatalogo_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoInCatalogo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	public void testDoSave() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("INSERT INTO prodottoInCatalogo (codice, num_disponibilità, nome, colore, marca, descrizione, peso, prezzo, data_inserimento, strumento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		stm.setInt(1, 3);
		stm.setInt(2, 10);
		stm.setString(3, "Tastiera Casio");
		stm.setString(4, "Nero");
		stm.setString(5,"Casio");
		stm.setString(6, "Bella");
		stm.setDouble(7, 2.00);
		stm.setDouble(8, 150.00);
		stm.setDate(9, new Date(118,0,25));
		stm.setString(10, "tastiera");
		
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("prodottoInCatalogo");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/insert_prodottoInCatalogo_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoInCatalogo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	public void testDoDelete() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("DELETE FROM prodottoInCatalogo WHERE CODICE = ?");
		stm.setInt(1, 2);
		

		stm.executeUpdate();
		ITable actualTable = connection.createDataSet().getTable("prodottoInCatalogo");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/remove_prodottoInCatalogo_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoInCatalogo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testDoRetrieveByInstruments() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM prodottoInCatalogo WHERE STRUMENTO = ?");
		stm.setString(1, "batteria");
		
		ITable actualTable = connection.createTable("retrieve_prodottoInCatalogo", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/retrieve_prodottoInCatalogo_by_strumento_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoInCatalogo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testDoRetrieveByKey() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM prodottoInCatalogo WHERE CODICE = ?");
		stm.setInt(1, 2);
		
		ITable actualTable = connection.createTable("retrieve_prodottoInCatalogo", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/retrieve_prodottoInCatalogo_by_codice_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoInCatalogo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testDoRetrieveByName() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM prodottoInCatalogo WHERE NOME like ?");
		stm.setString(1, "%yamaha%");;
		
		ITable actualTable = connection.createTable("retrieve_prodottoInCatalogo", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/retrieve_prodottoInCatalogo_by_nome_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoInCatalogo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testDoRetrieveByMarca() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM prodottoInCatalogo WHERE MARCA = ?");
		stm.setString(1, "tamburo");
		
		ITable actualTable = connection.createTable("retrieve_prodottoInCatalogo", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/retrieve_prodottoInCatalogo_by_marca_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoInCatalogo");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testAggiorna() throws Exception{
		 IDatabaseConnection connection = getConnection();
		  
			PreparedStatement stm = connection.getConnection().prepareStatement("UPDATE prodottoInCatalogo SET  num_disponibilità = num_disponibilità - ? WHERE CODICE= ?");
			stm.setInt(1,1);
			stm.setInt(2, 1);
			stm.executeUpdate();
			
			ITable actualTable = connection.createDataSet().getTable("prodottoInCatalogo");
	        
	       
	        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/update_prodotto_oracle.xml"));
	        ITable expectedTable = expectedDataSet.getTable("prodottoInCatalogo");

	        Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public void testGeneraCodice() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT COUNT(*) AS TOTAL FROM prodottoInCatalogo ");
		
		ITable actualTable = connection.createTable("cod_prodotto", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/prodotto/cod_prodotto_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("cod_prodotto");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("test/full.xml"));
	}
	

}
