package Bean;


import java.util.ArrayList;
import java.util.List;

public abstract class UtenteBean {

	private String email;
	private String password;
	private String nickName;
	private String nome;
	private String cognome;

	

	public UtenteBean(String email, String password, String nickName, String nome, String cognome) {
		super();
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.nome = nome;
		this.cognome = cognome;
	}

	public UtenteBean() {
		email = "";
		password = "";
	}
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
}