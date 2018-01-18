package Test_Bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import junit.framework.*;

import org.junit.Before;
import org.junit.Test;


import Bean.ProdottoOrdineBean;
import junit.framework.TestCase;

public class ProdottoOrdineBean_Test  {

	ProdottoOrdineBean prodotto;
	
	
	
	@Before
	public void setUp() {
		try {
			prodotto = new ProdottoOrdineBean();
			prodotto.setCodice(001);
			prodotto.setColore("Verde");
			prodotto.setDescrizione("Microfono professionale");
			prodotto.setMarca("Apple");
			prodotto.setNome("Microfono Apple");
			prodotto.setPeso(3);
			prodotto.setPrezzo(300);
			prodotto.setQuantit�(300);
			prodotto.setStrumento("Microfono");
		}catch (Exception e) {
			fail();
		}
	}
	
	
	@Test
	  public void TestGetCodice() {
		  assertEquals(prodotto.getCodice(), 001);
	  }
	  
	  @Test
	  public void TestSetCodice() {
		  prodotto.setCodice(003);
		  assertEquals(prodotto.getCodice(), 003);
	  }
	  @Test
	  public void TestGetNome() {
		  assertEquals(prodotto.getNome(), "Microfono Apple");
	  }
	  @Test
	  public void TestSetNome() {
		  prodotto.setNome("P45 B");
		  assertEquals(prodotto.getNome(), "P45 B");
	  }
	  @Test
	  public void TestGetColore() {
		  assertEquals(prodotto.getColore(), "Verde");
	  }
	  @Test
	  public void TestSetColore() {
		  prodotto.setColore("Rosso");
		  assertEquals(prodotto.getColore(), "Rosso");
	  }
	  @Test
	  public void TestGetMarca() {
		  assertEquals(prodotto.getMarca(), "Apple");
	  }
	  @Test
	  public void TestSetMarca() {
		  prodotto.setMarca("Yamaaha");
		  assertEquals(prodotto.getMarca(), "Yamaaha");
	  }
	  @Test
	  public void TestGetQuantita() {
		  assertEquals(prodotto.getQuantit�(), 300);
	  }
	  @Test
	  public void TestSetQuantita() {
		  prodotto.setQuantit�(2);
		  assertEquals(prodotto.getQuantit�(), 2);
	  }
	  @Test
	  public void TestGetDescrizione() {
		  assertEquals(prodotto.getDescrizione(), "Microfono professionale");
	  }
	  @Test
	  public void TestSetDescrizione() {
		  prodotto.setDescrizione("Tastiera della Yamahaa");
		  assertEquals(prodotto.getDescrizione(), "Tastiera della Yamahaa");
	  }
	  @Test
	  public void TestGetPeso () {
		  assertEquals(prodotto.getPeso(), 3);
	  }
	  
	  @Test
	  public void TestSetPeso() {
		  prodotto.setPeso(6);
		  assertEquals(prodotto.getPeso(), 6);
	  }
	  
	  /**
	   * Ci sono 3 parametri nel metodo assertEquals : 
	   * 1: valore attuale
	   * 2: valore atteso
	   * 3: valore delta per i numeri double, dove ci sar� la perdita di precisione
	   */
	@Test
	  public void TestGetPrezzo () {
		  assertEquals(prodotto.getPrezzo(),300.0,2);
	  }
	  /**
	   * Ci sono 3 parametri nel metodo assertEquals : 
	   * 1: valore attuale
	   * 2: valore atteso
	   * 3: valore delta per i numeri double, dove ci sar� la perdita di precisione
	   */
	  @Test
	  public void TestSetPrezzo() {
		  prodotto.setPrezzo(111);
		  assertEquals(prodotto.getPrezzo(),111.0,2);
	  }
	  
	  @Test
	  public void TestGetStrumento() {
		  assertEquals(prodotto.getStrumento(), "Microfono");
	  }
	  
	  @Test
	  public void TestSetStrumento() {
		  prodotto.setStrumento("Tastiera");
		  assertEquals(prodotto.getStrumento(), "Tastiera");
	  }
}

	

