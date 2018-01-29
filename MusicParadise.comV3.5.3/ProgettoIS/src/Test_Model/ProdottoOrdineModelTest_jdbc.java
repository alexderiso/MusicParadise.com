package Test_Model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import Bean.ProdottoCatalogoBean;
import Bean.ProdottoOrdineBean;
import junit.framework.TestCase;

public class ProdottoOrdineModelTest_jdbc {
	
	private static ProdottoOrdineModel_jdbc model;

	static {
		model = new ProdottoOrdineModel_jdbc();
	}
	
	@Test
	public void TestProdottiOrdine() throws SQLException {
		Collection<ProdottoOrdineBean> prodotti= model.prodottiOrdine(1);
		assertNotNull(prodotti);
		assertEquals(prodotti.size(), 2);
	}
	
	@Test
	public void TestDoSave() throws SQLException {
		ArrayList<String> foto = new ArrayList<String>();
		  foto.add("immaginiProdotti/yamaha_batteria.jpg");
		  foto.add("immaginiProdotti/yamaha_batteria2.jpg");
		  foto.add("immaginiProdotti/yamaha_batteria3.jpg");
		  
		  ProdottoCatalogoBean prd = new ProdottoCatalogoBean(1,"Batteria","Bianco","Yamaha","Bella",7500.00,1,"batteria",foto,1,new Date(118, 0, 26),2);
		ArrayList<ProdottoCatalogoBean> prodotti = new ArrayList<>();
		prodotti.add(prd);
		model.doSave(prodotti);
		
		
	}

}
