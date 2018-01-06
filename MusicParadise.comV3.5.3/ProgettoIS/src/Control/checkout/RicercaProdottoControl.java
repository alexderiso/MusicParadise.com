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
@WebServlet("/RicercaProdottoControl")
public class RicercaProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	  static ProdottoModel model;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaProdottoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		model = new ProdottoModel();
		String strumento = request.getParameter("strumento");
		String code = request.getParameter("prodotto");
		String name = request.getParameter("name");
		String marca = request.getParameter("marca");
		System.out.println(strumento);
		if(strumento != null){
		try {
				Collection<ProdottoCatalogoBean> collezione = model.doRetrieveByInstruments(strumento);
		    	   request.getSession().removeAttribute("prodotti");
				   request.getSession().setAttribute("prodotti", collezione);
					
					
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/catalogo.jsp");
					rd.forward(request, response);
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
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/prodotto.jsp");
					rd.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
					
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/catalogo.jsp");
					rd.forward(request, response);
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
					
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/catalogo.jsp");
					rd.forward(request, response);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("sTO QUASQL");
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
