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
	 * @param codice
	 * @param nome
	 * @param colore
	 * @param marca
	 * @param descrizione
	 * @param prezzo
	 * @param peso
	 * @param strumento
	 * @param foto
	 * @param quantit�
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
	 * @param quantit�
	 */
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}

}
