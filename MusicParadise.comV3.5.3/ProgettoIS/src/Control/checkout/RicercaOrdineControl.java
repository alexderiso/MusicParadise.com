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
@WebServlet("/RicercaOrdineControl")
public class RicercaOrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaOrdineControl() {
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
		
		int cod = Integer.parseInt(request.getParameter("codOrdine"));
		
		ArrayList<OrdineBean> ordiniUtente = (ArrayList<OrdineBean>) request.getSession().getAttribute("ordiniUtente");
		
		for(OrdineBean temp : ordiniUtente) {
			if(temp.getNumOrdine() == cod) {
				request.getSession().setAttribute("ordine",temp);
			}
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ordine.jsp");
		rd.forward(request, response);
		
	}

}
