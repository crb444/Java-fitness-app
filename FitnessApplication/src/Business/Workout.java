package Business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnection;
import Patterns.IdentityMap;

import Patterns.UnitOfWork; 
import Database.DBConnection;;

public class Workout { 
	private int workoutId; 
	private String workoutName; 
	private TargetMuscleGroup target; 
	private ArrayList<WorkoutComponent> workoutComponents;   
	private int clientId;
	
	
	public Workout(int workoutId, String workoutName,  TargetMuscleGroup target,
			ArrayList<WorkoutComponent> workoutComponents, int clientId) {
		this.target = target; 
		this.workoutComponents = workoutComponents;
		//finder(workoutId);
		this.workoutId = workoutId;
		this.workoutName = workoutName;
		this.clientId = clientId;
		UnitOfWork.registerAsClean(this);
	}  
	
	public Workout() {
		
	}
	
	
	
	//identity field pattern 
	public void finder(int workoutId) {
		String sql = "SELECT id FROM workout WHERE name=" + getName(); 
		
		try {
			PreparedStatement stmt = DBConnection.prepare(sql);
			ResultSet rs = stmt.executeQuery(); 
			if(rs != null) {
				int dbId = rs.getInt(1);
				this.workoutId = dbId; 
			} else {
				this.workoutId = workoutId;  
			}
		} catch (Exception e) {
			System.out.println(e); 
		}
	}  
	
	//association table pattern 
	/*
	public Exercise[] getExercises(int workoutId) {
		String sql = "SELECT exerciseId FROM exercise-workout WHERE workoutId=" +workoutId; 
		PreparedStatement stmt = DBConnection.prepare(sql); 
		try {
			ResultSet rs = stmt.executeQuery(); 
			//EXERCISE MAPPER? 
			Exercise[] exercises = new Exercise[rs.getFetchSize()];
			ExerciseMapper exMapper = new ExerciseMapper(); 
			//change code below
			for(int i =0; i<rs.getFetchSize(); i++) {
				rs.next(); 
				exercises[i] = ExerciseMapper.find(rs.getInt(1)); 
			}
		}
	}
	*/
	public void setId(int id) {
		this.workoutId = id;
	}
	
	public void setName(String name) {
		this.workoutName = name; 
	}
	
	public void setTargetMuscleGroup(TargetMuscleGroup tmg) {
		this.target = tmg; 
	}
	
	public int getWorkoutId() {
		return workoutId; 
	}
	
	public String getName() {
		return workoutName; 
	}
	
	public TargetMuscleGroup getTargetMuscleGroup() {
		return target;
	}
	
	public ArrayList<WorkoutComponent> getWorkoutComponents() {
		return workoutComponents;
	} 
	
	public int getId() {
		return workoutId; 
	}
	
	public void addWorkoutComponent(WorkoutComponent workoutComponent) {
		workoutComponents.add(workoutComponent);
		UnitOfWork.getCurrent().registerAsDirty(this);
	} 
	
	public void removeWorkoutComponent(WorkoutComponent workoutComponent) {
		workoutComponents.remove(workoutComponent);
		UnitOfWork.getCurrent().registerAsDirty(this);
		 
	} 
	
	public void removeWorkout() {
		//UnitOfWork.getCurrent().registerAsDeleted(this); 
	}
	
	public int getClientId() {
		return clientId;
	}

}
