package LogicServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.WorkoutComponent;
import Patterns.ServiceLayers.WorkoutCreationServiceLayer;

/**
 * Servlet implementation class ExerciseSpecificationServlet
 */
@WebServlet("/ExerciseSpecificationServlet")
public class ExerciseSpecificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseSpecificationServlet() {
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
		String exerciseName = request.getParameter("exerciseName");
		String exerciseSets = request.getParameter("exerciseSets");
		String exerciseReps = request.getParameter("exerciseReps");

		if (exerciseName != null && exerciseSets != null && exerciseReps != null) {
			WorkoutComponent workoutComponent = new WorkoutComponent(Integer.parseInt(exerciseSets),
					Integer.parseInt(exerciseReps), exerciseName);
			WorkoutCreationServiceLayer.getWorkoutComponents().add(workoutComponent);
		}
		
		response.sendRedirect("WorkoutCreation.jsp");
	}

}
