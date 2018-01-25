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
 * @author Alessandro
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
	 * @pre azione != null prodotto != null quantit‡ != null cart != null
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


		String azione = request.getParameter("azC");
		if(azione == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			if(azione.indexOf("aggiungi") != -1) {
				String q = request.getParameter("quantit‡");
				ProdottoCatalogoBean bean = (ProdottoCatalogoBean) (request.getSession().getAttribute("prodotto"));
				if(q == null || bean == null) {
					response.sendRedirect(request.getContextPath() + "/404.jsp");
				}else {

					try {

						int quantit‡ = Integer.parseInt(q);

						if(azione.equalsIgnoreCase("aggiungiFromPrd")){
							cart.addProduct(bean);
							if(cart.getQuantit‡ByCodice(bean.getCodice()) >= bean.getNumDisp()) {
								response.getWriter().write("fineDisp");
							} else {
								int differenza = bean.getNumDisp() - cart.getQuantit‡ByCodice(bean.getCodice()); 
								response.getWriter().write(differenza+"");
							}
						}
						if(azione.equalsIgnoreCase("aggiungiFromCart")){
							int code = Integer.parseInt(request.getParameter("id"));
							System.out.println("aggiungiFromCart: "+ code);
							cart.aggiornaQuantit‡(code,quantit‡);
						}

						request.getSession().setAttribute("cart", cart);
						request.setAttribute("cart", cart);
						return;
					}catch(NumberFormatException e) {
						response.sendRedirect(request.getContextPath() + "/404.jsp");
					}
				}
			}
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
