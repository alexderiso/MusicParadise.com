package Test_Model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import Bean.ClienteBean;
import Bean.ProdottoCatalogoBean;

public class CarrelloModelTest_jdbc {

	 private static CarrelloModel_jdbc model;
	  
	  static {
	    model = new CarrelloModel_jdbc();
	  }
	  
	  @Test
	  public void TestDoSave() throws SQLException {
		  java.util.Date utilDate = new java.util.Date();
		  ProdottoCatalogoBean prd = new ProdottoCatalogoBean(20,"Batteria","nera","Yamaha","bella",500,1,"batteria",null,1,new Date(utilDate.getTime()),10);
		  ClienteBean utente = new ClienteBean("a@gmail.com","123456","Antonio","Antonio","Spera",null);
		  ArrayList<ProdottoCatalogoBean> prodotti = new ArrayList<ProdottoCatalogoBean>();
		  prodotti.add(prd);
		  model.doSave(prodotti,utente);
		  
	  } 
	  
	@Test 
	  public void TestLeggi() throws SQLException {
		  ArrayList<ProdottoCatalogoBean> carrello = new ArrayList<ProdottoCatalogoBean>();
		  ArrayList<String> foto = new ArrayList<String>();
		  foto.add("immaginiProdotti/yamaha_batteria.jpg");
		  foto.add("immaginiProdotti/yamaha_batteria2.jpg");
		  foto.add("immaginiProdotti/yamaha_batteria3.jpg");
		  
		  ProdottoCatalogoBean prd = new ProdottoCatalogoBean(0,"Batteria Yamaha","Bianco","Yamaha","Bella",3500.00,1,"batteria",foto,2,new Date(117, 05, 29),2);
		 
		  carrello.add(prd);
		  ClienteBean utente = new ClienteBean("a@gmail.com","123456","Antonio","Antonio","Spera",null);
		  model.doSave(carrello, utente);
		  
		  
		  ArrayList<ProdottoCatalogoBean> carrelloDB = model.leggi("Antonio");
		  assertEquals(carrello.get(0).getColore(),carrelloDB.get(0).getColore());
		  assertEquals(carrello.get(0).getCodice(),carrelloDB.get(0).getCodice());
		  assertEquals(carrello.get(0).getData(),carrelloDB.get(0).getData());
		  assertEquals(carrello.get(0).getDescrizione(),carrelloDB.get(0).getDescrizione());
		  assertEquals(carrello.get(0).getFoto(),carrelloDB.get(0).getFoto());
		  assertEquals(carrello.get(0).getMarca(),carrelloDB.get(0).getMarca());
		  assertEquals(carrello.get(0).getNome(),carrelloDB.get(0).getNome());
		  assertEquals(carrello.get(0).getNumDisp(),carrelloDB.get(0).getNumDisp());
		  assertEquals(carrello.get(0).getPeso(),carrelloDB.get(0).getPeso());
		  assertEquals(carrello.get(0).getPrezzo(),carrelloDB.get(0).getPrezzo(),00);
		  assertEquals(carrello.get(0).getQuantAgg(),carrelloDB.get(0).getQuantAgg());
		  assertEquals(carrello.get(0).getStrumento(),carrelloDB.get(0).getStrumento());
	  }
	
	@Test
	public void TestRemove() throws SQLException {
		ArrayList<ProdottoCatalogoBean> carrello = new ArrayList<ProdottoCatalogoBean>();
		  ArrayList<String> foto = new ArrayList<String>();
		  foto.add("immaginiProdotti/yamaha_batteria.jpg");
		  foto.add("immaginiProdotti/yamaha_batteria2.jpg");
		  foto.add("immaginiProdotti/yamaha_batteria3.jpg");
		  
		  ProdottoCatalogoBean prd = new ProdottoCatalogoBean(0,"Batteria Yamaha","Bianco","Yamaha","Bella",3500.00,1,"batteria",foto,2,new Date(117, 05, 29),2);
		 
		  carrello.add(prd);
		  ClienteBean utente = new ClienteBean("a@gmail.com","123456","Antonio","Antonio","Spera",null);
		  model.doSave(carrello, utente);
		  model.remove(utente);
		  ArrayList<ProdottoCatalogoBean> carrelloDB = model.leggi(utente.getNickName());
		  assertEquals(carrelloDB.size(),0);
	}
	  
	  

}
