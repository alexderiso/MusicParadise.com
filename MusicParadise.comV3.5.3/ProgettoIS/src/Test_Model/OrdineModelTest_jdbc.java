package Test_Model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import Bean.CartaBean;
import Bean.IndirizzoBean;
import Bean.OrdineBean;
import Bean.ProdottoOrdineBean;

public class OrdineModelTest_jdbc {

	private static OrdineModel_jdbc model;

	static {
		model = new OrdineModel_jdbc();
	}

	@Test
	public void TestDoSave() throws SQLException {
		
		
		OrdineBean ordine = new OrdineBean();
        java.util.Date utilDate = new java.util.Date();
        ordine.setData(new Date(utilDate.getTime()));
        ordine.setCorriere("");
        ordine.setStato("in preparazione");
        ordine.setDataConsegna(null);
        ordine.setProdotti(new ArrayList<ProdottoOrdineBean>());
        ordine.setTracking("");
        ordine.setTotale(100);
        ordine.setIndirizzo(new IndirizzoBean("Via Marigliano", "Marigliano", 80034, "Paolo", "Rossi",2, "1783764782"));
        ordine.setCarta(new CartaBean("01/17","1234567890123456","Antonio Spera",1));
        ordine.setUser("Antonio");
        model.doSave(ordine.getIndirizzo(), ordine.getCarta(), "in preparazione", ordine.getCorriere(), ordine.getTracking(), ordine.getTotale(), ordine.getUser());
        
	}
	
	@Test
	public void TestRicercaPerId() throws SQLException {
		OrdineBean ordine = model.doRetrieveByKey(1);
		assertNotNull(ordine);
		assertEquals(ordine.getNumOrdine(),1);
	}
	
	@Test 
	public void TestOrdiniUtenti() throws SQLException {
        ArrayList<OrdineBean> ordini = model.ordiniUtente("Antonio");
        assertNotNull(ordini);
        assertEquals(4,ordini.size());
	}
	
	@Test
	public void TestAggiornaOrdine() throws SQLException {
		 java.util.Date utilDate = new java.util.Date();
		model.aggiorna(3, "1234567890", new Date(118,0,26), "GLS");
		
		OrdineBean ordine = model.doRetrieveByKey(3);
		assertEquals(ordine.getStato(),"spedito");
		assertEquals(ordine.getTracking(),"1234567890");
		assertEquals(ordine.getDataConsegna(),new Date(118,0,26));
		assertEquals(ordine.getCorriere(),"GLS");
		
	}


}
