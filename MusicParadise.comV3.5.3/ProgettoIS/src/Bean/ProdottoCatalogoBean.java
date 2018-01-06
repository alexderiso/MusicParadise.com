package Bean;

import java.sql.Date;
import java.util.ArrayList;

public class ProdottoCatalogoBean extends ProdottoBean {
	private int quantAgg;
	private Date data;
	private int numDisp;
	
	
	
	
	public ProdottoCatalogoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProdottoCatalogoBean(int codice, String nome, String colore, String marca, String descrizione, double prezzo,
			int peso, String strumento, ArrayList<String> foto,int quantAgg, Date data, int numDisp) {
		super(codice, nome, colore, marca, descrizione, prezzo, peso, strumento, foto);
		this.quantAgg = quantAgg;
		this.data = data;
		this.numDisp = numDisp;
		
	}
	public void addQuanAgg() {
		this.quantAgg = this.quantAgg + 1;
	}
	public void decrementQuanAgg(){
		this.quantAgg = quantAgg-1;
	}
	
	
	
	
	public int getQuantAgg() {
		return quantAgg;
	}
	public void setQuantAgg(int quantAgg) {
		this.quantAgg = quantAgg;
	}
	public int getNumDisp() {
		return numDisp;
	}



	public void setNumDisp(int numDisp) {
		this.numDisp = numDisp;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


}
