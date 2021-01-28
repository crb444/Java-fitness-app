package LogicServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Objects.Client;
import Patterns.Mappers.ClientMapper;
import Patterns.ServiceLayers.ClientDataServiceLayer;
import Patterns.ServiceLayers.ClientProfileServiceLayer;
import Patterns.ServiceLayers.WorkoutCreationServiceLayer;
import Utility.GlobalAttributes;

import java.util.ArrayList;
/**
 * Servlet implementation class ClientManagementServlet
 */
@WebServlet("/ClientManagementServlet")
public class ClientManagementServlet extends HttpServlet {
	
	public static ArrayList<Integer> clientIds = new ArrayList<>();
	
	public static ClientDataServiceLayer clientDataServiceLayer;
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientManagementServlet() {
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
		/*int idRangeStart = 100;
		int idRangeEnd = 120;	*/	
		//clientIds.get(0);
		
		for (int i : clientIds) {
	        if (request.getParameter(Integer.toString(i)) != null) {
	        	//ClientMapper.removeClientById(i);
	        	clientDataServiceLayer.conductClientRemoval(i);
	        	response.getWriter().println("<!DOCTYPE html><html><body>Client with id: " + 
	        	Integer.toString(i) + " has been removed." +
	        	" <a href='ListClients.jsp'>Return to Client List</a></body></html>");
	        	break;
	        	//response.sendRedirect("ListClients.jsp");
	        }
	        if (request.getParameter(Integer.toString(i) + "WORKOUT") != null) {
	        	//ClientMapper.removeClientById(i);
	        	GlobalAttributes.workoutCreationTarget = -1;
	        	WorkoutCreationServlet.currentClientID = i;
	        	response.sendRedirect("WorkoutCreation.jsp");
	        	break;
	        	//response.sendRedirect("ListClients.jsp");
	        }
	        if (request.getParameter(Integer.toString(i) + "PROFILE") != null) {
	        	//ClientMapper.removeClientById(i);
	        	System.out.println("PROPER METHOD IS EXECUTING");
	        	ArrayList<Client> clients = clientDataServiceLayer.getClients();
	        	Client clientToSend = new Client();
	        	for (Client c : clients) {
	        		if (c.getClientId() == i) {
	        			clientToSend = c;
	        		}
	        	}
	        	WorkoutCreationServlet.currentClientID = i;
	        	ClientProfileServiceLayer.client = clientToSend;
	        	response.sendRedirect("ClientProfile.jsp");
	        	break;
	        	//response.sendRedirect("ListClients.jsp");
	        }
	        
		}
		
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
