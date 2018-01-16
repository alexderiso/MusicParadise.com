package Test_Bean;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Bean.IndirizzoBean;
import junit.framework.TestCase;

public class Indirizzi_Test extends TestCase {

	IndirizzoBean indirizzo;
	
	@BeforeClass
    public static void setUpClass() {
    }
     
    @AfterClass
    public static void tearDownClass() {
    }
    
	
	@Before
	public void setUp() {
		try {
			indirizzo=new IndirizzoBean();
			indirizzo.setCap(80050);
			indirizzo.setCittà("Roma");
			indirizzo.setCodice(001);
			indirizzo.setCognome("Somma");
			indirizzo.setIndirizzo("Via Roma");
			indirizzo.setNome("Pasquale");
			indirizzo.setTelefono("0258785412");
			
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetIndirizzo() {
		assertEquals(indirizzo.getIndirizzo(), "Via Roma");
	}
	
	@Test
	public void testSetIndirizzo() {
		indirizzo.setIndirizzo("Via Napoli");
		assertEquals(indirizzo.getIndirizzo(), "Via Napoli");
	}
	
	@Test
	public void testGetCittà() {
		assertEquals(indirizzo.getCittà(), "Roma");
	}
	
	@Test
	public void testSetCittà() {
		indirizzo.setCittà("Milano");
		assertEquals(indirizzo.getCittà(), "Milano");
	}
	
	@Test
	public void testGetCap() {
		assertEquals(indirizzo.getCap(), 80050);
	}
	
	@Test
	public void testSetCap() {
		indirizzo.setCap(80051);
		assertEquals(indirizzo.getCap(),80051);
	}
	
	@Test
	public void testGetNome() {
		assertEquals(indirizzo.getNome(), "Pasquale");
	}
	
	@Test
    public void testSetNome() {
        
        indirizzo.setNome("Nicola");
        assertEquals(indirizzo.getNome(), "Nicola");
        
    }

   
    @Test
    public void testGetCognome() {
        
        assertEquals(indirizzo.getCognome(), "Somma");
        
    }

    
    @Test
    public void testSetCognome() {
        
        indirizzo.setCognome("Bella");
         assertEquals(indirizzo.getCognome(), "Bella");
    }



    
    @Test
    public void testGetTelefono() {
        
        assertEquals(indirizzo.getTelefono(), "0258785412");
        
    }

    
    @Test
    public void testSetTelefono() {
        
        indirizzo.setTelefono("0855874758");
        assertEquals(indirizzo.getTelefono(), "0855874758");
        
    }


}
