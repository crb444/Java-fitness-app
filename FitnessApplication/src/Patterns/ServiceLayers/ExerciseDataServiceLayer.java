package Patterns.ServiceLayers;

import java.util.ArrayList;

import Business.Exercise;
import Patterns.Mappers.ExerciseMapper;

public class ExerciseDataServiceLayer {
	
	public ExerciseDataServiceLayer() {
		//Load all of the exercise data from the server into the Exercise HashMap
	}
	
	public String populateDropDownWithAllExercises() {
		ArrayList<Exercise> exercises = ExerciseMapper.getAllExercises();
		String result = "";
		
		for (Exercise e : exercises) {
			result = result + "<option>" + e.getName() + "</option>";
		}
		
		return result;
	}
}
