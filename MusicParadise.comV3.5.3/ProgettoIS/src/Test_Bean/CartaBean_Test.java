package Test_Bean;

import org.junit.Before;
import org.junit.Test;

import Bean.CartaBean;
import junit.framework.TestCase;

public class CartaBean_Test extends TestCase {
	CartaBean carta;
	@Before
	public void setUp() {
		try {
			carta = new CartaBean();
			carta.setCodice(001);
			carta.setNomeProprietario("Alessandro");
			carta.setNumCarta("4065258745632587");
			carta.setScadenza("07/20");
			
		}catch(Exception e) {
			fail();
		}
		
		 
		
	}
	
	@Test
	public void testGetCodice() {
		assertEquals(carta.getCodice(), 001);
	}
	@Test
	public void testSetCodice() {
		carta.setCodice(002);
		assertEquals(carta.getCodice(), 002);
	}
	@Test
	public void testGetNomeProprietario() {
		assertEquals(carta.getNomeProprietario(), "Alessandro");
	}
	@Test
	public void testSetNomeProprietario() {
		carta.setNomeProprietario("Luigi");
		assertEquals(carta.getNomeProprietario(), "Luigi");
		
	}
	@Test
	public void testGetNumCarta() {
		assertEquals(carta.getNumCarta(), "4065258745632587");
		
	}
	
	@Test
	public void testSetNumCarta() {
		carta.setNumCarta("1578965412547897");
		assertEquals(carta.getNumCarta(), "1578965412547897");
	}

	@Test
	public void testGetScadenza() {
		assertEquals(carta.getScadenza(), "07/20");
	}
	
	public void testSetScadenza() {
		carta.setScadenza("05/19");
		assertEquals(carta.getScadenza(), "05/19");
	}
}
