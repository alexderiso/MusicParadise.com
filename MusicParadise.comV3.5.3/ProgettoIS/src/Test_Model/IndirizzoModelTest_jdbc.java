package Test_Model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import Bean.IndirizzoBean;

public class IndirizzoModelTest_jdbc {

	private static IndirizzoModel_jdbc model;
	  
	  static {
	    model = new IndirizzoModel_jdbc();
	  }
	  
	  
	  @Test
	  public void TestDoSave() throws SQLException {
		  IndirizzoBean indirizzo = new IndirizzoBean("Via Marigliano","Marigliano",80034,"Valentino","Rossi",model.generaCodice(),"0812347862");
		  model.doSave(indirizzo, "Antonio");
	  }
	  
	  @Test
	  public void TestLeggi() throws SQLException {
		  ArrayList<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
		  indirizzi = model.leggi("Antonio");
		  assertNotNull(indirizzi);
		  assertEquals(indirizzi.size(),4);
	  }

}
