package Test_Model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Bean.IndirizzoBean;

public class IndirizzoModelTest_jdbc {

	private static IndirizzoModel_jdbc model;
	  
	  static {
	    model = new IndirizzoModel_jdbc();
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
	  public void TestDoSave() throws SQLException {
		  IndirizzoBean indirizzo = new IndirizzoBean("Via Marigliano","Marigliano",80034,"Valentino","Rossi",0,"0812347862");
		  model.doSave(indirizzo, "Antonio");
	  }
	  
	  @Test
	  public void TestLeggi() throws SQLException {
		  ArrayList<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
		  indirizzi = model.leggi("Antonio");
		  assertNotNull(indirizzi);
		  assertEquals(indirizzi.size(),1);
	  }
	  
	  @Test
	  public void TestRimuoviIndirizzo() throws SQLException {
		  model.rimuoviIndirizzo(1);
		  ArrayList<IndirizzoBean> indirizzi = model.leggi("Antonio");
		  assertNotNull(indirizzi);
		  assertEquals(indirizzi.size(),0);
	  }

}
