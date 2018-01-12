package Control.gestioneProfilo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CartaBean;
import Bean.ClienteBean;
import Bean.IndirizzoBean;
import Bean.UtenteBean;
import Model.CartaModel;

/**
 * Servlet implementation class AggiungiCartaControl
 */

/**
 * Classe che implementa la servlet per aggiungere un indirizzo ad un account
 *
 */
@WebServlet("/AggiungiCartaControl")
public class AggiungiCartaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * Chiama il costruttore della superclasse
	 */
    public AggiungiCartaControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    CartaModel cartaModel = new CartaModel();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     * Vuoto
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * Effettua una richiesta HTTP POST per aggiungere un indirizzo ad un account cliente
	 * @param request
     * @param respose
     * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String numCarta = request.getParameter("numCarta");
		String mese = request.getParameter("mese");
		String anno = request.getParameter("anno");
		String scadenza = mese+"/"+anno;
		String nomProprietario = request.getParameter("nomProprietario");
		CartaBean carta= new CartaBean();
		carta.setNumCarta(numCarta);
		carta.setScadenza(scadenza);
		carta.setNomeProprietario(nomProprietario);
		ClienteBean utente = (ClienteBean)request.getSession().getAttribute("utente");
		utente.addCarta(carta);
		try {
			carta.setCodice(cartaModel.generaCodice());
			cartaModel.doSave(carta,utente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean checkout = (Boolean) request.getSession().getAttribute("checkout");
		if(checkout != null) {
			request.getSession().setAttribute("utente", utente);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		}else {
			request.getSession().setAttribute("utente", utente);
			
			response.sendRedirect(request.getContextPath() + "/profiloCliente.jsp");
		}
		
	}

}
