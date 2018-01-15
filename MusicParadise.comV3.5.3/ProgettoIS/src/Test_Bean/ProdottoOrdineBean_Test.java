package Test_Bean;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Bean.ProdottoOrdineBean;
import junit.framework.TestCase;

public class ProdottoOrdineBean_Test extends TestCase{
		
	   ProdottoOrdineBean prodottoordine;
	   
	   
	   @BeforeClass
	   public static void setUpClass() {
		   
	   }
	   
	   @AfterClass
	   public static void tearDownClass() {
		   
	   }
	   
	   @Before
	   public void setUp() {
		   try {
			   prodottoordine = new ProdottoOrdineBean();
			   prodottoordine.setCodice(002);
			   prodottoordine.setNome("Darestone Elgbk");
			   prodottoordine.setColore("Nero");
			   prodottoordine.setMarca("Fender");
			   prodottoordine.setQuantità(1);
			   prodottoordine.setDescrizione("Chitarra della Fender");
			   prodottoordine.setPeso(4);
			   prodottoordine.setPrezzo(85);
			   prodottoordine.setStrumento("Chitarra");
			} catch (Exception e) {
				fail ();
			}	
	   }
	  
	  @Test
	  public void TestGetCodice() {
		  assertEquals(prodottoordine.getCodice(), 002);
	  }
	  
	  @Test
	  public void TestSetCodice() {
		  prodottoordine.setCodice(003);
		  assertEquals(prodottoordine.getCodice(), 003);
	  }
	  @Test
	  public void TestGetNome() {
		  assertEquals(prodottoordine.getCodice(), "Darestone Elgbk");
	  }
	  @Test
	  public void TestSetNome() {
		  prodottoordine.setNome("P45 B");
		  assertEquals(prodottoordine.getNome(), "P45 B");
	  }
	  @Test
	  public void TestGetColore() {
		  assertEquals(prodottoordine.getColore(), "Nero");
	  }
	  @Test
	  public void TestSetColore() {
		  prodottoordine.setColore("Rosso");
		  assertEquals(prodottoordine.getColore(), "Rosso");
	  }
	  @Test
	  public void TestGetMarca() {
		  assertEquals(prodottoordine.getMarca(), "Fender");
	  }
	  @Test
	  public void TestSetMarca() {
		  prodottoordine.setMarca("Yamaaha");
		  assertEquals(prodottoordine.getMarca(), "Yamaaha");
	  }
	  @Test
	  public void TestGetQuantita() {
		  assertEquals(prodottoordine.getQuantità(), 1);
	  }
	  @Test
	  public void TestSetQuantita() {
		  prodottoordine.setQuantità(2);
		  assertEquals(prodottoordine.getQuantità(), 2);
	  }
	  @Test
	  public void TestGetDescrizione() {
		  assertEquals(prodottoordine.getDescrizione(), "Chitarra della Fender");
	  }
	  @Test
	  public void TestSetDescrizione() {
		  prodottoordine.setDescrizione("Tastiera della Yamahaa");
		  assertEquals(prodottoordine.getDescrizione(), "Tastiera della Yamahaa");
	  }
	  @Test
	  public void TestGetPeso () {
		  assertEquals(prodottoordine.getPeso(), 4);
	  }
	  
	  @Test
	  public void TestSetPeso() {
		  prodottoordine.setPeso(6);
		  assertEquals(prodottoordine.getPeso(), 6);
	  }
	  
	  @Test
	  public void TestGetPrezzo () {
		  assertEquals(prodottoordine.getPrezzo(), "85");
	  }
	  
	  @Test
	  public void TestSetPrezzo() {
		  prodottoordine.setPrezzo(111);
		  assertEquals(prodottoordine.getPrezzo(), 111);
	  }
	  
	  @Test
	  public void TestGetStrumento() {
		  assertEquals(prodottoordine.getStrumento(), "Chitarra");
	  }
	  
	  @Test
	  public void TestSetStrumento() {
		  prodottoordine.setStrumento("Tastiera");
		  assertEquals(prodottoordine.getStrumento(), "Tastiera");
	  }
}
