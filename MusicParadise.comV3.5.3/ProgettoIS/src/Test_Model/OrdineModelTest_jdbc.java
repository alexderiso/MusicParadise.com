package Test_Model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
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
		
		
		OrdineBean ordine = new OrdineBean();
        java.util.Date utilDate = new java.util.Date();
        ordine.setData(new Date(utilDate.getTime()));
        ordine.setCorriere("");
        ordine.setStato("in preparazione");
        ordine.setDataConsegna(null);
        ordine.setProdotti(new ArrayList<ProdottoOrdineBean>());
        ordine.setTracking("");
        ordine.setTotale(100);
        ordine.setIndirizzo(new IndirizzoBean("Via Marigliano", "Marigliano", 80034, "Paolo", "Rossi",1, "1783764782"));
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
        assertEquals(3,ordini.size());
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
	@Test
	public void TestAggiornaOrdineConsegnato() throws SQLException {
		 java.util.Date utilDate = new java.util.Date();
		model.aggiornaConsegnato(1);
		
		OrdineBean ordine = model.doRetrieveByKey(1);
		assertEquals(ordine.getStato(),"consegnato");
		assertEquals(ordine.getTracking(),"1214235435");
		assertEquals(ordine.getDataConsegna(),new Date(118,0,22));
		assertEquals(ordine.getCorriere(),"Bartolini");
		
	}
	@Test
	public void TestRicercaPerNickname() throws SQLException {
		ArrayList<OrdineBean> ordine = model.ordiniUtente("Antonio");
		assertNotNull(ordine);
		assertEquals(ordine.size(),3);
	}
	
	@Test
	public void TestListaOrdini() throws SQLException {
		Collection<OrdineBean> ordine = model.listaOrdini();
		assertNotNull(ordine);
		assertEquals(ordine.size(),3);
	}
	
	@Test
	public void TestdoRetrieveByStato() throws SQLException {
		Collection<OrdineBean> ordine =model.doRetrieveByStato("in preparazione");
		assertNotNull(ordine);
		assertEquals(ordine.size(),1);
	}
	
	
	


}
