package Test_Model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import Bean.ProdottoCatalogoBean;

public class ProdottoModelTest_jdbc {
	
	private static ProdottoModel_jdbc model;

	static {
		model = new ProdottoModel_jdbc();
	}
	
	@Test
	public void TestRicercaAll() throws SQLException {
		ArrayList<ProdottoCatalogoBean> prodotti = model.doRetrieveAll();
		assertNotNull(prodotti);
		assertEquals(prodotti.size(),1);
	}
	
	@Test
	public void TestRicercaPerStrumento() throws SQLException {
		Collection<ProdottoCatalogoBean> prodotti = model.doRetrieveByInstruments("batteria");
		Collection<ProdottoCatalogoBean> prodottoAssente = model.doRetrieveByInstruments("tastiera");
		assertNotNull(prodotti);
		assertEquals(prodotti.size(),1);
		assertEquals(prodottoAssente.size(),0);
	}
	
	@Test
	public void TestRicercaPerMarca() throws SQLException {
		Collection<ProdottoCatalogoBean> prodotti = model.doRetrieveByMarca("Yamaha");
		Collection<ProdottoCatalogoBean> prodottoAssente = model.doRetrieveByMarca("Casio");
		assertNotNull(prodotti);
		assertEquals(prodotti.size(),1);
		assertEquals(prodottoAssente.size(),0);
	}
	
	@Test
	public void TestRicercaPerID() throws SQLException {
		ProdottoCatalogoBean prodotto = model.doRetrieveByKey(0);
		ProdottoCatalogoBean prodottoAssente = model.doRetrieveByKey(1);
		assertNotNull(prodotto);
		assertEquals(prodotto.getCodice(),0);
		assertEquals(prodottoAssente,null);
	}
	
	@Test
	public void TestRicercaPerNome() throws SQLException {
		Collection<ProdottoCatalogoBean> prodotti = model.doRetrieveByName("Batteria Yamaha");
		Collection<ProdottoCatalogoBean> prodottoAssente = model.doRetrieveByName("Chitarra");
		assertNotNull(prodotti);
		assertEquals(prodotti.size(),1);
		assertEquals(prodottoAssente.size(),0);
	}
	
	@Test
	public void TestAggiornaProdotto() throws SQLException {
		ArrayList<ProdottoCatalogoBean> carrello = new ArrayList<ProdottoCatalogoBean>();
		  ArrayList<String> foto = new ArrayList<String>();
		  foto.add("immaginiProdotti/yamaha_batteria.jpg");
		  foto.add("immaginiProdotti/yamaha_batteria2.jpg");
		  foto.add("immaginiProdotti/yamaha_batteria3.jpg");
		  
		  ProdottoCatalogoBean prd = new ProdottoCatalogoBean(0,"Batteria Yamaha","Bianco","Yamaha","Bella",3500.00,1,"batteria",foto,1,new Date(117, 05, 29),2);
		 
		  carrello.add(prd);
		  
		  model.aggiorna(carrello);
		  
		  ProdottoCatalogoBean prodottoDB = model.doRetrieveByKey(0);
		  assertEquals(prodottoDB.getNumDisp(),prd.getNumDisp()-prd.getQuantAgg());
	}


}
