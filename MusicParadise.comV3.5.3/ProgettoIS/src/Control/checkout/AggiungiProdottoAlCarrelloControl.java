package Control.checkout;

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
 * Servlet implementation class AggiungiProdottoAlCarrelloControl
 */
@WebServlet("/AggiungiProdottoAlCarrelloControl")
public class AggiungiProdottoAlCarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiProdottoAlCarrelloControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	static ProdottoModel model = new ProdottoModel();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CarrelloBean cart = (CarrelloBean)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new CarrelloBean();
			request.getSession().setAttribute("cart", cart);
		}

		String azione = request.getParameter("azC");

		if(azione.indexOf("aggiungi") != -1) {
			int quantit‡ = Integer.parseInt(request.getParameter("quantit‡"));
			ProdottoCatalogoBean bean = (ProdottoCatalogoBean) (request.getSession().getAttribute("prodotto"));

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
