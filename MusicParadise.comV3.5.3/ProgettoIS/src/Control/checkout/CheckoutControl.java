package Control.checkout;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CarrelloBean;
import Bean.CartaBean;
import Bean.IndirizzoBean;
import Bean.UtenteBean;
import Model.CartaModel;
import Model.IndirizzoModel;

/**
 * Servlet implementation class CheckoutControl
 */
/**
 * Classe che modella la servlet per eseguire il checkout 
 * @author Alessandro
 */
@WebServlet("/CheckoutControl")
public class CheckoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * Costruttore della superclasse
	 */
    public CheckoutControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    CartaModel cartaModel = new CartaModel();
    IndirizzoModel indirizzoModel = new IndirizzoModel();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     * Effettua una richiesta HTTP GET per gestire il checkout
     * @param request
     * @param respose
     * @throws ServletException, IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
		CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("cart");
	
			request.getSession().setAttribute("prodottiOrdine", carrello.getProducts());
			request.getSession().setAttribute("checkout", true);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
