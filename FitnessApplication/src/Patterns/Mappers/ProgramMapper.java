package Patterns.Mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Business.FitnessGoal;
import Business.Program;
import Business.Workout;
import Business.WorkoutComponent;
import Database.DBConnection;
import Objects.Client;
import Utility.GlobalAttributes;

public class ProgramMapper {
	public static ArrayList<Program> getAllProgramsForTrainer(int trainerID) {
		ArrayList<Program> programs = new ArrayList<>();
		boolean found = false;
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM program WHERE trainerID=" +
					trainerID + ";");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("rs next was true once");
				try {
				//System.out.println("getClientFromId() method: Client name is" + rs.getString(2));
				} catch (NullPointerException e) {
					System.out.println("****ERROR***** Null pointer exception :( ProgramMapper getworkout ");
				}
				programs.add(createProgramInstanceFromResultSet(rs));
				found = true;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (found == false) {
			System.out.println("***WARNING**** no programs found for the given trainerID ");
		}
		
		return programs;
	}
	
	public static Program createProgramInstanceFromResultSet(ResultSet rs) throws SQLException {
		String name = rs.getString(3);
		int programId = rs.getInt(1);
		ArrayList<Workout> workouts = getWorkoutsForProgramId(programId);
		Program program = new Program(programId, name, workouts);
		// may have concurrent read issues
		return program;
		
	}
	
	public static ArrayList<Workout> getWorkoutsForProgramId(int programId) {
    	ArrayList<Workout> workouts = new ArrayList<>();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM workout"
					+ " WHERE programID=" + programId + ";");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Workout workout = WorkoutMapper.getWorkoutInstanceFromResultSet(rs);
				//System.out.println(client.getLastName());
				workouts.add(workout);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//allWorkouts = workouts
		for (Workout w : workouts) {
			System.out.println("***1234****being returned from datbase method w with ex name" + w.getName());
		}
		return workouts;
	}
	
	public static int getNextProgramIdFromDatabase() {
    	int largestId=0;
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM program");
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
		
		System.out.println("**TEST** from ProgramMapper get next workout id, largest id is " + largestId);
		return largestId + 1;
	}
	
	public static void addProgramToDatabase(Program program) {
		int id = program.getId();
		int trainerID = GlobalAttributes.trainerID;
		String name = program.getName();
		
		String sqlStatement = "INSERT INTO program VALUES"
				+ " (" + id +  ", " + trainerID + ", '"
				+ name  + "');" + "COMMIT;";
		System.out.println("The sql statement is: " + sqlStatement);
		try {
			PreparedStatement s = DBConnection.prepare(sqlStatement);
			s.executeUpdate();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
