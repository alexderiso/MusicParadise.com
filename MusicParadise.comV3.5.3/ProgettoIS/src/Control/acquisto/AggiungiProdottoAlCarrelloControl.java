package Control.acquisto;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CarrelloBean;
import Bean.ProdottoBean;
import Bean.ProdottoCatalogoBean;
import Model.ProdottoModel;
/**
 * Servlet implementation class AggiungiProdottoAlCarrelloControl
 */

/**
 * Classe che modella la servlet per aggiungere un prodotto al carrello
 * @author Antonio
 */
@WebServlet("/AggiungiProdottoAlCarrelloControl")
public class AggiungiProdottoAlCarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * Costruttore della superclasse
	 */
	public AggiungiProdottoAlCarrelloControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	static ProdottoModel model = new ProdottoModel();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * Effettua una richiesta HTTP GET per aggiungere un prodotto al carrello
	 * @param request
	 * @param respose
	 * @pre bean != null && cart != null 
	 * @post se il prodotto aggiunto non esiste nel carrello allora viene aggiunto al carrello
	 * se nel carrello gi‡ esiste il prodotto allora la quantit‡ del prodotto viene aumentata di 1
	 * @throws IOExeption, ServletException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CarrelloBean cart = (CarrelloBean)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new CarrelloBean();
			request.getSession().setAttribute("cart", cart);
		}


		ProdottoCatalogoBean bean = (ProdottoCatalogoBean) (request.getSession().getAttribute("prodotto"));
		if(bean == null) {
			String errore = "Nessun prodotto selezionato";
			request.getSession().setAttribute("errore",errore);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {



			cart.addProduct(bean);
			if(cart.getQuantit‡ByCodice(bean.getCodice()) >= bean.getNumDisp()) {
				response.getWriter().write("fineDisp");
			} else {
				int differenza = bean.getNumDisp() - cart.getQuantit‡ByCodice(bean.getCodice()); 
				response.getWriter().write(differenza+"");
			}
			request.setAttribute("cart", cart);
			return;

		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
