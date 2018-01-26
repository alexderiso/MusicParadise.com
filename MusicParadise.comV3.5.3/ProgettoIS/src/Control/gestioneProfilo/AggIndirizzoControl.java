package Control.gestioneProfilo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ClienteBean;
import Bean.IndirizzoBean;
import Bean.UtenteBean;
import Model.IndirizzoModel;

/**
 * Servlet implementation class AggiungiIndririzzoControl
 */

/**
 * Classe che implementa la servlet per aggiungere un indirizzo ad un account
 *
 */
@WebServlet("/AggIndirizzoControl")
public class AggIndirizzoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
 
     */
	
	/**
	 * Chiama il costruttore della superclasse
	 */
    public AggIndirizzoControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    IndirizzoModel indModel = new IndirizzoModel();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     * Vuoto
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * Effettua una richiesta HTTP POST per aggiungere un indirizzo ad un account cliente
	 * @param request
     * @param respose
     * @pre String[] ind != null && cap != null 
     * nome.matches("[a-zA-Z]{1,10}[ ]{0,1}[a-zA-Z]{0,10}") && cognome.matches("[a-zA-Z]{1,10}[ ]{0,1}[a-zA-Z]{0,10}")
				&&città.matches("[a-zA-Z]{1,10}[ ]{0,1}[a-zA-Z]{0,10}")&& indirizzo.matches("[a-zA-Z0-9]{1,10}[ ]{0,1}[a-zA-Z0-9]{0,10}")
				&& cap.matches("[0-9]{5}") && telefono.matches("[0-9]{10}"))
     * @post l'indirizzo è salvato nel database
     * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] ind = request.getParameterValues("paramOrder");
		ClienteBean utente = (ClienteBean) request.getSession().getAttribute("utente");
		String c = request.getParameter("cap"); //cap
		if(ind == null || c == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else if(verificaParametri(ind[0],ind[1],ind[2],ind[3],c,ind[4]) && utente != null) {
			int cap = Integer.parseInt(c);
			IndirizzoBean indirizzo = new IndirizzoBean();
			indirizzo.setNome(ind[0]);
			indirizzo.setCognome(ind[1]);
			indirizzo.setIndirizzo(ind[2]);
			indirizzo.setCittà(ind[3]);
			indirizzo.setCap(cap);
			indirizzo.setTelefono(ind[4]);
			
			
			try {
		
				indModel.doSave(indirizzo,utente.getNickName());
				utente.setIndirizzi(indModel.leggi(utente.getNickName()));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Boolean checkout = (Boolean) request.getSession().getAttribute("checkout");
			if(checkout != null) {
				request.getSession().setAttribute("utente", utente);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
				rd.forward(request, response);
			}else {
				request.getSession().setAttribute("utente", utente);
				response.sendRedirect(request.getContextPath() + "/profiloCliente.jsp");
			}
			
		}else {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
		
	}
	
	private boolean verificaParametri(String nome, String cognome, String indirizzo, String città, String cap, String telefono) {
		if((nome.matches("[a-zA-Z]{4,10}[ ]{0,1}[a-zA-Z]{0,10}") && cognome.matches("[a-zA-Z]{4,10}[ ]{0,1}[a-zA-Z]{0,10}")
				&&città.matches("[a-zA-Z]{1,10}[ ]{0,1}[a-zA-Z]{0,10}")&& indirizzo.matches("[a-zA-Z0-9]{3,10}[ ]{0,1}[a-zA-Z0-9]{0,10}")
				&& cap.matches("[0-9]{5}") && telefono.matches("[0-9]{10}"))) {
			return true;
		}
		return false;
	}

}
