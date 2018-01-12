package Model;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import Bean.*;
import junit.framework.TestCase;

public class CLIENTE_TEST  extends TestCase{
	private static ClienteModel model;
	CarrelloBean carrello=new CarrelloBean();
	
	static {
		model=new ClienteModel();
		
	}

	@Test
	public void testSalvaAccountCliente() {
		ClienteBean cliente = new ClienteBean("aaaabbb@gmail.com", "amicomio123", "ciaociao", "alex", "deriso0",carrello); 
		try {
			ClienteBean utente=model.leggi(cliente.getNickName(), cliente.getPassword());
			assertEquals(utente, null);
			
			model.doSave(cliente);
			assertEquals(utente, cliente);
			
			model.doSave(cliente);
			//mi aspetto un eccezione sql
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
