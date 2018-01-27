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
	 * @param indirizzo indirizzo dell'utente
	 * @param citt� citt� relativa alla spedizione
	 * @param cap cap relativo alla citt�
	 * @param nome nome relativo alla spedizione
	 * @param cognome cognome relativo alla spedizione
	 * @param codice codice che identifica univocamente l'indirizzo
	 * @param telefono numero di telefono relativo alla spedizione
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
	 * @param indirizzo nome via e numero civico dell'indirizzo
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
	 * @param citt� citt� relativa alla spedizione
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
	 * @param cap codice avviamento postale
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
	 * @param nome nome relativo alla spedizione
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Restiruisce il cognome del proprietario dell'indirizzo
	 * @return
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Setta il cognome del proprietario dell'indirizzo
	 * @param cognome cognome cognome relativo alla spedizione 
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
	 * @param codice codice che identifica univocamente l'indirizzo
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
	 * @param telefono numero di telefono della relativo alla spedizione dell'ordine
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	@Override
	public String toString() {
		return indirizzo + "," + citt� + "," + cap + "," + nome
				+ "," + cognome + "," + telefono;
	}
	
	
	
	
	
	

}



