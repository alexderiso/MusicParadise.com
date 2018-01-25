package Test_DBUnit.prodottoOrdine;

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

import Model.ProdottoOrdineModel;
import Test_DBUnit.DatabaseProperty;

public class ProdottoOrdineTestCase extends DBTestCase {
	
	public ProdottoOrdineTestCase(String name) {
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
		PreparedStatement stm = connection.getConnection().prepareStatement("INSERT INTO prodottoordine (codice, quantita, nome, colore, marca, descrizione, peso, prezzo, strumento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		stm.setInt(1, 3);
		stm.setInt(2, 2);
		stm.setString(3, "Tastiera Casio");
		stm.setString(4, "Nero");
		stm.setString(5,"Casio");
		stm.setString(6, "Bella");
		stm.setDouble(7, 2.00);
		stm.setDouble(8, 150.00);
		stm.setString(9, "tastiera");
		
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable("prodottoordine");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/prodottoOrdine/insert_prodottoordine_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	public void testProdottiOrdine() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT * FROM prodottoordine JOIN composizione WHERE prodottoordine.CODICE = composizione.PRODOTTO AND composizione.ORDINE = ?");
		stm.setInt(1,0);
		
		ITable actualTable = connection.createTable("retrieve_prodottoordine", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/prodottoOrdine/retrieve_prodottoordine_by_ordine_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("prodottoordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	public void testGeneraCodice() throws Exception{
		IDatabaseConnection connection = getConnection();
		PreparedStatement stm = connection.getConnection().prepareStatement("SELECT COUNT(*) AS TOTAL FROM prodottoordine ");
		
		ITable actualTable = connection.createTable("cod_prodottoordine", stm);
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/prodottoOrdine/cod_prodottoordine_oracle.xml"));
		ITable expectedTable = expectedDataSet.getTable("cod_prodottoordine");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/Test_DBUnit/full.xml"));
	}

}
