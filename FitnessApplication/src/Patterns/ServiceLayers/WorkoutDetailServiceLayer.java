package Patterns.ServiceLayers;

import Business.Workout;
import Business.WorkoutComponent;
import Patterns.Mappers.WorkoutMapper;

public class WorkoutDetailServiceLayer {
	public static Workout currentWorkout;
	
	public static String populateListWithWorkoutComponents() {
		if (currentWorkout == null) {
			System.out.println("Workout current is null");
			return "Workout current is null";
		} else {
			System.out.println("current workout is " + currentWorkout.getName());
			/*for (WorkoutComponent wc : WorkoutMapper
					.getWorkoutComponentsWithGivenWorkoutId(currentWorkout.getId())) {
				System.out.println("**wc0 0" + wc.getExerciseName());
			}*/
			String returnString = "<ul>";
			
			for (WorkoutComponent wc : WorkoutMapper
					.getWorkoutComponentsWithGivenWorkoutId
					(currentWorkout.getId())) {
				
				returnString = returnString + "<li>" +
					wc.getSets() + "x" +
					wc.getReps() + " " + 
					wc.getExerciseName() + "</li>"; 
			}
			
			return returnString + "</ul>"; 
				
		}
	}
}
