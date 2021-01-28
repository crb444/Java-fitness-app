package LogicServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Patterns.Mappers.WorkoutMapper;
import Patterns.ServiceLayers.WorkoutCreationServiceLayer;
import Utility.GlobalAttributes;

/**
 * Servlet implementation class WorkoutCreationServlet
 */
@WebServlet("/WorkoutCreationServlet")
public class WorkoutCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static int currentClientID;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutCreationServlet() {
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
		//System.out.println("Servlet method executed. Next id is: " + WorkoutMapper.getNextWorkoutIdFromDatabase());
		String workoutName = request.getParameter("workoutName");
		String workoutTargetMuscleGroup = request.getParameter("workoutTargetMuscleGroup");
		WorkoutCreationServiceLayer.conductWorkoutCreation(workoutName, workoutTargetMuscleGroup);
		if (GlobalAttributes.workoutCreationTarget == -1) {
    	response.getWriter().println("<!DOCTYPE html><html><body>Workout has been added." +
    	" <a href='ClientProfile.jsp'>Return to Client Profile</a></body></html>");
		} else {
	    	response.getWriter().println("<!DOCTYPE html><html><body>Workout has been added." +
	    	    	" <a href='ProgramCreationDetailPage.jsp'>Return to Program Creation Page</a></body></html>");
		}
	}

}
