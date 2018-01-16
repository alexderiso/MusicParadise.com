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
		  IndirizzoBean indirizzo = new IndirizzoBean("Via Marigliano","Marigliano",80034,"Paolo","Rossi",model.generaCodice(),"1783764782");
		  model.doSave(indirizzo, "Antonio");
		  ArrayList<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
		  indirizzi = model.leggi("Antonio");
		  assertEquals(indirizzi.size(),3);
	  }
	  
	  @Test
	  public void TestLeggi() throws SQLException {
		  ArrayList<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
		  indirizzi = model.leggi("Antonio");
		  assertEquals(indirizzi.size(),3);
	  }

}
