package Control.checkout;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CarrelloBean;
import Bean.CartaBean;
import Bean.ClienteBean;
import Bean.IndirizzoBean;
import Bean.OrdineBean;
import Bean.UtenteBean;
import Model.CarrelloModel;
import Model.ComposizioneModel;
import Model.OrdineModel;
import Model.ProdottoModel;
import Model.ProdottoOrdineModel;

/**
 * Servlet implementation class ConfermaOrdineControl
 */

/**
 * Servlet per confermare un ordine
 *
 */
@WebServlet("/ConfermaOrdineControl")
public class ConfermaOrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * Chiama il costruttore della superclasse
	 */
    public ConfermaOrdineControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    OrdineModel ordineModel = new OrdineModel();
    ProdottoOrdineModel prodottoOrdineModel = new ProdottoOrdineModel();
    ComposizioneModel composizioneModel = new ComposizioneModel();
    CarrelloModel carrelloModel = new CarrelloModel();
    ProdottoModel prodottoModel = new ProdottoModel();
    

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
	 * Effettua una richiesta HTTP POST per gestire la conferma dell'ordine
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String indirizzo = request.getParameter("indirizzo");
		String carta = request.getParameter("carta");
		String[] ind = indirizzo.split(",");
		String[] cart = carta.split(",");
		int codInd = Integer.parseInt(ind[0]);
		int codCar = Integer.parseInt(cart[0]);
		
		ClienteBean utente = (ClienteBean) request.getSession().getAttribute("utente");
		CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("cart");
		double totale = (carrello.getTotale() + (carrello.getTotale()*0.22)	+ 15.00);
		
		CartaBean c = utente.trovaCarta(codCar);
		IndirizzoBean i = utente.trovaIndirizzo(codInd);
		
		try {
			int codOrdine = ordineModel.generaCodice();
			ordineModel.doSave(i,c,"in preparazione","","",totale,utente.getNickName());
			prodottoOrdineModel.doSave(carrello.getProducts());
			composizioneModel.doSave(carrello.getProducts(),codOrdine);
			carrelloModel.remove(utente);
			prodottoModel.aggiorna(carrello.getProducts());
			
			request.getSession().removeAttribute("checkout");
			request.getSession().removeAttribute("cart");
			
			
			response.sendRedirect(request.getContextPath() + "/ordineok.jsp");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
