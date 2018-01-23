package Control.accesso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Bean.CarrelloBean;
import Bean.CartaBean;
import Bean.ClienteBean;
import Bean.GestoreOrdiniBean;
import Bean.IndirizzoBean;
import Bean.OrdineBean;
import Bean.ProdottoBean;
import Bean.ProdottoCatalogoBean;
import Bean.UtenteBean;
import Model.CarrelloModel;
import Model.CartaModel;
import Model.IndirizzoModel;
import Model.OrdineModel;
import Model.ClienteModel;
import Model.GestoreOrdineModel;

/**
 * Servlet implementation class AccediControl
 */

/**
 * Classe che modella la servlet per eseguire la login all'interno del sito
 * @author Alessandro
 */
@WebServlet("/AccediControl")
public class AccediControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteModel model = new ClienteModel();
	OrdineModel ordineModel = new OrdineModel();
	CarrelloModel carrelloModel = new CarrelloModel();
	CartaModel cartaModel = new CartaModel();
	IndirizzoModel indirizzoModel = new IndirizzoModel();
	GestoreOrdineModel gestOrdModel = new GestoreOrdineModel();



	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 */
	/**
	 * Costruttore della superclasse
	 */
	public AccediControl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * Effettua una richiesta HTTP GET per effettuare il login
	 * @param request
	 * @param respose
	 * @throws IOExceprion
	 * @author Alessandro
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * Effettua una richiesta HTTP POST per il login
	 * @param request
	 * @param response
	 * @pre nick != null && password != null && action != null 
	 * nick.length() < 5 && nick.length() > 15 && password.length() < 8 && password.length() > 20
	 * @post l'utente effettua il login al sistema
	 * @throws IOException
	 * @author Alessandro.....................
	 * 
	 * ..
	 * 
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");



		String nick= request.getParameter("nick");
		String password= request.getParameter("password");
		String action = request.getParameter("action");


		try {
			if(action != null) {	


				if(action.equals("accedi")) {
					response.setContentType( "application/json" );
					response.setCharacterEncoding( "UTF-8" );

					if(nick.length() < 5 || nick.length() > 15 || password.length() < 8 || password.length() > 20) {
						HashMap<String,String> lista = new HashMap<String,String>();
						lista.put("role","falso");
						response.getWriter().write(new Gson().toJson(lista));
						return;
					}else {
						ClienteBean cliente = model.leggi(nick, password);
						GestoreOrdiniBean gestoreOrdini = gestOrdModel.leggi(nick, password);
						if(cliente != null){
							request.getSession().setAttribute("utente", cliente);
							request.getSession().setAttribute("adminRoles", new Boolean(false));
							ArrayList<ProdottoCatalogoBean> prodInCarrello = carrelloModel.leggi(cliente.getNickName());
							CarrelloBean cart = (CarrelloBean) request.getSession().getAttribute("cart");
							if(prodInCarrello.size() > 0) {
								if(cart != null) {
									for(ProdottoCatalogoBean p : prodInCarrello) {
										cart.getProducts().add(p);
										request.getSession().setAttribute("cart",cart);
									}
								}else {
									CarrelloBean carrello = new CarrelloBean();
									carrello.setProducts(prodInCarrello);
									request.getSession().setAttribute("cart",carrello);
								}
							}

							ArrayList<IndirizzoBean> indirizzi = indirizzoModel.leggi(cliente.getNickName());
							cliente.setIndirizzi(indirizzi);
							ArrayList<CartaBean> carte = cartaModel.leggi(cliente.getNickName());
							cliente.setCarte(carte);
							HashMap<String,String> lista = new HashMap<String,String>();
							lista.put("role","cliente");
							response.getWriter().write(new Gson().toJson(lista));
							return;
						}else if (gestoreOrdini != null) {
							ArrayList<OrdineBean> inPreparazione = ordineModel.doRetrieveByStato("in preparazione");
							ArrayList<OrdineBean> spedito = ordineModel.doRetrieveByStato("spedito");
							ArrayList<OrdineBean> consegnato = ordineModel.doRetrieveByStato("consegnato");
							request.getSession().setAttribute("inPreparazione",inPreparazione);
							request.getSession().setAttribute("spedito",spedito);
							request.getSession().setAttribute("consegnato",consegnato);
							request.getSession().setAttribute("utente-gestore", gestoreOrdini);
							request.getSession().setAttribute("adminRoles",new Boolean(true));
							HashMap<String,String> lista = new HashMap<String,String>();
							lista.put("role","gestore-ordini");
							response.getWriter().write(new Gson().toJson(lista));
							return;

						}else {
							HashMap<String,String> lista = new HashMap<String,String>();
							lista.put("role","falso");
							response.getWriter().write(new Gson().toJson(lista));
							return;
						}
					}
				}

			}else {
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		} catch (SQLException e) {

		}

	}

}

