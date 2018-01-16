package Test_Bean;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Bean.CartaBean;
import Bean.ClienteBean;
import Bean.IndirizzoBean;
import junit.framework.TestCase;

public class ClienteBean_Test extends TestCase {

	public ClienteBean_Test() {

	}
	 
	ClienteBean utente;
	ArrayList<CartaBean> carte2 = new ArrayList<CartaBean>();
	IndirizzoBean indirizzo=new IndirizzoBean("Via Roma", "NAPOLI", 80050, "aLE", "sOMMA", 001, "002258745");
	ArrayList<IndirizzoBean> indirizzi=new ArrayList<IndirizzoBean>();
	
	
    

    @Before
    public void setUp() 
    {
         try
        {
            utente = new ClienteBean();
            utente.setNome("Pasquale");
            utente.setCognome("Somma");
            utente.setNickName("CiaoCiao0");
            utente.setPassword("roma123456789");
            utente.setEmail("mm@gmail.com");
            utente.setCarte(carte2);
            utente.setIndirizzi(indirizzi);
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
    public void testGetNome() 
    {
        assertEquals(utente.getNome(), "Pasquale");
    }

    /**
     * Test of setNome method, of class ClienteBean.
     */
    @Test
    public void testSetNome() {
        
        utente.setNome("Giuseppe");
        assertEquals(utente.getNome(), "Giuseppe");
  }
    
    /**
     * Test of getCognome method, of class ClienteBean
     * 
     */
    @Test
    public void testGetCognome() {
        
        assertEquals(utente.getCognome(), "Somma");
        
    }

    /**
     * Test of setCognome method, of class ClienteBean.
     */
    @Test
    public void testSetCognome() {
        
        utente.setCognome("D'avino");
         assertEquals(utente.getCognome(), "D'avino");
    }
 
    
    @Test
    public void testAddCarta() {
    	CartaBean c = new CartaBean("01/18","1234567890123456","Antonio Spera",1);
    	assertEquals(carte2.size(), 0);
        carte2.add(c);
        assertEquals(carte2.size(), 1);
        assertEquals(carte2.get(0), c);
    }
    
    @Test
    public void testAddIndirizzo() {
    	assertEquals(indirizzi.size(), 0);
        indirizzi.add(indirizzo);
        assertEquals(indirizzi.size(), 1);
        assertEquals(indirizzi.get(0), indirizzo);
    }
    
    @Test
    public void testTrovaCarta() {
    	CartaBean c = new CartaBean("01/18","1234567890123456","Antonio Spera",1);
        utente.addCarta(c);
        assertEquals(c,utente.trovaCarta(1));
        assertNull(utente.trovaCarta(2));
    }
  

   
    /**
     * test
     */
    @Test
    public void testgetCognome() {
    	assertEquals(utente.getCognome(), "Somma");
    }
    




	

}
