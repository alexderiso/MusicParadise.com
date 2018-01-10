package Bean;

import java.util.ArrayList;


/**
 * Rappresenta un entità
 * @author Alessandro
 *
 */
public class ClienteBean extends UtenteBean{
	
	/**
	 * Variabili d'instanza
	 */
	private ArrayList<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
	private ArrayList<CartaBean> carte = new ArrayList<CartaBean>();
	private CarrelloBean cart;
	
	/**
	 * Costruttore vuoto
	 */
	public ClienteBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Costruttore che inizializza le variabili d'instanza
	 * @param email
	 * @param password
	 * @param nickName
	 * @param nome
	 * @param cognome
	 * @param cart
	 */
	public ClienteBean(String email, String password, String nickName, String nome, String cognome, CarrelloBean cart) {
		super(email, password, nickName, nome, cognome);
		carte = null;
		indirizzi = null;
		this.cart = cart;
		
	}
	/**
	 * Ritorna gli indirizzi di un cliente
	 * @return indirizzi
	 * post: indirizzi!=null
	 */
	public ArrayList<IndirizzoBean> getIndirizzi() {
		return indirizzi;
	}
	
	/**
	 * Setta gli indirizzi di un cliente
	 * @param indirizzi
	 * pre: indirizzi !=null
	 */
	public void setIndirizzi(ArrayList<IndirizzoBean> indirizzi) {
		this.indirizzi = indirizzi;
	}
	
	/**
	 * Ritorna le carte di credito di un cliente
	 * @return carte
	 * post: carte!=null
	 */

	public ArrayList<CartaBean> getCarte() {
		return carte;
	}
	
	/**
	 * Setta le carte di credito di un clietne
	 * @param carte2
	 * pre: carte2!=null
	 */

	public void setCarte(ArrayList<CartaBean> carte2) {
		this.carte = carte2;
	}
	
	/**
	 * Aggiunge una carta al cliente
	 * @param carta
	 * pre: carta!=null
	 */
	
	public void addCarta(CartaBean carta) {
		carte.add(carta);
	}
	
	/**
	 * Aggiunge un indirizzo al cliente 
	 * @param indirizzo
	 * pre: indirizzo!=null
	 */
	public void addIndirizzo(IndirizzoBean indirizzo) {
		indirizzi.add(indirizzo);
	}
	
	
	/**
	 * Cerca una carta di un cliente tramite il codice
	 * @param cod
	 * @return cod, se la carta è stata trovata
	 * @return null, se la carta non è stata trovata
	 * pre: cod maggiore di 0
	 */
	public CartaBean trovaCarta(int cod) {
		for(CartaBean temp : carte) {
			if(temp.getCodice() == cod);
			return temp;
		}
		return null;
	}
	
	/**
	 * Cerca un indirizzo di un cliente tramite il codice
	 * @param cod
	 * @return cod, se l'indirizzo è stato trovata
	 * @return null, se l'indirizzo non è stato trovata
	 * pre: cod maggiore di 0
	 */
	public IndirizzoBean trovaIndirizzo(int cod) {
		for(IndirizzoBean temp : indirizzi) {
			if(temp.getCodice() == cod);
			return temp;
		}
		return null;
	}
	
	
	
	

}
