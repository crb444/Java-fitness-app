package Patterns;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Business.Exercise;
import Business.Workout; 

//This class is used in conjunction with the identityMap pattern

public class Finder {
	/*
	public IdentityMap im; 

	
	public Finder() {
		im = new IdentityMap(); 
	}
	
	public Exercise findExercise(int key) {
		Exercise w = im.getExercise(key); 
		
		if(w == null) {
			String sqlStatement = "SELECT * from Exercise WHERE id=" +key; 
			PreparedStatement stmt = DBConnection.prepare(sqlStatement); 
			try {
				ResultSet rs = stmt.executeQuery(); 
				String name = rs.getString(2);
				String targetMuscle = rs.getString(3);
				int repititions = rs.getInt(4);
				
				Exercise fromDb = new Exercise(name, targetMuscle, repititions);
				im.addExercise(fromDb);
			} catch (SQLException e) {
				System.out.println(e); 
			}	
		
		}
		
		return w; 
		
	}
*/
}
