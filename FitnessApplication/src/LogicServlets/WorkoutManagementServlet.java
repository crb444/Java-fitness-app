package LogicServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.Workout;
import Patterns.ServiceLayers.ClientProfileServiceLayer;
import Patterns.ServiceLayers.WorkoutDetailServiceLayer;

/**
 * Servlet implementation class WorkoutManagementServlet
 */
@WebServlet("/WorkoutManagementServlet")
public class WorkoutManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutManagementServlet() {
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
		System.out.println("Workout management dopost is executing");
		for (Workout w : ClientProfileServiceLayer.clientWorkouts) {
			if (request.getParameter(w.getId() + "REMOVE") != null) {
				System.out.println("found the request get parameter to remove this id: " + w.getId());
				ClientProfileServiceLayer.conductWorkoutRemoval(w);
	        	response.getWriter().println("<!DOCTYPE html><html><body>Workout with id: " + 
	        	w.getId() + " has been removed." +
	        	" <a href='ClientProfile.jsp'>Return to Client Profile</a></body></html>");
			}
			if (request.getParameter(w.getId() + "VIEW") != null) {
				WorkoutDetailServiceLayer.currentWorkout = w;
				response.sendRedirect("WorkoutDetail.jsp");
			}
		}
		
	}

}
