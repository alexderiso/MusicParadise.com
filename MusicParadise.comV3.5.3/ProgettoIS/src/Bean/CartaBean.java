package Bean;


	public class CartaBean{

		String scadenza;
		String numCarta;
		String nomeProprietario;
		int codice;

		
		
		public String getScadenza() {
			return scadenza;
		}
		public void setScadenza(String scadenza) {
			this.scadenza = scadenza;
		}
		public String getNumCarta() {
			return numCarta;
		}
		public void setNumCarta(String numCarta) {
			this.numCarta = numCarta;
		}
		public String getNomeProprietario() {
			return nomeProprietario;
		}
		public void setNomeProprietario(String nomeProprietario) {
			this.nomeProprietario = nomeProprietario;
		}
		public int getCodice() {
			return codice;
		}
		public void setCodice(int codice) {
			this.codice = codice;
		}
		@Override
		public String toString() {
			return codice +"," + scadenza + "," + numCarta + ","
					+ nomeProprietario ;
		}
		
		
		
		
		
	}
