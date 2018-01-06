package Bean;

import java.util.ArrayList;

public class ProdottoOrdineBean extends ProdottoBean {

	
	private int quantità;
	
	
	
	public ProdottoOrdineBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProdottoOrdineBean(int codice, String nome, String colore, String marca, String descrizione, double prezzo,
			int peso, String strumento, ArrayList<String> foto, int quantità) {
		super(codice, nome, colore, marca, descrizione, prezzo, peso, strumento, foto);
		this.quantità = quantità;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

}
