package Control.accesso;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CarrelloBean;
import Bean.UtenteBean;
import Model.CarrelloModel;

/**
 * Servlet implementation class LogoutControl
 */
@WebServlet("/LogoutControl")
public class LogoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutControl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	CarrelloModel model = new CarrelloModel();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if(action != null) {	

			if(action.equalsIgnoreCase("esci")){
				CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("cart");
				UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
				if(carrello != null) {
					try {
						if(carrello.getProducts().size() == 0) { //se sono stati eliminati i prodotti da carrello bisogna eliminare il carrello daldatabase
							model.remove(utente);
						}else {
							model.doSave(carrello.getProducts(),utente);
						}
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
				}
				request.getSession().invalidate();
				response.sendRedirect("./index.jsp");
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
