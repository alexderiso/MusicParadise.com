package Bean;
import java.sql.Date;
import java.util.ArrayList;

public abstract class ProdottoBean{
	int codice;
	String nome;
	String colore;
	String marca;
	String descrizione;
	double prezzo;
	int peso;
	String strumento;
	ArrayList<String> foto = new ArrayList<String>();
	
	
	

	public ProdottoBean(int codice, String nome, String colore, String marca, String descrizione, double prezzo,
			int peso, String strumento, ArrayList<String> foto) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.colore = colore;
		this.marca = marca;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.peso = peso;
		this.strumento = strumento;
		this.foto = foto;
	}


	public ProdottoBean(){

	}
	

	public void setCodice(int cod){
		codice = cod;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	public void setColore(String colore){
		this.colore = colore;
	}
	public void setMarca(String marca){
		this.marca = marca;
	}
	public void setDescrizione(String descrizione){
		this.descrizione = descrizione;
	}
	public void setPeso(int peso){
		this.peso = peso;
	}
	public void setPrezzo(double prezzo){
		this.prezzo = prezzo;
	}
	public void setStrumento(String strumento){
		this.strumento = strumento;
	}

	public String getNome(){
		return nome;
	}
	public String getColore(){
		return colore;
	}
	public String getMarca(){
		return marca;
	}
	public String getDescrizione(){
		return descrizione;
	}
	public int getCodice(){
		return codice;
	}

	public double getPrezzo(){
		return prezzo;
	}
	public int getPeso(){
		return peso;
	}
	public String getStrumento(){
		return strumento;
	}
	public ArrayList<String> getFoto() {
		return foto;
	}
	public void aggiungiFoto(String foto) {
		this.foto.add(foto);
	}

}
