package Business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import Patterns.IdentityMap;
import Patterns.Mappers.ExerciseMapper;


public class Exercise { 
	private int exerciseId; 
	private String name; 
	private String targetMuscle;
	private TargetMuscleGroup targetMuscleGroup; 
	private int repetitions; 
	
	public static Map<String, Exercise> exerciseMap = new HashMap<String, Exercise>();
	
	// ADD TARGET MUSCLE GROUP INTO SETTERS AND GETTERS
	
	//TODO:Add target muscle group retrieval from database + assignment
	
	public static void loadExercises() {
		System.out.println("Load exercises is executing");
		ArrayList<Exercise> exerciseList;
		if (ExerciseMapper.getExerciseList() == null) {
			System.out.println("exec1");
			exerciseList = ExerciseMapper.getAllExercises();
		} else {
			System.out.println("exec2");
			exerciseList = ExerciseMapper.getExerciseList();
		}
		for (Exercise e : exerciseList) {
			if (!exerciseMap.containsValue(e)) {
				exerciseMap.put(e.getName(), e);
				System.out.println(e.getName() + " added to Exercise hash map");
			}
		}
	}
	
	public Exercise() {
		
	}
	
	public Exercise(int exerciseId, String name, String targetMuscle, int repititions) {
		this.name = name; 
		this.targetMuscle = targetMuscle; 
		this.repetitions = repititions; 
		//finder(exerciseId);  
		//TODO: Implement identity field again, perhaps with a simpler method
	}  
	
//	//identity map pattern 
//	public Exercise finderSingleton(int id) {
//		Exercise ex = new Exercise(); 
//		IdentityMap<Exercise> map = IdentityMap.getInstance(ex); 
//		ex = map.get(id); 
//		
//		if(ex == null) {
//			Exercise ex2 = new Exercise();
//			Record record = new Record(gateway.find(id));  
//			ex2.setId(record.getId()); 
//			ex2.setName(record.getName());
//			ex2.setTargetMuscle(record.getTargetMuscle());
//			ex2.setTargetMuscleGroup(record.getTargetMuscleGroup());
//			map.put(id, ex2);	
//			return ex2;
//		} else {
//			return ex; 
//		}
//	}
	
	//identity field pattern
	public void finder(int exerciseId) { /*
		String sql = "SELECT id FROM exercise WHERE name=" + getName() + " AND targetMuscleGroup=" + getTargetMuscleGroup(); 
		PreparedStatement stmt = DBConnection.prepare(sql);
		try {
			ResultSet rs = stmt.executeQuery(); 
			if(rs != null) {
				int dbId = rs.getInt(1);
				this.exerciseId = dbId; 
			} else {
				this.exerciseId = exerciseId;  
			}
		} catch (SQLException e) {
			System.out.println(e); 
		} */
	} 
	
	public int getExerciseId() {
		return exerciseId; 
	} 
	
	
	/*
	public String getTargetMuscle() {  
		if(this.targetMuscle == null) {
			Exercise ex = gateway.find(this.id); 
			this.targetMuscle = ex.getTargetMuscle(); 
		}
		return targetMuscle; 
	}
	
	public int getRepititions() { 
		if(this.repititions == 0) {
			Exercise ex = gateway.find(this.id); 
			this.repititions = ex.getRepititions(); 
		}
		return repititions; 
	}
*/
	public int getID() {
		return exerciseId; 
	} 
	
	public String getName() {
		return name;
	}
	
	public TargetMuscleGroup getTargetMuscleGroup() {
		return targetMuscleGroup; 
	}
	
	public void setId(int id) {
		this.exerciseId = id; 
	} 
	
	public void setName(String name) {
		this.name = name; 
	} 
	
	public void setTargetMuscle(String targetMuscle) {
		this.targetMuscle = targetMuscle; 
	} 
	
	public void setTargetMuscleGroup(TargetMuscleGroup tmg) {
		this.targetMuscleGroup = tmg; 
	} 
	
	public void setRepititions(int rep) {
		this.repetitions = rep; 
	}

}
