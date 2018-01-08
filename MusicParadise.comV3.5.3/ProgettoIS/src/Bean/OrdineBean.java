package Bean; 



import java.util.ArrayList;
import java.util.Date;



	public class OrdineBean {
	
		/**
		 * Variabili d'instanza
		 */
		private Date data;
		private IndirizzoBean indirizzo;
		private CartaBean carta;
		private String stato;
		private String corriere;
		private String tracking;
		private double totale;
		private int numOrdine;
		private String user;
		private ArrayList<ProdottoOrdineBean> prodotti;
		private Date dataConsegna;
		
		
		/**
		 * Costruttore che inizializza le variabili d'instanza
		 * @param data
		 * @param indirizzo
		 * @param carta
		 * @param stato
		 * @param corriere
		 * @param tracking
		 * @param totale
		 * @param numOrdine
		 * @param user
		 * @param prodotti
		 * @param dataConsegna
		 */
		public OrdineBean(Date data, IndirizzoBean indirizzo, CartaBean carta, String stato, String corriere,
				String tracking, double totale, int numOrdine, String user, ArrayList<ProdottoOrdineBean> prodotti, Date dataConsegna) {
			super();
			this.data = data;
			this.indirizzo = indirizzo;
			this.carta = carta;
			this.stato = stato;
			this.corriere = corriere;
			this.tracking = tracking;
			this.totale = totale;
			this.numOrdine = numOrdine;
			this.user = user;
			this.prodotti = prodotti;
			this.dataConsegna = dataConsegna;
		}
		
		/**
		 * Costruttore vuoto
		 */
		public OrdineBean() {
			
		}
		
		/**
		 * Restituisce la data dell'ordine
		 * @return data
		 */
		public Date getData() {
			return data;
		}
		
		/**
		 * Setta la data dell'ordine
		 * @param data
		 */
		public void setData(Date data) {
			this.data = data;
		}
		
		/**
		 * Restituisce l'indirizzo di spedizione dell'ordine
		 * @return indirizzo
		 */
		public IndirizzoBean getIndirizzo() {
			return indirizzo;
		}
		
		/**
		 * Setta l'indirizzo di spedizioned dell'ordine
		 * @param indirizzo
		 */
		public void setIndirizzo(IndirizzoBean indirizzo) {
			this.indirizzo = indirizzo;
		}
		
		/**
		 * Restituisce la carta di credito che ha effettuato l'ordine
		 * @return carta
		 */
		public CartaBean getCarta() {
			return carta;
		}
		
		/**
		 * Setta la carta di credito per l'ordine
		 * @param carta
		 */
		public void setCarta(CartaBean carta) {
			this.carta = carta;
		}
		
		/**
		 * Restituisce lo stato dell'ordine
		 * @return stato
		 */
		public String getStato() {
			return stato;
		}
		
		/**
		 * Setta lo stato dell'ordine
		 * @param stato
		 */
		public void setStato(String stato) {
			this.stato = stato;
		}
		
		/**
		 * Restituisce il corriere che effettuerà la spedizione dell'ordine
		 * @return corriere
		 */
		public String getCorriere() {
			return corriere;
		}
		
		/**
		 * Setta il corriere che effettuerà la spedizione dell'ordine
		 * @param corriere
		 */
		public void setCorriere(String corriere) {
			this.corriere = corriere;
		}
		
		/**
		 * Restituisce il codice di tracking dell'ordine
		 * @return tracking
		 */
		public String getTracking() {
			return tracking;
		}
		
		/**
		 * Setta il codice di tracking dell'ordine
		 * @param tracking
		 */
		public void setTracking(String tracking) {
			this.tracking = tracking;
		}
		
		/**
		 * Restituisce il costo totale dell'ordine
		 * @return totale
		 */
		public double getTotale() {
			return totale;
		}
		
		/**
		 * Setta il costo totale dell'ordine
		 * @param totale
		 */
		public void setTotale(double totale) {
			this.totale = totale;
		}
		
		/**
		 * Restituisce il numero dell'ordine
		 * @return numOrdine
		 */
		public int getNumOrdine() {
			return numOrdine;
		}
		
		/**
		 * Setta il numero dell'ordine
		 * @param numOrdine
		 */
		public void setNumOrdine(int numOrdine) {
			this.numOrdine = numOrdine;
		}
		
		/**
		 * Restituise l'user che ha effettuato l'ordine
		 * @return user
		 */
		public String getUser() {
			return user;
		}
		
		/**
		 * Setta l'user che ha effettuato l'ordine
		 * @param user
		 */
		public void setUser(String user) {
			this.user = user;
		}
		
		/**
		 * Restituisce la lista di prodotto contenuti nell'ordine
		 * @return prodotti
		 */
		public ArrayList<ProdottoOrdineBean> getProdotti() {
			return prodotti;
		}
		
		/**
		 * Setta la lista di prodotto contenuti nell'ordine
		 * @param prodotti
		 */
		public void setProdotti(ArrayList<ProdottoOrdineBean> prodotti) {
			this.prodotti = prodotti;
		}

		/**
		 * Restituisce la data di consegna dell'ordine
		 * @return dataConsegna
		 */
		public Date getDataConsegna() {
			return dataConsegna;
		}

		/**
		 * Setta la data di consegna dell'ordine
		 * @param dataConsegna
		 */
		public void setDataConsegna(Date dataConsegna) {
			this.dataConsegna = dataConsegna;
		}
		
		
		
		
		
	}
	