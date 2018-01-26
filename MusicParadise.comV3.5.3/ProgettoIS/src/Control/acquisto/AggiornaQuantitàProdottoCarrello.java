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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CarrelloBean cart = (CarrelloBean)request.getSession().getAttribute("cart");
		String q = request.getParameter("quantit‡");
		String c = request.getParameter("id");
		
		if(q == null || c == null || cart == null) {
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
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}
	}

}
