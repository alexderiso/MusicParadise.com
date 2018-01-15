package Test_Bean;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Bean.UtenteBean;
import junit.framework.TestCase;

public class UtenteBean_Test extends TestCase{

	UtenteBean utente;
	
	@BeforeClass
	public static void setUpClass() {

	}

	@AfterClass
	public static void tearDownClass() {

	}

	@Before
	public void setUp () {
		try {
			
			utente = new UtenteBean();
			utente.setNome("Mario");
			utente.setCognome("Rossi");
			utente.setNickname("MarioRossi");
			utente.setPassword("12345");
			utente.setEmail("mariorossi@gmail.com")
		} catch (Exception e) {
			fail ();
		}	  
	}

	@Test
	public void testGetNome() {
		assertEquals(utente.getNome(), "Mario");
	}

	@Test
	public void testSetNome() {
		utente.setNome("Luca");
		assertEquals(utente.getNome(), "Luca");
	}

	@Test
	public void testGetCognome () {
		assertEquals(utente.getCognome(), "Rossi");
	}

	@Test
	public void testSetCognome () {
		utente.setCognome("Bianchi");
		assertEquals(utente.getCognome(), "Bianchi")
	}

	@Test
	public void testGetNickname() {
		assertEquals(utente.getNickname(), "MarioRossi");
	}

	@Test
	public void testSetNickname() {
		utente.setNickname("LucaBianchi");
		assertEquals(utente.getNickname(), "LucaBianchi");
	}

	@Test
	public void testGetPassword () {
		assertEquals(utente.getPassword(), "12345");
	}

	@Test
	public void testSetPassword () {
		utente.setPassword ("67890");
		assertEquals(utente.getPassword(), "67890");
	}

	@Test
	public void testGetEmail () {
		assertEquals(utente.getEmail(), "mariorossi@gmail.com");
	}

	public void testSetEmail () {
		utente.setEmail ("lucabianchi@gmail.com");
		assertEquals(utente.getEmail(), "lucabianchi@gmail.com");
	}
}
