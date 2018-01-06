package Bean;

import java.util.ArrayList;
import java.util.List;

public class CarrelloBean {

	private ArrayList<ProdottoCatalogoBean> products;
	private double totale;
	
	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public CarrelloBean() {
		products = new ArrayList<ProdottoCatalogoBean>();
	}
	
	
	
	public void setProducts(ArrayList<ProdottoCatalogoBean> products) {
		this.products = products;
	}

	public void addProduct(ProdottoCatalogoBean product) {
		boolean flag = false;
		int count = 0;
		for(int i = 0; i < products.size(); i++){
			if(products.get(i).getCodice() == product.getCodice()){
				flag = true;
				count=i;
			}
		}
		if(flag){
				products.get(count).addQuanAgg();
				totale = totale + products.get(count).getPrezzo();
		}else{
			product.addQuanAgg();
			products.add(product);
			totale = totale + product.getPrezzo();
		}
	}
	
	
	
	public void aggiornaQuantità(int cod, int quantità){
		for(int i = 0; i < products.size(); i++){
			if(products.get(i).getCodice() == cod){
				if(quantità > products.get(i).getQuantAgg()){
					totale = totale + products.get(i).getPrezzo();
				}else if(quantità < products.get(i).getQuantAgg()){
					totale = totale - products.get(i).getPrezzo();
				}
				products.get(i).setQuantAgg(quantità);
				System.out.println("quantità"+quantità +" "+"quantPrd"+products.get(i).getQuantAgg());
			}
		}
	}
	
	public void deleteProduct(ProdottoBean product) {
		int tot= 0;
		for(ProdottoBean prod : products) {
			if(prod.getCodice() == product.getCodice()) {
					products.remove(prod);
					break;
			}
		}
		for(ProdottoBean prod : products) {
			tot += prod.getPrezzo();
		}
		setTotale(tot);
 	}
	
	public ArrayList<ProdottoCatalogoBean> getProducts() {
		return  products;
	}
	
	public int countingPrds() {
		int i;
		int c = 0;
		for(i=0; i < products.size(); i++) {
			c = c + products.get(i).getQuantAgg();
		}
		return c;
	}
	
	
	public int getQuantitàByCodice(int codice){
		for(int i = 0; i< products.size(); i++){
			ProdottoCatalogoBean prodotto = products.get(i);
			if(prodotto.getCodice() == codice){
				return prodotto.getQuantAgg();
			}
		}
		return 0;
	}

}
