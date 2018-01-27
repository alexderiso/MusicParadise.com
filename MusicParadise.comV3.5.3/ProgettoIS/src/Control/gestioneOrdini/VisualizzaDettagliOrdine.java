package Control.gestioneOrdini;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class VisualizzaDettagliOrdine
 */
@WebServlet("/VisualizzaDettagliOrdine")
public class VisualizzaDettagliOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaDettagliOrdine() {
        super();
        // TODO Auto-generated constructor stub
    }
    OrdineModel model = new OrdineModel();
    ProdottoOrdineModel prdModel = new ProdottoOrdineModel();

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
		String c = request.getParameter("codice");
		GestoreOrdiniBean gestore = (GestoreOrdiniBean) request.getSession().getAttribute("utente-gestore");
		
		if(gestore != null) {
			if(c == null) {
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}else {
				try {
					int codice = Integer.parseInt(c);
					OrdineBean ordine = model.doRetrieveByKey(codice);
					
					if(ordine == null) {
						String errore = "Ordine non esistente";
						request.getSession().setAttribute("errore",errore);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
					}else {
						ordine.setProdotti(prdModel.prodottiOrdine(ordine.getNumOrdine()));
						request.getSession().setAttribute("dettagli", ordine);
						response.sendRedirect(request.getContextPath() + "/dettagliOrdine.jsp");
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

}
