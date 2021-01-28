package Authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.Exercise;
import Utility.GlobalAttributes;

/**
 * Servlet implementation class SimpleServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int DEFAULT_TRAINERID = 3;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String toString() {
    	return "This is the LoginServlet toString() method";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 
		System.out.println("Hello from GET method"); 
		PrintWriter writer = response.getWriter();  
		writer.println("<h3>Hello in html</h3>");  
		String paramUserName = "userName"; 
		String paramUserValue = request.getParameter(paramUserName); 
		String paramPasswordName = "passWord"; 
		String paramPasswordValue = request.getParameter(paramPasswordName); 
		
		writer.println("Hello from GET " + paramUserValue + "" + paramPasswordValue); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 

		
		
		PrintWriter writer = response.getWriter();  
		writer.println("<h3>Fitness Dashboard</h3>");  
		
		String paramUserName = "userName"; 
		String paramUserValue = request.getParameter(paramUserName); 
		String paramPasswordName = "passWord"; 
		String paramPasswordValue = request.getParameter(paramPasswordName);  
		
		String defaultUserName = getServletConfig().getInitParameter("userNameI"); 
		String defaultPassWord = getServletConfig().getInitParameter("passWordI"); 
		
		System.out.println(defaultUserName); 
		System.out.println(defaultPassWord);  
		
		if(paramUserValue.equals(defaultUserName) && paramPasswordValue.contentEquals(defaultPassWord)) {
			response.sendRedirect("/FitnessApplication/success.jsp");   
			writer.println("<h2>Hello admin</h2>"); 
		} else {
			writer.println("<h2>Hello general user</h2>");  
		}
		
		if (paramUserValue.equals("trainer") && paramPasswordValue.equals("trainer")) {
			GlobalAttributes.trainerID = DEFAULT_TRAINERID;
			response.sendRedirect("ListClients.jsp");
		} else {
			writer.println("Error: Invalid login details");
		}
	}

}
