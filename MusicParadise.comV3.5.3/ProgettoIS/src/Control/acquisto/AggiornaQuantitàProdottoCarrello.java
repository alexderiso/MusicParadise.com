package Control.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CarrelloBean;

/**
 * Servlet implementation class AggiornaQuantit‡ProdottoCarrello
 */
@WebServlet("/AggiornaQuantit‡ProdottoCarrello")
public class AggiornaQuantit‡ProdottoCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaQuantit‡ProdottoCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

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
	 * Effettua una richiesta HTTP POST per aggiornare la quantit‡ del prodotto nel carrello
	 * @param request
	 * @param respose
	 * @pre cart != null && q != null c != null
	 * @post se nel carrello c'Ë un prodotto con il codice uguale a quello passato allora la quantit‡ del prodotto nel
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
		String q = request.getParameter("quantit‡");
		String c = request.getParameter("id");
		
		if(q == null || c == null || cart == null) {
			String errore = "Aggiornamento quantit‡ prodotto non valida";
			request.getSession().setAttribute("errore",errore);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			try {
				int code = Integer.parseInt(c);
				int quantit‡ = Integer.parseInt(q);
				cart.aggiornaQuantit‡(code,quantit‡);
				request.getSession().setAttribute("cart", cart);
				request.setAttribute("cart", cart);
				return;
				
			}catch(NumberFormatException e){
				String errore = "Argomenti non validi";
				request.getSession().setAttribute("errore",errore);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}
	}

}
