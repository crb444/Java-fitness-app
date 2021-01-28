package Patterns.ServiceLayers;

import java.util.ArrayList;

import Business.Program;
import Business.Workout;
import Patterns.IdentityMap;
import Patterns.UnitOfWork;
import Patterns.Mappers.ProgramMapper;

public class ProgramDisplayServiceLayer {
	public static String test() {
		
		IdentityMap identityMap = UnitOfWork.getIdentityMap();
		String result = "";
		ArrayList<Program> programs = ProgramMapper.getAllProgramsForTrainer(3);
		for (Program p : programs) {
			UnitOfWork.registerAsClean(p);
			System.out.println("program name - " + p.getName());
			result = result + p.getName() + ":<ul>" ;
			for (Workout w : p.getWorkouts()) {
				result = result + "<li>";
				System.out.println("has workout name - " + w.getName());
				result = result + w.getName();
				result = result + "</li>";
			}
			result = result + "</ul>";
		}
		return result;
		
	}
}
