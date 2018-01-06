package Bean;

public class GestoreOrdiniBean extends UtenteBean {
	
	/**
	 * 
	 */
	private String matricola;

	public GestoreOrdiniBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GestoreOrdiniBean(String email, String password, String nickName, String nome, String cognome, String matricola) {
		super(email, password, nickName, nome, cognome);
		this.matricola = matricola;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	
	
	

}
