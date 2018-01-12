package Bean;

import java.util.ArrayList;
import java.util.List;

public class CarrelloBean {
	
	/**
	 * Costruttore carrello
	 */
	public CarrelloBean() {
		products = new ArrayList<ProdottoCatalogoBean>();
	}
	

	/**
	 * Variabili d'instanza
	 */
	private ArrayList<ProdottoCatalogoBean> products;
	private double totale;
	
	/**
	 * Ritorna il totale del carrello
	 * @return totale : valore totale del carrello
	 */
	public double getTotale() {
		return totale;
	}
	
	
	/**
	 * Setta il totale del carrello
	 * @param totale valore totale del carrello
	 */
	public void setTotale(double totale) {
		this.totale = totale;
	}
	

	
	/**
	 * Setta i prodotti del carrello
	 * @param products : lista dei prodotti presenti nel carrello
	 */
	public void setProducts(ArrayList<ProdottoCatalogoBean> products) {
		this.products = products;
	}
	
	/**
	 * Aggiungi un prodotto al carrello
	 * @param product : lista dei prodotti presenti nel carrello
	 */
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
	
	
	/**
	 * Aggiorna la quantità di un prodotto
	 * @param cod codice del prodotto
	 * @param quantità quantità da inserire 
	 */
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
	
	
	/**
	 * Elimina il prodotto dal carrello
	 * @param prodotto prodotto da eliminare
	 */
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
	
	/**
	 * Ritorna i prodotti del carrello
	 * @return prodotti del carrello
	 */
	public ArrayList<ProdottoCatalogoBean> getProducts() {
		return  products;
	}
	
	/**
	 * Ritorna il numero totale del carrello
	 * @return c numero dei prodotti del carrello
	 */
	public int countingPrds() {
		int i;
		int c = 0;
		for(i=0; i < products.size(); i++) {
			c = c + products.get(i).getQuantAgg();
		}
		return c;
	}
	
	/**
	 * Ritorna la quantità di un prodotto
	 * @param codice codice del prodotto
	 * @return quantità quantità del prodotto passato come parametro
	 */
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
