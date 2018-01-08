package Bean;

import java.util.ArrayList;

public class ProdottoOrdineBean extends ProdottoBean {

	/**
	 * Variabli d'instanza
	 */
	private int quantità;
	
	
	/**
	 * Costruttore vuoto
	 */
	public ProdottoOrdineBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Costruttore che inizializza le variabili
	 * @param codice
	 * @param nome
	 * @param colore
	 * @param marca
	 * @param descrizione
	 * @param prezzo
	 * @param peso
	 * @param strumento
	 * @param foto
	 * @param quantità
	 */
	public ProdottoOrdineBean(int codice, String nome, String colore, String marca, String descrizione, double prezzo,
			int peso, String strumento, ArrayList<String> foto, int quantità) {
		super(codice, nome, colore, marca, descrizione, prezzo, peso, strumento, foto);
		this.quantità = quantità;
	}
	
	/**
	 * Restituisce la quantità di un prodotto nell'ordine
	 * @return quantità	
	 */
	public int getQuantità() {
		return quantità;
	}
	
	/**
	 * Setta la quantità di un prodotto nell'ordine
	 * @param quantità
	 */
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

}
