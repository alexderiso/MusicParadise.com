package Bean;


import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un entità
 * 
 *
 */
public abstract class UtenteBean {
	/**
	 * Variabili d'instanza
	 */
	private String email;
	private String password;
	private String nickName;
	private String nome;
	private String cognome;

	

	/**
	 * Costruttore che inizializza le variabili d'instanza
	 * @param email email dell'utente
	 * @param password password per accedere al sistema
	 * @param nickName nome che identifica univocamente l'utente
	 * @param nome nome dell'utente
	 * @param cognome cognome dell'utente
	 */
	public UtenteBean(String email, String password, String nickName, String nome, String cognome) {
		super();
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.nome = nome;
		this.cognome = cognome;
	}

	/**
	 * Costruttore che inizializza le variabili email e password
	 */
	public UtenteBean() {
		email = "";
		password = "";
	}
	
	/**
	 * Restituisce il nickName di un utente
	 * @return nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Setta il nickName di un utente
	 * @param nickName identificativo utente nel sistema
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * Restituisce il nome di un utente
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setta il nome di un utente
	 * @param nome nome dell'utente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Restituisce il cognome di un utente
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Setta il cognome di un utente
	 * @param cognome cognome dell'utente
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Restituisce l'email di un utente
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	
	/**
	 * Setta l'email di un utente
	 * @param email email dell'utente
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Restituisce la password di un utente
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setta la password di un utente
	 * @param password password per accedere al sistema
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	
}