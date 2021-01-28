package Utility;

public class GlobalAttributes {
	public static int trainerID;
	
	// when this vaiable is -1 when workouts are being created, it will be created directly
	// for clients, with a programID of null. If otherwise, the value of this variable 
	// corresponds to the program ID for which to create a new workout
	public static int workoutCreationTarget = -1;
	
}
