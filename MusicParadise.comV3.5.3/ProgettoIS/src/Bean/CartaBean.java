package Bean;


	public class CartaBean{

		
		/**
		 * Variabili d'instanza
		 */
		private String scadenza;
		private String numCarta;
		private String nomeProprietario;
		private int codice;
		public CartaBean() {
			
		}
		
		/**
		 * Costruttore carta
		 * @param scadenza scadenza della carta di credito
		 * @param numCarta numero relativo alla carta
		 * @param nomeProprietario nome dell'intestatario della carta
		 * @param codice codice identificativo della carta
		 */

		public CartaBean(String scadenza, String numCarta, String nomeProprietario, int codice) {
		
			this.scadenza = scadenza;
			this.numCarta = numCarta;
			this.nomeProprietario = nomeProprietario;
			this.codice = codice;
		}



		
		/**
		 * Ritorna la scadenza della carta di credito
		 * @return scadenza
		 */
		public String getScadenza() {
			return scadenza;
		}
		
		/**
		 * setta la scadenza della carta di credito
		 * @param scadenza scadenza della carta di credito
		 */
		public void setScadenza(String scadenza) {
			this.scadenza = scadenza;
		}
		
		/**
		 * Ritorna il numero della carta di credito
		 * @return numCarta
		 */
		public String getNumCarta() {
			return numCarta;
		}
		
		/**
		 * Setta il numero della carta di credito
		 * @param numCarta numero della carta di credito
		 */
		public void setNumCarta(String numCarta) {
			this.numCarta = numCarta;
		}
		/**
		 * Ritorna il nome del proprietario della carta di credito
		 * @return nomeProprietario
		 */
		public String getNomeProprietario() {
			return nomeProprietario;
		}
		
		/**
		 * Setta il nome del proprietario della carta di credito
		 * @param nomeProprietario nome del proprietario della carta
		 */
		public void setNomeProprietario(String nomeProprietario) {
			this.nomeProprietario = nomeProprietario;
		}
		
		/**
		 * Restituisce il codice della carta di credito
		 * @return codice
		 */
		public int getCodice() {
			return codice;
		}
		
		/**
		 * Setta il codice della carta di credito
		 * @param codice codice che identifica la carta
		 */
		public void setCodice(int codice) {
			this.codice = codice;
		}
		
		
		@Override
		public String toString() {
			return codice +"," + scadenza + "," + numCarta + ","
					+ nomeProprietario ;
		}
		
		
		
		
		
	}
