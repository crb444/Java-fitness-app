package LogicServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.FitnessGoal;
import Patterns.ServiceLayers.ClientDataServiceLayer;

/**
 * Servlet implementation class ClientCreationServlet
 */
@WebServlet("/ClientCreationServlet")
public class ClientCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static ClientDataServiceLayer clientDataServiceLayer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientCreationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("requestFirstNme is " + request.getParameter("firstName"));
		String firstName = request.getParameter("firstName");
		clientDataServiceLayer.conductClientAddition(firstName,
				request.getParameter("lastName"), FitnessGoal.COMPETITION,
				request.getParameter("contactnumber")
				);
    	response.getWriter().println("<!DOCTYPE html><html><body>Client " + firstName +
    			" has been successfully added."
    			+ " <a href=\"ListClients.jsp\">Return to Client List</a></body></html>");
    	
	}

}
