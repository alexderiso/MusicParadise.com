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
        ordine.setNumOrdine(model.generaCodice());
        ordine.setStato("in preparazione");
        ordine.setDataConsegna(null);
        ordine.setProdotti(new ArrayList<ProdottoOrdineBean>());
        ordine.setTracking("");
        ordine.setTotale(100);
        ordine.setIndirizzo(new IndirizzoBean("Via Roma", "NAPOLI", 80050, "aLE", "sOMMA",1, "002258745"));
        ordine.setCarta(new CartaBean("01/18","1234567890123456","Antonio Spera",1));
        ordine.setUser("Antonio");
        
	}
	
	@Test
	public void TestRicercaPerId() throws SQLException {
		OrdineBean ordine = model.doRetrieveByKey(1);
		assertNotNull(ordine);
		assertEquals(ordine.getNumOrdine(),1);
	}
	
	@Test 
	public void TestOrdiniUtenti() throws SQLException {
		OrdineBean ordine = new OrdineBean();
        java.util.Date utilDate = new java.util.Date();
        ordine.setData(new Date(utilDate.getTime()));
        ordine.setCorriere("");
        ordine.setNumOrdine(model.generaCodice());
        ordine.setStato("in preparazione");
        ordine.setDataConsegna(null);
        ordine.setProdotti(new ArrayList<ProdottoOrdineBean>());
        ordine.setTracking("");
        ordine.setTotale(100);
        ordine.setIndirizzo(new IndirizzoBean("Via Marigliano", "Marigliano", 80034, "Paolo", "Rossi",2, "1783764782"));
        ordine.setCarta(new CartaBean("01/17","1234567890123456","Antonio Spera",0));
        ordine.setUser("Antonio");
        model.doSave(ordine.getIndirizzo(), ordine.getCarta(), ordine.getStato(), ordine.getCorriere(), ordine.getTracking(), ordine.getTotale(), ordine.getUser());
        ArrayList<OrdineBean> ordini = model.ordiniUtente("Antonio");
        assertEquals(5,ordini.size());
        assertEquals(0,ordini.get(0).getNumOrdine());
        assertEquals(1,ordini.get(1).getNumOrdine());
        assertEquals(2,ordini.get(2).getNumOrdine());
        assertEquals(3,ordini.get(3).getNumOrdine());
        assertEquals(4,ordini.get(4).getNumOrdine());
	}


}
