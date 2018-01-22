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
     * @pre azione != null && cod != null
     * @post il prodotto è stato rimosso dal carrello
     * @throws ServletException, IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CarrelloBean cart = (CarrelloBean)request.getSession().getAttribute("cart");
		String azione = request.getParameter("azC");
		
		if(azione == null || cart == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			try{
				if(azione.equalsIgnoreCase("elimina")) {
					int code = Integer.parseInt(request.getParameter("id"));
					cart.deleteProduct(model.doRetrieveByKey(code));
					request.getSession().setAttribute("cart", cart);
					request.setAttribute("cart", cart);
					if(cart.getProducts().size() == 0) {
						response.getWriter().write("finitiPrd");
					}
					return;
				}	
			}catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}catch (NumberFormatException e) {
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
