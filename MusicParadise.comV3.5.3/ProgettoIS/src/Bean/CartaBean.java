package Bean;


	public class CartaBean{

		
		
		public CartaBean() {
			
		}

		public CartaBean(String scadenza, String numCarta, String nomeProprietario, int codice) {
		
			this.scadenza = scadenza;
			this.numCarta = numCarta;
			this.nomeProprietario = nomeProprietario;
			this.codice = codice;
		}


		/**
		 * Variabili d'instanza
		 */
		String scadenza;
		String numCarta;
		String nomeProprietario;
		int codice;

		
		/**
		 * Ritorna la scadenza della carta di credito
		 * @return scadenza
		 */
		public String getScadenza() {
			return scadenza;
		}
		
		/**
		 * setta la scadenza della carta di credito
		 * @param scadenza
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
		 * @param numCarta
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
		 * @param nomeProprietario
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
		 * @param codice
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
