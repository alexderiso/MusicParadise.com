package Control.gestioneProfilo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CartaBean;
import Bean.ClienteBean;
import Bean.IndirizzoBean;
import Model.CartaModel;

/**
 * Servlet implementation class RimuoviCartaControl
 */
@WebServlet("/RimuoviCartaControl")
public class RimuoviCartaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RimuoviCartaControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	CartaModel model = new CartaModel();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String c = request.getParameter("cod");
		ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("utente");

		if(c == null || cliente == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			try {
				int codice = Integer.parseInt(c);
				model.rimuoviCarta(codice);
				ArrayList<CartaBean> carte = model.leggi(cliente.getNickName());
				cliente.setCarte(carte);
				response.sendRedirect(request.getContextPath() + "/profiloCliente.jsp");
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
