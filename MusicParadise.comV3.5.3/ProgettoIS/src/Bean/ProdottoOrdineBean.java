package Bean;

import java.util.ArrayList;

public class ProdottoOrdineBean extends ProdottoBean {

	/**
	 * Variabli d'instanza
	 */
	private int quantit�;
	
	
	/**
	 * Costruttore vuoto
	 */
	public ProdottoOrdineBean() {
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
	 * @param quantit� quantit� del prodotto nell'ordine
	 */
	public ProdottoOrdineBean(int codice, String nome, String colore, String marca, String descrizione, double prezzo,
			int peso, String strumento, ArrayList<String> foto, int quantit�) {
		super(codice, nome, colore, marca, descrizione, prezzo, peso, strumento, foto);
		this.quantit� = quantit�;
	}
	
	/**
	 * Restituisce la quantit� di un prodotto nell'ordine
	 * @return quantit�	
	 */
	public int getQuantit�() {
		return quantit�;
	}
	
	/**
	 * Setta la quantit� di un prodotto nell'ordine
	 * @param quantit� quantit� del prodotto nell'ordine
	 */
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}

}
