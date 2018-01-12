package Test_Bean;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Bean.CarrelloBean;
import Bean.ClienteBean;
import Bean.ProdottoCatalogoBean;
import junit.framework.TestCase;

public class CarrelloBean_Test extends TestCase {
	
	public CarrelloBean_Test() {
		
	}
	
	CarrelloBean carrello = new CarrelloBean();
	 java.util.Date utilDate = new java.util.Date();
	ProdottoCatalogoBean prodotto1 = new ProdottoCatalogoBean(1,"Batteria yamaha","Nero","Yamaha","Bella",500.00,1,"Batteria",null,
			 0,new Date(utilDate.getTime()),10);
	 
	 ProdottoCatalogoBean prodotto2 = new ProdottoCatalogoBean(2,"Chitarra yamaha","Marrone","Yamaha","Bella",600.00,1,"Batteria",null,
			 0,new Date(utilDate.getTime()),10);
	@Before
    public void setUp() 
    {
         try
        {

        	
        	 
        	 carrello.addProduct(prodotto1);
        	 carrello.addProduct(prodotto2);
        }
        catch(Exception e)
        {
            fail();
        }
         

    }

    @After
    public void tearDown() {}
    
    @Test
    public void testSizeArrayList() {
    	assertEquals(carrello.getProducts().size(),2);
    	assertNotNull(carrello.getTotale());
    }
    
    @Test 
    public void testAggiungiProdottoesistente() {
    	
    	carrello.addProduct(prodotto1);
    	assertEquals(carrello.getProducts().get(0).getQuantAgg(),2);
    	assertEquals(carrello.getProducts().size(),2);
    	assertEquals(carrello.getTotale(),1600.00);
    	
    }
    
    @Test
    public void testAggiungiNuovoProdotto() {
    	java.util.Date utilDate = new java.util.Date();
    	ProdottoCatalogoBean prodotto3 = new ProdottoCatalogoBean(3,"Batteria yamaha","Nero","Yamaha","Bella",600.00,1,"Batteria",null,
    			 0,new Date(utilDate.getTime()),10);
    	carrello.addProduct(prodotto3);
    	assertEquals(carrello.getProducts().size(),3);
    	assertEquals(carrello.getTotale(),1700.00);
    }
    
    @Test
    public void testDeleteProduct() {
    	carrello.deleteProduct(prodotto1);
    	assertEquals(carrello.getProducts().size(),1);
    	assertEquals(carrello.getTotale(),600.00);
    }
    
    @Test
    public void countingPrds() {
    	assertEquals(carrello.countingPrds(),2);
    }
    
    @Test
    public void testGetQuantit‡ByCodice() {
    	int quantit‡ = carrello.getQuantit‡ByCodice(prodotto1.getCodice());
    	assertEquals(carrello.getProducts().get(0).getQuantAgg(),quantit‡);
    }
	

}
