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
		ProdottoCatalogoBean prd = new ProdottoCatalogoBean(0,"Batteria Yamaha","Bianco","Yamaha","Bella",3500.00,1,"batteria",null,1,new Date(117, 05, 29),2);
		ClienteBean utente = new ClienteBean("a@gmail.com","123456","Antonio","Antonio","Spera",null);
		ArrayList<ProdottoCatalogoBean> prodotti = new ArrayList<ProdottoCatalogoBean>();
		prodotti.add(prd);
		model.doSave(prodotti,utente);

	} 



	@Test 
	public void TestLeggi() throws SQLException {
		ArrayList<String> foto = new ArrayList<String>();
		foto.add("immaginiProdotti/yamaha_batteria.jpg");
		foto.add("immaginiProdotti/yamaha_batteria2.jpg");
		foto.add("immaginiProdotti/yamaha_batteria3.jpg");

		ProdottoCatalogoBean prd = new ProdottoCatalogoBean(0,"Batteria Yamaha","Bianco","Yamaha","Bella",3500.00,1,"batteria",foto,1,new Date(117, 05, 29),0);
		ArrayList<ProdottoCatalogoBean> prodotti = new ArrayList<ProdottoCatalogoBean>();
		prodotti.add(prd);
		ClienteBean utente = new ClienteBean("a@gmail.com","123456","Antonio","Antonio","Spera",null);
		model.doSave(prodotti,utente);

		ArrayList<ProdottoCatalogoBean> carrelloDB = model.leggi("Antonio");
		assertEquals(prd.getColore(),carrelloDB.get(0).getColore());
		assertEquals(prd.getCodice(),carrelloDB.get(0).getCodice());
		assertEquals(prd.getData(),carrelloDB.get(0).getData());
		assertEquals(prd.getDescrizione(),carrelloDB.get(0).getDescrizione());
		assertEquals(prd.getFoto(),carrelloDB.get(0).getFoto());
		assertEquals(prd.getMarca(),carrelloDB.get(0).getMarca());
		assertEquals(prd.getNome(),carrelloDB.get(0).getNome());
		assertEquals(prd.getNumDisp(),carrelloDB.get(0).getNumDisp());
		assertEquals(prd.getPeso(),carrelloDB.get(0).getPeso());
		assertEquals(prd.getPrezzo(),carrelloDB.get(0).getPrezzo(),00);
		assertEquals(prd.getQuantAgg(),carrelloDB.get(0).getQuantAgg());
		assertEquals(prd.getStrumento(),carrelloDB.get(0).getStrumento());
	}


	@Test
	public void TestRemove() throws SQLException {
		ClienteBean utente = new ClienteBean("a@gmail.com","123456","Antonio","Antonio","Spera",null);
		model.remove(utente);
		ArrayList<ProdottoCatalogoBean> carrelloDB = model.leggi(utente.getNickName());
		assertEquals(carrelloDB.size(),0);
	}




}
