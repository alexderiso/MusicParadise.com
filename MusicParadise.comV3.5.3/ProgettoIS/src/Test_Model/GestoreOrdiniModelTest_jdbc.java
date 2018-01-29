package Test_Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Bean.GestoreOrdiniBean;

public class GestoreOrdiniModelTest_jdbc {
	private static GestoreOrdineModel_jdbc model;

	static {
		model = new GestoreOrdineModel_jdbc();
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
	    	GestoreOrdiniBean gestore = model.leggi("paolo10", "1234567@");
	    	GestoreOrdiniBean gestore2 = model.leggi("paolo0", "1234567@");
	    	assertNotNull(gestore);
	    	assertEquals(null,gestore2);
	    	assertEquals(gestore.getEmail(),"b@gmail.com");
	    	assertEquals(gestore.getMatricola(),"123456789");
	    	
	    }
}
