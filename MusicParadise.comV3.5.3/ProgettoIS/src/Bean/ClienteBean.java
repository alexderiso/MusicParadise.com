package Bean;

import java.util.ArrayList;


/**
 * Rappresenta un entit�
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
	 * @param email : email del cliente
	 * @param password : password scelta dal cliente per accedere al sistema
	 * @param nickName : riconosce univocamente un cliente nel sistema
	 * @param nome : nome del cliente
	 * @param cognome : cognome del cliente 
	 * @param cart : carrello del utente salvato nella sessione
	 */
	public ClienteBean(String email, String password, String nickName, String nome, String cognome, CarrelloBean cart) {
		super(email, password, nickName, nome, cognome);
		carte = null;
		indirizzi = null;
		this.cart = cart;
		
	}
	/**
	 * Ritorna gli indirizzi di un cliente
	 * @return indirizzi : indirizzi che il cliente ha salvato per il suo account
	 * post: indirizzi!=null
	 */
	public ArrayList<IndirizzoBean> getIndirizzi() {
		return indirizzi;
	}
	
	/**
	 * Setta gli indirizzi di un cliente
	 * @param indirizzi : indirizzi che il cliente ha salvato per il suo account
	 * pre: indirizzi !=null
	 */
	public void setIndirizzi(ArrayList<IndirizzoBean> indirizzi) {
		this.indirizzi = indirizzi;
	}
	
	/**
	 * Ritorna le carte di credito di un cliente
	 * @return carte : carte di credito che il cliente ha salvato per il suo account
	 * post: carte!=null
	 */

	public ArrayList<CartaBean> getCarte() {
		return carte;
	}
	
	/**
	 * Setta le carte di credito di un clietne
	 * @param carte2 : carte di credito che il cliente ha salvato per il suo account
	 * pre: carte2!=null
	 */

	public void setCarte(ArrayList<CartaBean> carte2) {
		this.carte = carte2;
	}
	
	/**
	 * Aggiunge una carta al cliente
	 * @param carta : carte di credito che il cliente ha salvato per il suo account
	 * pre: carta!=null
	 */
	
	public void addCarta(CartaBean carta) {
		carte.add(carta);
	}
	
	/**
	 * Aggiunge un indirizzo al cliente 
	 * @param indirizzo : indirizzi che il cliente ha salvato per il suo account
	 * pre: indirizzo!=null
	 */
	public void addIndirizzo(IndirizzoBean indirizzo) {
		indirizzi.add(indirizzo);
	}
	
	
	/**
	 * Cerca una carta di un cliente tramite il codice
	 * @param cod : codice della carta di credito
	 * @return cod, se la carta � stata trovata
	 * @return null, se la carta non � stata trovata
	 * pre: cod maggiore di 0
	 */
	public CartaBean trovaCarta(int cod) {
		System.out.println("Codice carta nella classe cliente: "+ cod);
		for(CartaBean temp : carte) {
			if(temp.getCodice() == cod) {
			return temp;
		}
		}
		return null;
	}
	
	/**
	 * Cerca un indirizzo di un cliente tramite il codice
	 * @param cod : codice dell'indirizzo
	 * @return cod, se l'indirizzo � stato trovata
	 * @return null, se l'indirizzo non � stato trovata
	 * pre: cod maggiore di 0
	 */
	public IndirizzoBean trovaIndirizzo(int cod) {
		System.out.println("Codice indirizzo nella classe cliente: " +cod+" grandezza "+indirizzi.size());
		for(IndirizzoBean temp : indirizzi) {
			if(temp.getCodice() == cod) {
			System.out.println("INDIRIZZO: " +temp.getCodice() +" "+cod);
			return temp;
		}
		}
		return null;
	}
	
	
	
	}
