package Bean;

import java.util.ArrayList;

public class ProdottoOrdineBean extends ProdottoBean {

	
	private int quantit�;
	
	
	
	public ProdottoOrdineBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProdottoOrdineBean(int codice, String nome, String colore, String marca, String descrizione, double prezzo,
			int peso, String strumento, ArrayList<String> foto, int quantit�) {
		super(codice, nome, colore, marca, descrizione, prezzo, peso, strumento, foto);
		this.quantit� = quantit�;
	}
	public int getQuantit�() {
		return quantit�;
	}
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}

}
