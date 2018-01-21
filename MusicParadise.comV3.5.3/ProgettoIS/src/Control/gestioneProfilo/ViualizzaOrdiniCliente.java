package Control.gestioneProfilo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.OrdineBean;
import Model.OrdineModel;
import Model.ProdottoOrdineModel;
import Model.ClienteModel;

/**
 * Servlet implementation class ViualizzaOrdiniCliente
 */

/**
 * Classe che implementa la servlet per visualizzare gli ordini effettuati da parte dei clienti
 * 
 *
 */
@WebServlet("/ViualizzaOrdiniCliente")
public class ViualizzaOrdiniCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * Chiama il costruttore della superclasse
	 */
    public ViualizzaOrdiniCliente() {
        super();
        // TODO Auto-generated constructor stub
    }
    static OrdineModel ordineModel = new OrdineModel();
	static ClienteModel utenteModel = new ClienteModel();
	static ProdottoOrdineModel prodottoOrdineModel = new ProdottoOrdineModel();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * Effettua una richiesta HTTP GET per visualizzare gli ordini effettuati dal cliente
	 * @param request
     * @param respose
     * @pre nickname != null
     * @post ordiniUtente.size() == 0 || ordiniUtente.size() > 0
     * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nickname = request.getParameter("nickname");
		try {
			ArrayList<OrdineBean> ordiniUtente = ordineModel.ordiniUtente(nickname);
			for(OrdineBean ord : ordiniUtente) {
				ord.setProdotti(prodottoOrdineModel.prodottiOrdine(ord.getNumOrdine()));
			}
			request.getSession().setAttribute("ordiniUtente", ordiniUtente);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ordiniCliente.jsp");
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
