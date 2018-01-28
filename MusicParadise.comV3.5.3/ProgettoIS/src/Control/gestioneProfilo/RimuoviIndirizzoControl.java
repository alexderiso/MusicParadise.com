package Control.gestioneProfilo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ClienteBean;
import Bean.IndirizzoBean;
import Model.IndirizzoModel;

/**
 * Servlet implementation class RimuoviIndirizzoControl
 */
@WebServlet("/RimuoviIndirizzoControl")
public class RimuoviIndirizzoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviIndirizzoControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    IndirizzoModel model = new IndirizzoModel();

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
	 * Effettua una richiesta HTTP POST per aggiungere un indirizzo ad un account cliente
	 * @param request
     * @param respose
     * @pre cliente != null && codice != null
     * @post l'indirizzo di spedizione viene rimosso dal database 
     * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String c = request.getParameter("cod");
		ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("utente");
		
		if(c == null || cliente == null) {
			String errore = "Accesso non consentito";
			request.getSession().setAttribute("errore",errore);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			try {
				int codice = Integer.parseInt(c);
				model.rimuoviIndirizzo(codice);
				ArrayList<IndirizzoBean> indirizzi = model.leggi(cliente.getNickName());
				cliente.setIndirizzi(indirizzi);
				response.sendRedirect(request.getContextPath() + "/profiloCliente.jsp");
			}catch(NumberFormatException e) {
				String errore = "Codice non esistente";
				request.getSession().setAttribute("errore",errore);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
