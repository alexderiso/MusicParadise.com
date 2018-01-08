package Bean;


public class IndirizzoBean {
	
	/**
	 * Variabili d'instanza
	 */
	private String indirizzo;
	private String citt�;
	private int cap;
	private String nome;
	private String cognome;
	private int codice;
	private String telefono;
	
	
	/**
	 * Costruttore che inizializza le variabili d'instanza
	 * @param indirizzo
	 * @param citt�
	 * @param cap
	 * @param nome
	 * @param cognome
	 * @param codice
	 * @param telefono
	 */
	public IndirizzoBean(String indirizzo, String citt�, int cap, String nome, String cognome, int codice,
			String telefono) {
		super();
		this.indirizzo = indirizzo;
		this.citt� = citt�;
		this.cap = cap;
		this.nome = nome;
		this.cognome = cognome;
		this.codice = codice;
		this.telefono = telefono;
	}

	/**
	 * Costruttore vuoto
	 */
	public IndirizzoBean() {
		
	}

	/**
	 * Ritorna la via e il numero civico dell'indirizzo
	 * @return indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	/**
	 * Setta la via e il numero civico dell'indirizzo
	 * @param indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	/**
	 * Restituisce  la citt� dell'indirizzo
	 * @return citt�
	 */
	public String getCitt�() {
		return citt�;
	}
	
	/**
	 * Setta la citt� dell'indirizzo
	 * @param citt�
	 */
	public void setCitt�(String citt�) {
		this.citt� = citt�;
	}

	/**
	 * Restituisce il cap dell'indirizzo
	 * @return cap
	 */
	public int getCap() {
		return cap;
	}

	/**
	 * Setta il cap dell'indirizzo
	 * @param cap
	 */
	public void setCap(int cap) {
		this.cap = cap;
	}
	
	
	/**
	 * Restituisce il nome del proprietario dell'indirizzo
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Setta il nome del proprietario dell'indirizzo
	 * @param nome
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Restiruisce il cognome del proprietario dell'indirizzo
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Setta il cognome del proprietario dell'indirizzo
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Restituisce il codice dell'indirizzo
	 * @return codice
	 */

	public int getCodice() {
		return codice;
	}

	/**
	 * Setta il codice dell'indirizzo
	 * @param codice
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}

	/**
	 * Restituisce il numero di telefono dell'indirizzo
	 * @return
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Setta il numero di telefono dell'indirizzo
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	@Override
	public String toString() {
		return codice + "," +indirizzo + "," + citt� + "," + cap + "," + nome
				+ "," + cognome + "," + telefono;
	}
	
	
	
	
	
	

}



