package Control.gestioneOrdini;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
     * @pre dataConsegna != null && corriere != null && numeroTracking != null
     * data.matches("[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}") && tracking.matches("[0-9]{10}")&&(corriere.matches("[A-Za-z]{3,20}"))
     * @post lo stato dell'ordine è stato modificato
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		String inizStr = request.getParameter("dataConsegna");
		System.out.println(inizStr);
		String corriere = request.getParameter("corriere");
		String numeroTracking = request.getParameter("numTracking");
		
		OrdineBean ordine = (OrdineBean) request.getSession().getAttribute("ordMod");
		
		if(inizStr == null || corriere == null || numeroTracking == null || ordine == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else if(verificaParametri(inizStr,corriere,numeroTracking)) {
			LocalDate dataConsegna = LocalDate.parse(inizStr, formatter);
			java.sql.Date dataSql = Date.valueOf(dataConsegna); 
			
			
			try {
				ordineModel.aggiorna(ordine.getNumOrdine(), numeroTracking, dataSql, corriere);
				Collection<OrdineBean> collezione = ordineModel.listaOrdini();
				request.getSession().setAttribute("ordini",collezione);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/gestore-ordini.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
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
