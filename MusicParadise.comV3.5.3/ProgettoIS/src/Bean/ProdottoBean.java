package Bean;
import java.sql.Date;
import java.util.ArrayList;


/**
 * Rappresenta un entità
 * @author Alessandro
 *
 */
public abstract class ProdottoBean{
	
	// Variabili d'instanza
	int codice;
	String nome;
	String colore;
	String marca;
	String descrizione;
	double prezzo;
	int peso;
	String strumento;
	ArrayList<String> foto = new ArrayList<String>();
	
	
	
/**
 * il costruttore setta le variabili d'instanza
 * @param codice
 * @param nome
 * @param colore
 * @param marca
 * @param descrizione
 * @param prezzo
 * @param peso
 * @param strumento
 * @param foto
 */
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
	
	/**
     * Setta l'identificativo di un prodotto
     * @param cod_pezzo
     * pre: cod_pezzo!=null
     */

	public void setCodice(int cod){
		codice = cod;
	}
	/**
     * Setta il nome di un prodotto
     * @param nome
     * pre: nome!=null
     */

	public void setNome(String nome){
		this.nome = nome;
	}
	/**
     * Setta il colore di un prodotto
     * @param colore
     * pre: colore!=null
     */

	public void setColore(String colore){
		this.colore = colore;
	}
	
	/**
     * Setta la marca di un prodotto
     * @param marca
     * pre: marca!=null
     */

	public void setMarca(String marca){
		this.marca = marca;
	}
	
	/**
     * Setta la descrizione di un prodotto
     * @param marca
     * pre: marca!=null
     */

	public void setDescrizione(String descrizione){
		this.descrizione = descrizione;
	}
	
	/**
     * Setta il peso di un prodotto
     * @param peso
     * pre: peso>0
     */

	public void setPeso(int peso){
		this.peso = peso;
	}
	
	/**
     * Setta il prezzo di un prodotto
     * @param prezzo
     * pre: prezzo>0
     */

	public void setPrezzo(double prezzo){
		this.prezzo = prezzo;
	}
	
	/**
     * Setta che tipo di strumento è il prodotto
     * @param strumento
     * pre: strumento!=null
     */

	public void setStrumento(String strumento){
		this.strumento = strumento;
	}
	


    /**
     * ritorna il nome di un prodotto
     * @return nome
     * post: nome!=null
     */

	public String getNome(){
		return nome;
	}
	


    /**
     * ritorna il colore di un prodotto
     * @return colore
     * post: colore!=null
     */

	public String getColore(){
		return colore;
	}


    /**
     * ritorna la marca di un prodotto
     * @return marca
     * post: marca!=null
     */

	public String getMarca(){
		return marca;
	}
	


    /**
     * Ritorno la descrizione del prodotto
     * @return descrizione
     * post: descrizione!=null
     */

	public String getDescrizione(){
		return descrizione;
	}
	

    /**
     * ritorna l'identificativo di un prodotto
     * @return cod_pezzo
     * post: cod_pezzo!=null
     */

	public int getCodice(){
		return codice;
	}
	
	/**
     * Ritorno il prezzo del prodotto
     * @return prezzo
     * post: prezzo maggiore di 0
     */


	public double getPrezzo(){
		return prezzo;
	}
	

    /**
     * Ritorno il peso del prodotto
     * @return peso
     * post:peso maggiore di 0
     */

	public int getPeso(){
		return peso;
	}
	


    /**
     * Ritorno il tipo di strumento del prodotto
     * @return strumento
     * post: strumento!=null
     */

	public String getStrumento(){
		return strumento;
	}
	


    /**
     * Ritorno le foto del prodotto del prodotto
     * @return foto
     * post: descrizione!=null
     */

	public ArrayList<String> getFoto() {
		return foto;
	}
	


    /**
     * Setto le foto del prodotto
     *@param foto 
     * pre: foto!=null
     */

	public void aggiungiFoto(String foto) {
		this.foto.add(foto);
	}

}
