package Bean; 



import java.util.ArrayList;
import java.util.Date;



	public class OrdineBean {
	
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
		
		public OrdineBean() {
			
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
		public IndirizzoBean getIndirizzo() {
			return indirizzo;
		}
		public void setIndirizzo(IndirizzoBean indirizzo) {
			this.indirizzo = indirizzo;
		}
		public CartaBean getCarta() {
			return carta;
		}
		public void setCarta(CartaBean carta) {
			this.carta = carta;
		}
		public String getStato() {
			return stato;
		}
		public void setStato(String stato) {
			this.stato = stato;
		}
		public String getCorriere() {
			return corriere;
		}
		public void setCorriere(String corriere) {
			this.corriere = corriere;
		}
		public String getTracking() {
			return tracking;
		}
		public void setTracking(String tracking) {
			this.tracking = tracking;
		}
		public double getTotale() {
			return totale;
		}
		public void setTotale(double totale) {
			this.totale = totale;
		}
		public int getNumOrdine() {
			return numOrdine;
		}
		public void setNumOrdine(int numOrdine) {
			this.numOrdine = numOrdine;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public ArrayList<ProdottoOrdineBean> getProdotti() {
			return prodotti;
		}
		public void setProdotti(ArrayList<ProdottoOrdineBean> prodotti) {
			this.prodotti = prodotti;
		}

		public Date getDataConsegna() {
			return dataConsegna;
		}

		public void setDataConsegna(Date dataConsegna) {
			this.dataConsegna = dataConsegna;
		}
		
		
		
		
		
	}
	