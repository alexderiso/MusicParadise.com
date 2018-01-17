package Test_Bean;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import Bean.ProdottoCatalogoBean;
import junit.framework.TestCase;

public class ProdottoCatalogoBean_Test extends TestCase {


	ProdottoCatalogoBean prodotto;

	@Before
	public void setUp() {
		try {
			prodotto=new ProdottoCatalogoBean();
			java.util.Date utilDate = new java.util.Date();
			prodotto.setData(new Date(utilDate.getTime()));
			prodotto.setCodice(001);
			prodotto.setColore("Giallo");
			prodotto.setDescrizione("Chitarra di prova");
			prodotto.setMarca("Samsung");
			prodotto.setNome("Chitarra Samsung");
			prodotto.setNumDisp(100);
			prodotto.setPeso(300);
			prodotto.setPrezzo(300);
			prodotto.setStrumento("Chitarra");
		}catch (Exception e) {
			fail();
		}
	}
	


	@Test
	public void testGetCodice() {
		assertEquals(prodotto.getCodice(), 001);
	}

	@Test
	public void testSetCodice() {
		prodotto.setCodice(004);
		assertEquals(prodotto.getCodice(), 004);
	}

	@Test
	public void testGetNome() {
		assertEquals(prodotto.getNome(), "Chitarra Samsung");
	}

	@Test
	public void testSetNome() {
		prodotto.setNome("AS15");
		assertEquals(prodotto.getNome(), "AS15");
	}

	@Test
	public void testGetDisponibilita() {
		assertEquals(prodotto.getNumDisp(), 100);
	}

	@Test
	public void testSetDisponibilita() {
		prodotto.setNumDisp(17);;
		assertEquals(prodotto.getNumDisp(), 17);
	}

	@Test
	public void testGetColore() {
		assertEquals(prodotto.getColore(), "Giallo");
	}

	@Test
	public void testSetColore() {
		prodotto.setColore("Verde");
		assertEquals(prodotto.getColore(), "Verde");
	}

	@Test
	public void testGetMarca() {
		assertEquals(prodotto.getMarca(), "Samsung");
	}

	@Test
	public void testSetMarca() {
		prodotto.setMarca("Ferrara");
		assertEquals(prodotto.getMarca(), "Ferrara");
	}

	@Test
	public void testGetDescrizione() {
		assertEquals(prodotto.getDescrizione(), "Chitarra di prova");
	}

	@Test
	public void testSetDescrizione() {
		prodotto.setDescrizione("Sassofono della Ferrara");
		assertEquals(prodotto.getDescrizione(), "Sassofono della Ferrara");
	}

	@Test
	public void testGetPeso () {
		assertEquals(prodotto.getPeso(), 300);
	}
	@Test
	public void testSetPeso () {
		prodotto.setPeso(7);
		assertEquals(prodotto.getPeso(), 7);
	}
	@Test
	public void testGetPrezzo() {
		assertEquals(prodotto.getPrezzo(), 300.0);
	}
	@Test
	public void testSetPrezzo() {
		prodotto.setPrezzo(349);
		assertEquals(prodotto.getPrezzo(), 349.0);
	}
	@Test
	public void testGetDataInserimento() {
		java.util.Date utilDate = new java.util.Date();
        assertEquals(prodotto.getData(), new Date(utilDate.getTime()));
		
	}
	@Test
	public void testSetDataInserimento() {
		java.util.Date utilDate = new java.util.Date();
		prodotto.setData(new Date(utilDate.getTime()));
		assertEquals(prodotto.getData(), new Date(utilDate.getTime()));
	}
	@Test
	public void testSetStrumento() {
		assertEquals(prodotto.getStrumento(), "Chitarra");
	}
	@Test
	public void testGetStrumento() {
		prodotto.setStrumento("Sassofono");
		assertEquals(prodotto.getStrumento(), "Sassofono");
	}
}





