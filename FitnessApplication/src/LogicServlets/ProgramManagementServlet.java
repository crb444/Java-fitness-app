package LogicServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.Program;
import Patterns.Mappers.ProgramMapper;
import Patterns.ServiceLayers.ProgramCreationServiceLayer;
import Utility.GlobalAttributes;

/**
 * Servlet implementation class ProgramManagementServlet
 */
@WebServlet("/ProgramManagementServlet")
public class ProgramManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgramManagementServlet() {
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
		// set ProgramCreationServiceLayer program to new program
		if (request.getParameter("test") != null) {
			GlobalAttributes.workoutCreationTarget = 1;
			response.sendRedirect("WorkoutCreation.jsp");
		}
		if (request.getParameter("newProgram") != null) {
			GlobalAttributes.workoutCreationTarget = ProgramMapper.getNextProgramIdFromDatabase();
			Program newProgram = new Program();
			newProgram.setId(GlobalAttributes.workoutCreationTarget);
			ProgramCreationServiceLayer.program = newProgram;
			response.sendRedirect("ProgramNameInput.jsp");		
		}
		String newProgramName;
		if ((newProgramName = request.getParameter("newProgramName")) != null) {
			/*
			ProgramCreationServiceLayer.program = new Program();
			ProgramCreationServiceLayer.program.setName(newProgramName);
			ProgramCreationServiceLayer.program.setId(ProgramMapper.getNextProgramIdFromDatabase());
			response.sendRedirect("ProgramCreationDetailPage.jsp");
			*/
			ProgramCreationServiceLayer.program.setName(newProgramName);
			response.sendRedirect("ProgramCreationDetailPage.jsp");
		}
	}

}
