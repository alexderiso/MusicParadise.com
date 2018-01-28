package Control.gestioneProfilo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ClienteBean;

/**
 * Servlet implementation class VisualizzaProfiloControl
 */
@WebServlet("/VisualizzaProfiloControl")
public class VisualizzaProfiloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaProfiloControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
	 * Effettua una richiesta HTTP POST per aggiungere un indirizzo ad un account cliente
	 * @param request
     * @param respose
     * @pre cliente != null 
     * @post il cliente visualizza il suo profilo
     * @throws ServletException, IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("utente");
		 if(cliente == null) {
			 response.sendRedirect(request.getContextPath() + "/index.jsp");
		 }else {
			 response.sendRedirect(request.getContextPath() + "/profiloCliente.jsp");
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
