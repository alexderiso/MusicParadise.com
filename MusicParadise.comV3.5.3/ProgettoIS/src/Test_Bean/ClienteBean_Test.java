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
		indirizzi.add(indirizzo);
	}
	
	ClienteBean utente;
	ArrayList<CartaBean> carte2;
	IndirizzoBean indirizzo=new IndirizzoBean("Via Roma", "NAPOLI", 80050, "aLE", "sOMMA", 001, "002258745");
	ArrayList<IndirizzoBean> indirizzi=new ArrayList<>();
	
	
    

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
    public void testGetIndirizzo() {
    	IndirizzoBean indirizzo2=new IndirizzoBean("Via Roma", "NAPOLI", 80050, "aLE", "sOMMA", 001, "002258745");
    	assertEquals(indirizzi.get(0),indirizzo2);
    }
    
    

    
    
   



   
    /**
     * test
     */
    @Test
    public void testgetCognome() {
    	assertEquals(utente.getCognome(), "Somma");
    }
    




	

}
