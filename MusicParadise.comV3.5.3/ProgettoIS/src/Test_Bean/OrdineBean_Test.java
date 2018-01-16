package Test_Bean;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Bean.CartaBean;
import Bean.ClienteBean;
import Bean.IndirizzoBean;
import Bean.OrdineBean;
import Bean.ProdottoOrdineBean;
import junit.framework.TestCase;

public class OrdineBean_Test extends TestCase {
	
	public OrdineBean_Test() {
		
	}
	 
	OrdineBean ordine;
	 @Before
	    public void setUp() 
	    {
	         try
	        {
	            ordine = new OrdineBean();
	            java.util.Date utilDate = new java.util.Date();
	            ordine.setData(new Date(utilDate.getTime()));
	            ordine.setCorriere("");
	            ordine.setNumOrdine(1);
	            ordine.setStato("in preparazione");
	            ordine.setDataConsegna(null);
	            ordine.setProdotti(new ArrayList<ProdottoOrdineBean>());
	            ordine.setTracking("");
	            ordine.setTotale(100);
	            ordine.setIndirizzo(new IndirizzoBean("Via Roma", "NAPOLI", 80050, "aLE", "sOMMA",1, "002258745"));
	            ordine.setCarta(new CartaBean("01/18","1234567890123456","Antonio Spera",1));
	            ordine.setUser("Antonio");
	            
	            
	            
	        }
	        catch(Exception e)
	        {
	            fail();
	        }
	         

	    }

	    @After
	    public void tearDown() {}
	    
	    /**
	     * Test of getNome method, of class ClienteBean.
	     */
	    @Test
	    public void testSetUsername(){
	    	String username = "Valentino";
	    	ordine.setUser(username);
	        assertEquals(ordine.getUser(), "Valentino");
	    }
	    
	    @Test
	    public void testGetUsername(){
	        assertEquals(ordine.getUser(), "Antonio");
	    }
	    
	    @Test
	    public void testSetNumOrdine(){
	    	ordine.setNumOrdine(2);
	        assertEquals(ordine.getNumOrdine(), 2);
	    }
	    
	    @Test
	    public void testGetNumOrdine(){
	        assertEquals(ordine.getNumOrdine(), 1);
	    }
	    
	    @Test
	    public void testSetData(){
	    	java.util.Date utilDate = new java.util.Date();
            ordine.setData(new Date(utilDate.getTime()));
	        assertEquals(ordine.getData(), new Date(utilDate.getTime()));
	    }

	    @Test
	    public void testGetData(){
	    	java.util.Date utilDate = new java.util.Date();
	        assertEquals(ordine.getData(), new Date(utilDate.getTime()));
	    }
	    
	    @Test
	    public void testSetCorriere(){
	    	ordine.setCorriere("Bartolini");
	        assertEquals(ordine.getCorriere(), "Bartolini");
	    }
	    
	    @Test
	    public void testGetCorriere(){
	        assertEquals(ordine.getCorriere(), "");
	    }
	    
	    @Test
	    public void testSetStato(){
	    	ordine.setStato("spedito");
	        assertEquals(ordine.getStato(), "spedito");
	    }
	    
	    @Test
	    public void testGetStato(){
	        assertEquals(ordine.getStato(), "in preparazione");
	    }
	    
	    @Test
	    public void testSetDataConsegna(){
	    	java.util.Date utilDate = new java.util.Date();
            ordine.setDataConsegna(new Date(utilDate.getTime()));
	        assertEquals(ordine.getDataConsegna(), new Date(utilDate.getTime()));
	    }

	    @Test
	    public void testGetDataConsegna(){
	        assertEquals(ordine.getDataConsegna(), null);
	    }
	    
	    @Test
	    public void testSetTracking(){
            ordine.setTracking("1234567890");
	        assertEquals(ordine.getTracking(), "1234567890");
	    }

	    @Test
	    public void testGetTracking(){
	        assertEquals(ordine.getTracking(), "");
	    }
	    
	    @Test
	    public void testSetTotale(){
            ordine.setTotale(510);
	        assertEquals(ordine.getTotale(),510.0);
	    }

	    @Test
	    public void testGetTotale(){
	        assertEquals(ordine.getTotale(), 100.0);
	    }
	    
	    @Test
	    public void testSetIndirizzo(){
	    	IndirizzoBean indirizzo = new IndirizzoBean("Via Po","Marigliano",80034,"Antonio","Spera",1,"1234567890");
            ordine.setIndirizzo(indirizzo);
	        assertEquals(ordine.getIndirizzo(), indirizzo);
	    }
	    
	    @Test
	    public void testGetIndirizzo(){
	    	IndirizzoBean indirizzo = new IndirizzoBean("Via Roma", "NAPOLI", 80050, "aLE", "sOMMA", 1, "002258745");
	        assertEquals(ordine.getIndirizzo(), indirizzo);
	    }
	    

	    @Test
	    public void testSetCarta(){
	    	CartaBean carta = new CartaBean("01/18","1378467165263564","Ivan Esposito",1);
            ordine.setCarta(carta);
	        assertEquals(ordine.getCarta(), carta);
	    }
	    

	   
	    
	    
	    
	    
	    
	    
	

}
