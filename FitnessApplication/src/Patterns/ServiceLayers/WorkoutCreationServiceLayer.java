package Patterns.ServiceLayers;
import java.util.ArrayList;

import Business.Exercise;
import Business.TargetMuscleGroup;
import Business.Workout;
import Business.WorkoutComponent;
import LogicServlets.WorkoutCreationServlet;
import Patterns.IdentityMap;
import Patterns.UnitOfWork;
import Patterns.Mappers.ExerciseMapper;
import Patterns.Mappers.WorkoutMapper;
import Utility.StringUtilityFunctions;
public class WorkoutCreationServiceLayer {
	static ArrayList<WorkoutComponent> workoutComponents=workoutComponents = new ArrayList<WorkoutComponent>();
	public static int currentClient;
	public WorkoutCreationServiceLayer() {
		Exercise.loadExercises();
	}
	
	public static String getWorkoutSummary() {
		String summary;
		if (workoutComponents.isEmpty()) {
			summary = "Workout currently contains no exercises";
			return summary;
		}
		else {
			summary = "Workout contains the following exercise components: <br><ul>";
			for (WorkoutComponent wc : workoutComponents) {
				summary = summary + "<li>" + wc.getSets() + "x" + wc.getReps() +
						" " + wc.getExerciseName() + "</li>";
			}
			summary = summary + "</ul>";
			return summary;
			
		}
	}
	
	public static ArrayList<WorkoutComponent> getWorkoutComponents() {
		return workoutComponents;
	}
	
	public static String populateDropDownWithAllTargetMuscleGroups() {
		String result = "";
		
		for (TargetMuscleGroup targetMuscleGroup : TargetMuscleGroup.values()) {
			result = result + "<option>" + StringUtilityFunctions.lowerCaseAllButFirstCharacter
					(targetMuscleGroup.name()) +
					"</option>";
		}
		
		return result;
	}
	
	public static void conductWorkoutCreation(String workoutName, String workoutTargetMuscleGroup) {
		int workoutIdToAdd = WorkoutMapper.getNextWorkoutIdFromDatabase();
		int currentClientID = WorkoutCreationServlet.currentClientID;
		IdentityMap identityMap = UnitOfWork.getIdentityMap();
		Workout workout = new Workout(workoutIdToAdd, workoutName,
				TargetMuscleGroup.valueOf(workoutTargetMuscleGroup.toUpperCase()), workoutComponents,
				currentClientID);
		UnitOfWork.registerNew(workout);
		identityMap.put(workoutIdToAdd, workout);
		UnitOfWork.commitChanges();
		//UnitOfWork.commitChanges();
		System.out.println("The current client ID is: " + currentClientID);
		
	}
	
}
