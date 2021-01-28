package Patterns.Mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Business.Exercise;
import Business.FitnessGoal;
import Database.DBConnection;
import Objects.Client;

public class ExerciseMapper {
	
	static ArrayList<Exercise> exerciseList;
	
	public static ArrayList<Exercise> getAllExercises() {
    	ArrayList<Exercise> exercises = new ArrayList<>();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM exercise");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Exercise exercise = getExerciseInstanceFromResultSet(rs);
				//System.out.println(client.getLastName());
				
				exercises.add(exercise);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*System.out.println("Get all exercises test:");
		for (Exercise e : exercises) {
			System.out.println(e.getName());
		}*/
		System.out.println("exerciseslist szie is" + exercises.size());
		exerciseList = exercises;
		return exercises;
	}
	
	public static Exercise getExerciseInstanceFromResultSet(ResultSet rs) throws SQLException {
		Exercise exercise = new Exercise();
		exercise.setId(rs.getInt(1));
		exercise.setName(rs.getString(2));
		exercise.setTargetMuscle(rs.getString(3));
		
		return exercise;
	}
	
	public static ArrayList<Exercise> getExerciseList() {
		return exerciseList;
	}
}
