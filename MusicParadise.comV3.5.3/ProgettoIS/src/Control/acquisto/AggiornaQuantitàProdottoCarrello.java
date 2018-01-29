package Control.acquisto;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CarrelloBean;
import Bean.ProdottoCatalogoBean;
import Model.ProdottoModel;
import Model.ProdottoOrdineModel;

/**
 * Servlet implementation class AggiornaQuantitàProdottoCarrello
 */
@WebServlet("/AggiornaQuantitàProdottoCarrello")
public class AggiornaQuantitàProdottoCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaQuantitàProdottoCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }
    ProdottoModel model = new ProdottoModel();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * Effettua una richiesta HTTP POST per aggiornare la quantità del prodotto nel carrello
	 * @param request
	 * @param respose
	 * @pre cart != null && q != null c != null
	 * @post se nel carrello c'è un prodotto con il codice uguale a quello passato allora la quantità del prodotto nel
	 * carrello viene incrementata
	 * @throws IOExeption, ServletException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CarrelloBean cart = (CarrelloBean)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new CarrelloBean();
			request.getSession().setAttribute("cart", cart);
		}
		String q = request.getParameter("quantità");
		String c = request.getParameter("id");
		
		if(q == null || c == null || cart == null) {
			String errore = "Aggiornamento quantità prodotto non valida";
			request.getSession().setAttribute("errore",errore);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			try {
				int code = Integer.parseInt(c);
				int quantità = Integer.parseInt(q);
				ProdottoCatalogoBean prd = model.doRetrieveByKey(code);
				if(quantità > prd.getNumDisp() || quantità < 0) {
					String errore = "Quantità non valida";
					request.getSession().setAttribute("errore",errore);
					response.sendRedirect(request.getContextPath() + "/404.jsp");
				}else {
					cart.aggiornaQuantità(code,quantità);
					request.getSession().setAttribute("cart", cart);
					request.setAttribute("cart", cart);
					return;
				}
			}catch(NumberFormatException e){
				String errore = "Argomenti non validi";
				request.getSession().setAttribute("errore",errore);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
