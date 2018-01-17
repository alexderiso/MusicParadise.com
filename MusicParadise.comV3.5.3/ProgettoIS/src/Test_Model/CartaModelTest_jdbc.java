package Test_Model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import Bean.CartaBean;
import Bean.ClienteBean;
import Bean.IndirizzoBean;

public class CartaModelTest_jdbc {

	private static CartaModel_jdbc model;
	  
	  static {
	    model = new CartaModel_jdbc();
	  }
	  
	  @Test
	  public void TestDoSave() throws SQLException {
		  CartaBean carta = new CartaBean("01/17","7265365789187635","Paolo Rossi",model.generaCodice());
		  ClienteBean utente = new ClienteBean("a@gmail.com","123456","Antonio","Antonio","Spera",null);
		  model.doSave(carta, utente);
		  ArrayList<CartaBean> carte = new ArrayList<CartaBean>();
		  carte = model.leggi("Antonio");
		  assertEquals(carte.size(),2);
	  }
	  
	  @Test
	  public void TestLeggi() throws SQLException {
		  ArrayList<CartaBean> carte = new ArrayList<CartaBean>();
		  carte = model.leggi("Antonio");
		  assertEquals(carte.size(),2);
	  }

}