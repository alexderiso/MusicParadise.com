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
		assertEquals(prodotti.size(), 1);
	}
	
	@Test
	public void TestDoSave() throws SQLException {
		ProdottoCatalogoBean prodotto = new ProdottoCatalogoBean();
		ArrayList<ProdottoCatalogoBean> prodotti = new ArrayList<>();
		prodotti.add(prodotto);
		model.doSave(prodotti);
		
		
	}

}
