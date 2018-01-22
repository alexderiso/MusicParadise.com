package Control.checkout;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.OrdineBean;

/**
 * Servlet implementation class RicercaOrdineControl
 */

/**
 * Servlet che ricerca un determinato ordine nel sistema
 * @author Alex
 *
 */
@WebServlet("/RicercaOrdineControl")
public class RicercaOrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * Chiama il costruttore della superclasse
	 */
    public RicercaOrdineControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     * vuoto
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * Effettua una richiesta HTTP POST per ricercare un ordine all'interno del sistema
	 * @param request
     * @param respose
     * @pre cod != null
     * @post ordiniUtente.size() == 0 || ordiniUtente.size() > 0
     * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String c = request.getParameter("codOrdine"); //cod
		if(c == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			
			try {
				int cod = Integer.parseInt(request.getParameter("codOrdine"));
				ArrayList<OrdineBean> ordiniUtente = (ArrayList<OrdineBean>) request.getSession().getAttribute("ordiniUtente");
				
				for(OrdineBean temp : ordiniUtente) {
					if(temp.getNumOrdine() == cod) {
						request.getSession().setAttribute("ordine",temp);
					}
				}
				response.sendRedirect(request.getContextPath() + "/ordine.jsp");
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		}
		
	}

}
