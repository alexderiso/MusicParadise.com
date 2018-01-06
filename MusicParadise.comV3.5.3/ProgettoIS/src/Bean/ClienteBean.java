package Bean;

import java.util.ArrayList;

public class ClienteBean extends UtenteBean{
	
	/**
	 * 
	 */
	private ArrayList<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
	private ArrayList<CartaBean> carte = new ArrayList<CartaBean>();
	private CarrelloBean cart;
	public ClienteBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClienteBean(String email, String password, String nickName, String nome, String cognome, CarrelloBean cart) {
		super(email, password, nickName, nome, cognome);
		carte = null;
		indirizzi = null;
		this.cart = cart;
	}
	
	public ArrayList<IndirizzoBean> getIndirizzi() {
		return indirizzi;
	}

	public void setIndirizzi(ArrayList<IndirizzoBean> indirizzi) {
		this.indirizzi = indirizzi;
	}

	public ArrayList<CartaBean> getCarte() {
		return carte;
	}

	public void setCarte(ArrayList<CartaBean> carte2) {
		this.carte = carte2;
	}
	
	public void addCarta(CartaBean carta) {
		carte.add(carta);
	}
	
	public void addIndirizzo(IndirizzoBean indirizzo) {
		indirizzi.add(indirizzo);
	}
	
	
	
	public CartaBean trovaCarta(int cod) {
		for(CartaBean temp : carte) {
			if(temp.getCodice() == cod);
			return temp;
		}
		return null;
	}
	
	public IndirizzoBean trovaIndirizzo(int cod) {
		for(IndirizzoBean temp : indirizzi) {
			if(temp.getCodice() == cod);
			return temp;
		}
		return null;
	}
	
	
	
	

}
