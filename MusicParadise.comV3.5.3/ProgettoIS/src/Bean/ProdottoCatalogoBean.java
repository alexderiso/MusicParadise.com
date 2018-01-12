package Bean;

import java.sql.Date;
import java.util.ArrayList;

public class ProdottoCatalogoBean extends ProdottoBean {
	
	/**
	 * Variabili d'instanza
	 */
	private int quantAgg;
	private Date data;
	private int numDisp;
	
	
	
	/**
	 * Costruttore vuoto
	 */
	public ProdottoCatalogoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Costruttore che inizializza le variabili
	 * @param codice codice che identifica univocamente il prodotto
	 * @param nome nome del prodotto
	 * @param colore colore del prododoto
	 * @param marca nome della marca del prodotto
	 * @param descrizione piccola descrizione del prodotto
	 * @param prezzo prezzo del prodotto
	 * @param peso peso relativo al prodotto
	 * @param strumento tipo di prodotto
	 * @param foto foto associate al prodotto
	 * @param quantAgg quantità del prodotto nel caarrello
	 * @param data data di inserimento del prodotto nel sistema
	 * @param numDisp quantità disponibile nel magazzino
	 */
	public ProdottoCatalogoBean(int codice, String nome, String colore, String marca, String descrizione, double prezzo,
			int peso, String strumento, ArrayList<String> foto,int quantAgg, Date data, int numDisp) {
		super(codice, nome, colore, marca, descrizione, prezzo, peso, strumento, foto);
		this.quantAgg = quantAgg;
		this.data = data;
		this.numDisp = numDisp;
		
	}
	
	/**
	 * incrementa la quantita di disponibilità di un prodotto di 1 unità 
	 */
	public void addQuanAgg() {
		this.quantAgg = this.quantAgg + 1;
	}
	
	/**
	 * Decrementa la quantita di disponibilità di un prodotto di 1 unità 
	 */
	
	public void decrementQuanAgg(){
		this.quantAgg = quantAgg-1;
	}
	
	
	
	/**
	 * Restituisce la quantità aggiornata in tempo reale di un prodotto
	 * @return quantAgg
	 */
	public int getQuantAgg() {
		return quantAgg;
	}
	
	/**
	 * Setta la quantità aggiornata in tempo reale di un prodotto
	 * @param quantAgg quantità del prodotto nel caarrello
	 */
	public void setQuantAgg(int quantAgg) {
		this.quantAgg = quantAgg;
	}
	
	/**
	 * Restituisce la quantità disponibile di un prodotto
	 * @return numDisp
	 */
	public int getNumDisp() {
		return numDisp;
	}



	/**
	 * Setta la quantità disponibile di un prodotto
	 * @param numDisp quantità disponibile nel magazzino
	 */
	public void setNumDisp(int numDisp) {
		this.numDisp = numDisp;
	}

	/**
	 * Restituisce la data di inserimento di un prodotto al catalogo
	 * @return data
	 */
	public Date getData() {
		return data;
	}


	/**
	 * Setta la data di inserimento di un prodotto nel catalogo
	 * @param data data di inserimento del prodotto nel sistema
	 */
	public void setData(Date data) {
		this.data = data;
	}


}
