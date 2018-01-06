package Bean;


public class IndirizzoBean {
	
	/**
	 * 
	 */
	private String indirizzo;
	private String città;
	private int cap;
	private String nome;
	private String cognome;
	private int codice;
	private String telefono;
	
	
	
	public IndirizzoBean(String indirizzo, String città, int cap, String nome, String cognome, int codice,
			String telefono) {
		super();
		this.indirizzo = indirizzo;
		this.città = città;
		this.cap = cap;
		this.nome = nome;
		this.cognome = cognome;
		this.codice = codice;
		this.telefono = telefono;
	}

	public IndirizzoBean() {
		
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return codice + "," +indirizzo + "," + città + "," + cap + "," + nome
				+ "," + cognome + "," + telefono;
	}
	
	
	
	
	
	

}



