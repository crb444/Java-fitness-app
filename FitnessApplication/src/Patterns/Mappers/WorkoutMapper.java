package Patterns.Mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Business.Exercise;
import Business.TargetMuscleGroup;
import Business.Workout;
import Business.WorkoutComponent;
import Database.DBConnection;
import Objects.Client;
import Utility.GlobalAttributes;

public class WorkoutMapper {
	
	
	public static int getNextWorkoutIdFromDatabase() {
    	int largestId=0;
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM workout");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int nextId = rs.getInt(1);
				if (nextId > largestId) {
					largestId = nextId;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("**TEST** from WorkoutMapper get next workout id, largest id is " + largestId);
		return largestId + 1;
	}
	
	public static ArrayList<Workout> getWorkoutsForGivenClientId(int clientId) {
    	ArrayList<Workout> workouts = new ArrayList<>();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM workout WHERE memberID=" + clientId + ";");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Workout workout = getWorkoutInstanceFromResultSet(rs);
				//System.out.println(client.getLastName());
				workouts.add(workout);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//allWorkouts = workouts
		return workouts;
	}
	
	public static Workout getWorkoutInstanceFromResultSet(ResultSet rs) throws SQLException {
		int workoutId = rs.getInt(1);
		String workoutName = rs.getString(2);
		System.out.println("Workout " + workoutName + " retrieved from database.");
		TargetMuscleGroup targetMuscleGroup = TargetMuscleGroup.valueOf(rs.getString(3));
		int clientId = rs.getInt(4);
		//TODO: actually get the workout components
		return new Workout(workoutId, workoutName, targetMuscleGroup, new ArrayList<WorkoutComponent>(),
				clientId);
		
	}
	
	public static void addNewWorkoutToClient(Workout workout) {
		
		String id = Integer.toString(workout.getId());
		String name = workout.getName();
		String targetMuscleGroup = workout.getTargetMuscleGroup().name();
		String clientId = Integer.toString(workout.getClientId());
		String sqlStatement;
		// programID will be set to 0 since this workout doesn't correspond to a program
		if (GlobalAttributes.workoutCreationTarget == -1) {
			sqlStatement = "INSERT INTO workout VALUES"
					+ " (" + id +  ", '" + name + "', '"
					+ targetMuscleGroup + "', " + clientId  + ", NULL);" + "COMMIT;";
		} else {
			sqlStatement = "INSERT INTO workout VALUES"
					+ " (" + id +  ", '" + name + "', '"
					+ targetMuscleGroup + "', " + "NULL"  + ", " +
					GlobalAttributes.workoutCreationTarget + ");" + "COMMIT;";
		}
		System.out.println("The sql statement is: " + sqlStatement);
		try {
			PreparedStatement s = DBConnection.prepare(sqlStatement);
			s.executeUpdate();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getNextWorkoutComponentIdFromDatabase() {
    	int largestId=0;
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM workoutComponent");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int nextId = rs.getInt(1);
				if (nextId > largestId) {
					largestId = nextId;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("**TEST** from WorkoutMapper get next workout component id,"
				+ " largest id is " + largestId);
		return largestId + 1;
	}
	
	public static void addWorkoutComponentToDatabase(int workoutComponentId,
			int workoutId, WorkoutComponent wc) {
		// get the corresponding Exercise ID
		int exerciseId = -1;
		
		for (String exerciseKey : Exercise.exerciseMap.keySet() ) {
			if (exerciseKey.equals(wc.getExerciseName())) {
				exerciseId = wc.getExercise().getID();
			}
		}
		
		if (exerciseId == -1) {
			System.out.println("**ERROR** the appropriate exercise cannot be found in the hash map");
		}
	
		
		String sqlStatement = "INSERT INTO workoutComponent VALUES"
				+ " (" + workoutComponentId +  ", " + exerciseId + ", "
				+ workoutId + ", " + wc.getSets()  + ", " + wc.getReps() + ");" + "COMMIT;";
		System.out.println("The sql statement is: " + sqlStatement);
		try {
			PreparedStatement s = DBConnection.prepare(sqlStatement);
			s.executeUpdate();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void removeWorkoutByWorkoutId(int workoutId) {
		try {
			PreparedStatement s = DBConnection.prepare("DELETE FROM workout WHERE workoutID=" +
			Integer.toString(workoutId) + ";COMMIT;");
			System.out.println("the sql statement is: " + "DELETE FROM workout WHERE workoutID=" +
			Integer.toString(workoutId) + ";COMMIT;");
			s.executeUpdate();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<WorkoutComponent> getWorkoutComponentsWithGivenWorkoutId(int workoutId) {
    	ArrayList<WorkoutComponent> workoutComponents = new ArrayList<>();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM workoutComponent"
					+ " WHERE workoutID=" + workoutId + ";");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				WorkoutComponent workoutComponent = getWorkoutComponentInstanceFromResultSet(rs);
				//System.out.println(client.getLastName());
				workoutComponents.add(workoutComponent);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//allWorkouts = workouts
		for (WorkoutComponent wc : workoutComponents) {
			System.out.println("being returned from datbase method wc with ex name" + wc.getExerciseName());
		}
		return workoutComponents;
	}
	
	public static WorkoutComponent getWorkoutComponentInstanceFromResultSet(ResultSet rs)
			throws SQLException{
		int sets = rs.getInt(4);
		int reps = rs.getInt(5);
		int exerciseId = rs.getInt(2);
		//String exerciseName = rs.getString(3);
		String exerciseName = null;
		
		for (String key : Exercise.exerciseMap.keySet()) {
			if (Exercise.exerciseMap.get(key).getExerciseId() == exerciseId) {
				exerciseName = Exercise.exerciseMap.get(key).getName();
			}
		}
		
		System.out.println("exerciseId from database is" + exerciseId);
		
		if (exerciseName == null) {
			System.out.println("**ERROR, cannot find the corresponding exercise in the hash map");
		}
		
		System.out.println("Component instance created");
		
		return new WorkoutComponent(sets, reps, exerciseName);
		
		
	}
	
	
}
