package Test_Bean;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Bean.GestoreOrdiniBean;


public class GestoreOrdiniBean_Test {

	GestoreOrdiniBean gestoreOrdini;
	
	@BeforeClass
    public static void setUpClass() {
    }
     
    @AfterClass
    public static void tearDownClass() {
    }
    
	
	@Before
	public void setUp() {
		try {
			gestoreOrdini = new GestoreOrdiniBean();
			gestoreOrdini.setNome("Gestisci");
			gestoreOrdini.setCognome("ordine");
			gestoreOrdini.setEmail("ciao@gmail.com");
			gestoreOrdini.setNickName("GestiscoIo");
			gestoreOrdini.setPassword("Ciao");
			gestoreOrdini.setMatricola("051245abc");
			
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetMatricola() {
		assertEquals(gestoreOrdini.getMatricola(), "051245abc");
	}
	
	@Test
	public void testSetMatricola() {
		gestoreOrdini.setMatricola("Ciao123");
		assertEquals(gestoreOrdini.getMatricola(), "Ciao123");
	}

}
