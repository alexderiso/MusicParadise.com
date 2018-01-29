package Test_Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Bean.ClienteBean;
import Bean.GestoreOrdiniBean;

public class ClienteModelTest_jdbc {
	private static ClienteModel_jdbc model;

	static {
		model = new ClienteModel_jdbc();
	}
	 @Before
	    public void setUp() throws Exception{
	        DatabaseHelper.initializeDatabase();
	    }

	    @After
	    public void tearDown() throws Exception{
	        DatabaseHelper.initializeDatabase();
	    }
	    
	    @Test
	    public void TestLeggi() throws SQLException {
	    	ClienteBean cliente = model.leggi("Antonio", "12345678");
	    	ClienteBean cliente2 = model.leggi("paolo0", "1234567@");
	    	assertNotNull(cliente);
	    	assertEquals(null,cliente2);
	    	assertEquals(cliente.getEmail(),"a@gmail.com");
	    	
	    }
}
