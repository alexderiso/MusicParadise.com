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
	 * @param email
	 * @param password
	 * @param nickName
	 * @param nome
	 * @param cognome
	 * @param matricola
	 */
	public GestoreOrdiniBean(String email, String password, String nickName, String nome, String cognome, String matricola) {
		super(email, password, nickName, nome, cognome);
		this.matricola = matricola;
	}

	/**
	 * Restituisce la marticola del gestore
	 * @return matricola
	 */
	public String getMatricola() {
		return matricola;
	}

	/**
	 * Setta la matricola del gestore
	 * @param matricola
	 */
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	
	
	

}
