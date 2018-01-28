package Control.acquisto;

import java.io.IOException;
import java.sql.SQLException;

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
 * Classe che implementa la servlet per rimuove un prodotto dal carrello
 */

/**
 * Servlet implementation class RimuoviProdottoCarrelloControl
 */
@WebServlet("/RimuoviProdottoCarrelloControl")
public class RimuoviProdottoCarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * Chiama il costruttorer della superclasse
	 */
	public RimuoviProdottoCarrelloControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	static ProdottoModel model = new ProdottoModel();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * Effettua una richiesta HTTP GET per eliminare il prodotto dal carrello
	 * @param request
	 * @param respose
	 * @pre prd != null && cod != null && cart != null
	 * @post il prodotto è stato rimosso dal carrello sizeCarrello() > sizeCarrelloPostRemove()
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CarrelloBean cart = (CarrelloBean)request.getSession().getAttribute("cart");


		if(cart == null) {
			String errore = "Carrello non esistente";
			request.getSession().setAttribute("errore",errore);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			try{

				int code = Integer.parseInt(request.getParameter("id"));
				ProdottoCatalogoBean prd = model.doRetrieveByKey(code); 
				if(prd == null) {
					String errore = "Prodotto non esistente";
					request.getSession().setAttribute("errore",errore);
					response.sendRedirect(request.getContextPath() + "/404.jsp");
				}else {
					int sizeCarrello = cart.getProducts().size();
					cart.deleteProduct(prd);
					int sizeCarrelloPostRemove = cart.getProducts().size();
					if(sizeCarrello > sizeCarrelloPostRemove) {
						request.getSession().setAttribute("cart", cart);
						request.setAttribute("cart", cart);
						if(cart.getProducts().size() == 0) {
							response.getWriter().write("finitiPrd");
						}
						return;
					}else {
						String errore = "Prodotto selzeionato non presente nel carrello";
						request.getSession().setAttribute("errore",errore);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
					}
				}

			}catch (SQLException e) {
			}catch (NumberFormatException e) {
				String errore = "Codice prodotto non valido";
				request.getSession().setAttribute("errore",errore);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * Vuoto
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
