package Control.checkout;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ProdottoBean;
import Bean.ProdottoCatalogoBean;
import Model.ProdottoModel;



/**
 * Servlet implementation class RicercaProdottoControl
 */

/**
 * Servlet dedicata alla ricerca dei prodotti all'interno del database in base ad una certa chiave di ricerca
 * @author Alessandro
 */
@WebServlet("/RicercaProdottoControl")
public class RicercaProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Variabili di instanza
	static ProdottoModel model;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * Costruttore vuoto
	 */
	public RicercaProdottoControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * utilizziamo una richiesta http get, per gestire la ricerca dei prodotti, esso richiama
	 * ProdottoModel
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @pre strumento != null || code != null || name != null || marca != null
	 * @post prodotti.size() == 0 || prodotti.size() > 0
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		model = new ProdottoModel();
		String strumento = request.getParameter("strumento");
		String code = request.getParameter("prodotto");
		String name = request.getParameter("name");
		String marca = request.getParameter("marca");
		if(strumento == null && code == null && name == null && marca == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else {
			if(strumento != null){
				try {
					Collection<ProdottoCatalogoBean> collezione = model.doRetrieveByInstruments(strumento);
					request.getSession().removeAttribute("prodotti");
					request.getSession().setAttribute("prodotti", collezione);


					response.sendRedirect(request.getContextPath() + "/catalogo.jsp");
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				//TODO Errore non è stato trovato nulla
			}
			if(code != null){
				try {
					int codeNum = Integer.parseInt(code);
					ProdottoBean bean = model.doRetrieveByKey(codeNum);
					if(bean == null){
						//errore
					}else{
						request.getSession().removeAttribute("prodotto");
						request.getSession().setAttribute("prodotto", bean);
						response.sendRedirect(request.getContextPath() + "/prodotto.jsp");

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(NumberFormatException e){
					response.sendRedirect(request.getContextPath() + "/404.jsp");
				}

			}
			if(name != null) {
				System.out.println("sTO QUA");
				Collection<ProdottoCatalogoBean> collezione;
				try {
					collezione = model.doRetrieveByName(name);
					if(collezione == null) {
						System.out.println("ERRORE");
					} else {
						request.getSession().removeAttribute("prodotti");
						request.getSession().setAttribute("prodotti", collezione);

						response.sendRedirect(request.getContextPath() + "/catalogo.jsp");

					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("sTO QUASQL");
				}
			}
			if(marca != null) {
				Collection<ProdottoCatalogoBean> collezione;
				try {
					collezione = model.doRetrieveByMarca(marca);

					if(collezione == null) {
						System.out.println("ERRORE");
					} else {
						request.getSession().removeAttribute("prodotti");
						request.getSession().setAttribute("prodotti", collezione);

						response.sendRedirect(request.getContextPath() + "/catalogo.jsp");

					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("sTO QUASQL");
				}
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * Vuoto
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
