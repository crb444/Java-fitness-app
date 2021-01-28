package Business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Patterns.IdentityMap;

public class Program {
	private int programId; 
	private String name; 
	private ArrayList<Workout> workouts; 
	
	public Program (int programId, String name, ArrayList<Workout> workouts) {
		this.name = name; 
		this.workouts = workouts;
		this.programId = programId;
		//finder(programId); 
	} 
	
	public Program() {
		
	}
	
	//identity field pattern
	/*
	public void finder(int programId) {
		String sql = "SELECT id FROM program WHERE name=" + getName();  
		PreparedStatement stmt = DBConnection.prepare(sql);
		try {
			ResultSet rs = stmt.executeQuery(); 
			if(rs != null) {
				int dbId = rs.getInt(1);
				this.programId = dbId; 
			} else {
				this.programId = programId;  
			}
		} catch (SQLException e) {
			System.out.println(e); 
		}
	}  
	*/
	/*
	//identity map pattern 
	public Program finderSingleton(int id) {
		Program pg = new Program(); 
		IdentityMap<Program> map = IdentityMap.getInstance(pg); 
		pg = map.get(id); 
		
		if(pg == null) {
			Program prog = new Program();
			Record record = new Record(gateway.find(id));  
			prog.setId(record.getId()); 
			prog.setName(record.getName());
			prog.setWorkouts(record.getWorkouts());
			map.put(id, prog);
			return prog;
		} else  {
			return pg;
		}
	}
	*/
	
	public void setId(int id) {
		this.programId = id; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public void setWorkouts(ArrayList<Workout> wo) {
		this.workouts = wo; 
	}
	
	public String getName() {
		return name; 
	}
	
	public ArrayList<Workout> getWorkouts() {
		return workouts; 
	}
	
	public int getId() {
		return programId; 
	}

}
