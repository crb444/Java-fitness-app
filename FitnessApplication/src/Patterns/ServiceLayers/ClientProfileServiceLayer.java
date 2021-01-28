package Patterns.ServiceLayers;

import Objects.Client;
import Patterns.IdentityMap;
import Patterns.UnitOfWork;
import Patterns.Mappers.WorkoutMapper;

import java.util.ArrayList;

import Business.Exercise;
import Business.TargetMuscleGroup;
import Business.Workout;

public class ClientProfileServiceLayer {
	public static Client client;
	public static ArrayList<Workout> clientWorkouts;
	
	
	public String showClientInformation() {
		Exercise.loadExercises();
		if (client == null) {
			return "<h2>No Client has been selected. Please select a client from the client list</h2>";
		}
		else {
			System.out.println("CPSL client id is:" + client.getClientId());
			ArrayList<Workout> workouts = WorkoutMapper.getWorkoutsForGivenClientId(client.getClientId());
			clientWorkouts = workouts;
			String workoutString;
			if (workouts.isEmpty()) {
				workoutString = "Client currently does not have any workouts";
			} else {
				workoutString = "<ul>";
				for (Workout w : workouts) {
					workoutString = workoutString + "<li>" + "Name: " + w.getName() + ","
							+ " Target Muscle Group: " + w.getTargetMuscleGroup() +
							getWorkoutRemoveButtonString(w) +
							getWorkoutViewButtonString(w) 
							
							+ "</li>";
				}
				workoutString = workoutString + "</ul>";
			}

			return "First Name: " + client.getFirstName() + "<br>" +
					"Last Name:  " + client.getLastName() + "<br>" + 
					"Goal: " + client.getGoal() + "<br>" +
					"Contact Number: "  + client.getContactNumber() + "<br>" +
					"Workouts: " + workoutString;
		}
	}
	
	public String getWorkoutRemoveButtonString(Workout workout) {
		return " <input type=\"submit\" name=\"" + workout.getId()
		+ "REMOVE" + "\" value=\"Remove Workout\" />";
	}
	public String getWorkoutViewButtonString(Workout workout) {
		return " <input type=\"submit\" name=\"" + workout.getId()
		+ "VIEW" + "\" value=\"View Workout\" />";
	}
	
	public static void conductWorkoutRemoval(Workout workout) {
		IdentityMap identityMap = UnitOfWork.getIdentityMap();
		UnitOfWork.registerDelete(workout);
		identityMap.remove(workout.getId());
		UnitOfWork.commitChanges();
	}
}
