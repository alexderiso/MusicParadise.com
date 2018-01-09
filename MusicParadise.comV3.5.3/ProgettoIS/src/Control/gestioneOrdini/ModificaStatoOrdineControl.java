package Control.gestioneOrdini;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.OrdineBean;
import Model.OrdineModel;

/**
 * Servlet implementation class ModificaStatoOrdineControl
 */

/**
 * Classe che implementa la servlet per modificare lo stato di un ordine da parte del gestoreOrdini
 * 
 *
 */
@WebServlet("/ModificaStatoOrdineControl")
public class ModificaStatoOrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * Chiama il costruttore della superclasse
	 */
    public ModificaStatoOrdineControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    OrdineModel ordineModel = new OrdineModel();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     * Effettua una richiesta HTTP GET per modificare lo stato di un ordine
     * @param request
     * @param respose
     * @throws ServletException, IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int codice = Integer.parseInt(request.getParameter("cod"));
		try {
			OrdineBean ordine = ordineModel.doRetrieveByKey(codice);
			request.getSession().setAttribute("ordMod", ordine);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/modificaOrdine.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
