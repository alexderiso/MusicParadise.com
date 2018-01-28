package Control.gestioneOrdini;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

/**
 * Servlet implementation class ConfermaModOrdineControl
 */
/**
 * Classe che modella la servlet per eseguire la modifica dello stato di un ordine da parte del gestore ordini
 *
 */
@WebServlet("/ConfermaModOrdineControl")
public class ConfermaModOrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * Chiama il costruttore della superclasse
	 */
    public ConfermaModOrdineControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    OrdineModel ordineModel = new OrdineModel();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     * Effettua una richiesta HTTP GET per gestire la modifica dello stato del ordine
     * @pre dataConsegna != null && corriere != null && numeroTracking != null && gestore != null
     * data.matches("[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}") && tracking.matches("[0-9]{10}")&&(corriere.matches("[A-Za-z]{3,20}"))
     * @post lo stato dell'ordine è stato modificato
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		String inizStr = request.getParameter("dataConsegna");
		String corriere = request.getParameter("corriere");
		String numeroTracking = request.getParameter("numTracking");
		
		OrdineBean ordine = (OrdineBean) request.getSession().getAttribute("ordMod");
		GestoreOrdiniBean gestore = (GestoreOrdiniBean) request.getSession().getAttribute("utente-gestore");
		
		if(inizStr == null || corriere == null || numeroTracking == null || ordine == null) {
			String errore = "Operazione non consentita";
			request.getSession().setAttribute("errore",errore);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else if(verificaParametri(inizStr,corriere,numeroTracking)&& gestore != null) {
			LocalDate dataConsegna = LocalDate.parse(inizStr, formatter);
			java.sql.Date dataSql = Date.valueOf(dataConsegna); 
			
			
			try {
				ordineModel.aggiorna(ordine.getNumOrdine(), numeroTracking, dataSql, corriere);
				request.getSession().removeAttribute("ordMod");
				ArrayList<OrdineBean> inPreparazione = ordineModel.doRetrieveByStato("in preparazione");
				ArrayList<OrdineBean> spedito = ordineModel.doRetrieveByStato("spedito");
				ArrayList<OrdineBean> consegnato = ordineModel.doRetrieveByStato("consegnato");
				request.getSession().setAttribute("inPreparazione",inPreparazione);
				request.getSession().setAttribute("spedito",spedito);
				request.getSession().setAttribute("consegnato",consegnato);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/gestore-ordini.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			String errore = "Parametri non validi";
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
	
	private boolean verificaParametri(String data, String corriere, String tracking) {
		if(data.matches("[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}") && tracking.matches("[0-9]{10}")
				&&(corriere.matches("[A-Za-z]{3,20}"))) {
			return true;
		}
		return false;
	}

}
