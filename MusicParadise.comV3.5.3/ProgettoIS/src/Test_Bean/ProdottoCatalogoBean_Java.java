package Test_Bean;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Bean.ProdottoCatalogoBean;

public class ProdottoCatalogoBean_Java {

		ProdottoCatalogoBean prodottocatalogo;
		
		@BeforeClass
		public static void setUpClass() {

		}

		@AfterClass
		public static void tearDownClass() {

		}

		@Before
		public void setUp () {
			try {
				prodottocatalogo.setCodice(000);
				prodottocatalogo.setNome("Rydeen");
				prodottocatalogo.setNumDisp(15);
				prodottocatalogo.setColore("Bianco");
				prodottocatalogo.setMarca("Yamaha");
				prodottocatalogo.setDescrizione("Batteria della Yamaha");
				prodottocatalogo.setPeso(16);
				prodottocatalogo.setPrezzo(599);
				prodottocatalogo.setData(24/07);
				prodottocatalogo.setStrumento("Batteria");
			}
			catch (Exception e) {
				fail ();
			}
		} 
		
		@Test
		public void testGetCodice() {
			assertEquals(prodottocatalogo.getCodice(), 000);
		}
		
		@Test
		public void testSetCodice() {
			prodottocatalogo.setCodice(004);
			assertEquals(prodottocatalogo.getCodice(), 004);
		}
		
		@Test
		public void testGetNome() {
			assertEquals(prodottocatalogo.getNome(), "Rydeen");
		}
		
		@Test
		public void testSetNome() {
			prodottocatalogo.setNome("AS15");
			assertEquals(prodottocatalogo.getNome(), "AS15");
		}
		
		@Test
		public void testGetDisponibilita() {
			assertEquals(prodottocatalogo.getNumDisp(), 15);
		}
		
		@Test
		public void testSetDisponibilita() {
			prodottocatalogo.setNumDisp(17);
			assertEquals(prodottocatalogo.getNumDisp(), 17);
		}
		
		@Test
		public void testGetColore() {
			assertEquals(prodottocatalogo.getColore(), "Bianco");
		}
		
		@Test
		public void testSetColore() {
			prodottocatalogo.setColore("Giallo");
			assertEquals(prodottocatalogo.getColore(), "Giallo");
		}
		
		@Test
		public void testGetMarca() {
			assertEquals(prodottocatalogo.getMarca(), "Yamaha");
		}
		
		@Test
		public void testSetMarca() {
		    prodottocatalogo.setMarca("Ferrara");
		    assertEquals(prodottocatalogo.getMarca(), "Ferrara");
		}
		
		@Test
		public void testGetDescrizione() {
			assertEquals(prodottocatalogo.getDescrizione(), "Batteria della Yamaha");
		}
		
		@Test
		public void testSetDescrizione() {
			prodottocatalogo.setDescrizione("Sassofono della Ferrara");
			assertEquals(prodottocatalogo.getDescrizione(), "Sassofono della Ferrara");
		}
		
		@Test
		public void testGetPeso () {
			assertEquals(prodottocatalogo.getPeso(), "16");
		}
		
		public void testSetPeso () {
			prodottocatalogo.setPeso(7);
			assertEquals(prodottocatalogo.getPeso(), 7);
		}
		
		public void testGetPrezzo() {
			assertEquals(prodottocatalogo.getPrezzo(), 599);
		}
		
		public void testSetPrezzo() {
			prodottocatalogo.setPrezzo(349);
			assertEquals(prodottocatalogo.getPrezzo(), 349);
		}
		
		public void testGetDataInserimento() {
			assertEquals(prodottocatalogo.getData(), "07/20");
		}
		
		public void testSetDataInserimento() {
			prodottocatalogo.setData("04/25");
			assertEquals(prodottocatalogo.getData, "04/25");
		}
		
		public void testSetStrumento() {
			assertEquals(prodottocatalogo.getStrumento(), "Batteria");
		}
		
		public void testGetStrumento() {
			prodottocatalogo.setStrumento("Sassofono");
			assertEquals(prodottocatalogo.getStrumento(), "Sassofono");
		}
}
