package Business;

public class WorkoutComponent {
	int sets;
	int reps;
	Exercise exercise; //TODO: Change this to an exercise object
	
	public WorkoutComponent(int sets, int reps, String exerciseIn) {
		this.sets = sets;
		this.reps = reps;
		if (!Exercise.exerciseMap.containsKey(exerciseIn)) {
			System.out.println("****ERROR: Exercise you're trying to add doesn't exist in the"
					+ "exercise Hash Map.");
			return;
			
		}
		this.exercise = Exercise.exerciseMap.get(exerciseIn);
	}
	
	public String getExerciseName() {
		return exercise.getName();
	}
	
	public Exercise getExercise() {
		return exercise;
	}
	
	public int getSets() {
		return sets;
	}
	
	public int getReps() {
		return reps;
	}
}
