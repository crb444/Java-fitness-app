package Patterns.ServiceLayers;

import java.util.ArrayList;

import Business.Program;
import Business.Workout;
import Patterns.Mappers.ProgramMapper;

public class ProgramCreationServiceLayer {
	public static Program program;
	
	public static String displayWorkoutsForProgram() {
		if (program == null) {
			System.out.println("****ERROR**** programcreationservicelayer program instance not"
					+ "initialized");
			return null;
		}
		ArrayList<Workout> workouts = ProgramMapper.getWorkoutsForProgramId(program.getId());
		
		if (workouts.isEmpty()) {
			return "Currently no workouts in this program";
		} else {
			String returnString = "<ul>";
			for (Workout w : workouts) {
				returnString = returnString + "<li>" + w.getName() + "</li>";
			}
			return returnString + "</ul>";
		}
	}
	
	public static String getProgramName() {
		if (program == null) {
			System.out.println("****ERROR**** programcreationservicelayer program instance not"
					+ "initialized");
			return "error: check console";
		}
		else 
			return program.getName();
	}
}
