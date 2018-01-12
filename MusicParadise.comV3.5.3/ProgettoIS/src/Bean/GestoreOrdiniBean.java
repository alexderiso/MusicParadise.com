package Bean;

/**
 * Rappresenta un entità
 */
public class GestoreOrdiniBean extends UtenteBean {
	
	/**
	 * Variabili d'instanza
	 */
	private String matricola;
	
	/**
	* Costruttore vuoto
	*/
	public GestoreOrdiniBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Costruttorre che inizializza le variabili d'instanza
	 * @param email : email del gestore
	 * @param password : password usata dal gestore per accedere al sistema
	 * @param nickName : nickName che riconosce univocamente il gestore all'interno del sistema
	 * @param nome : nome del gestore
	 * @param cognome : cognome del gestore
	 * @param matricola : matricola del gestore
	 */
	public GestoreOrdiniBean(String email, String password, String nickName, String nome, String cognome, String matricola) {
		super(email, password, nickName, nome, cognome);
		this.matricola = matricola;
	}

	/**
	 * Restituisce la marticola del gestore
	 * @return matricola : matricola del gestore
	 */
	public String getMatricola() {
		return matricola;
	}

	/**
	 * Setta la matricola del gestore
	 * @param matricola : matricola del gestore
	 */
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	
	
	

}
