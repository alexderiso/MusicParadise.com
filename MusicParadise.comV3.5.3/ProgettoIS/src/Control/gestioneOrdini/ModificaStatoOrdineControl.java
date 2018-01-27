package Control.gestioneOrdini;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.GestoreOrdiniBean;
import Bean.OrdineBean;
import Model.OrdineModel;
import Model.ProdottoOrdineModel;

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
	ProdottoOrdineModel prdModel = new ProdottoOrdineModel();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * Effettua una richiesta HTTP GET per modificare lo stato di un ordine
	 * @param request
	 * @param respose
	 * @pre codice != null
	 * @post ordine != null
	 * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String c = request.getParameter("cod"); //cod
		GestoreOrdiniBean gestore = (GestoreOrdiniBean) request.getSession().getAttribute("utente-gestore");
		if(gestore != null) {
			if(c == null) {
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}else {
				try {
					int codice = Integer.parseInt(c);
					OrdineBean ordine = ordineModel.doRetrieveByKey(codice);
					
					if(ordine == null) {
						String errore = "Ordine non esistente";
						request.getSession().setAttribute("errore",errore);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
					}else {
						if(ordine.getStato().equalsIgnoreCase("in preparazione")) {
							request.getSession().setAttribute("ordMod", ordine);
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/modificaOrdine.jsp");
							rd.forward(request, response);
						}else if(ordine.getStato().equalsIgnoreCase("spedito")) {
							ordineModel.aggiornaConsegnato(codice);
							ArrayList<OrdineBean> inPreparazione = ordineModel.doRetrieveByStato("in preparazione");
							ArrayList<OrdineBean> spedito = ordineModel.doRetrieveByStato("spedito");
							ArrayList<OrdineBean> consegnato = ordineModel.doRetrieveByStato("consegnato");
							request.getSession().setAttribute("inPreparazione",inPreparazione);
							request.getSession().setAttribute("spedito",spedito);
							request.getSession().setAttribute("consegnato",consegnato);
							response.sendRedirect(request.getContextPath() + "/gestore-ordini.jsp");
						}else {
							response.sendRedirect(request.getContextPath() + "/404.jsp");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (NumberFormatException e) {
					String errore = "Codice non valido";
					request.getSession().setAttribute("errore",errore);
					response.sendRedirect(request.getContextPath() + "/404.jsp");
				}
			}
		}else {
			String errore = "Accesso non consentito";
			request.getSession().setAttribute("errore",errore);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
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
