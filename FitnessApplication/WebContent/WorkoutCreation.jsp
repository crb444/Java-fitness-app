<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Workout Creation</title>
</head>
<body>
<h3>Workout Creation Page</h3>

<br>
<br>


  <%=new Patterns.ServiceLayers.WorkoutCreationServiceLayer().getWorkoutSummary()%>
  <br><br>
  <form action="ExerciseSpecificationServlet" method="post">
	  Exercise 1 <br>
	  <select name="exerciseName" method = "post">
	  <%=new Patterns.ServiceLayers.ExerciseDataServiceLayer().populateDropDownWithAllExercises()%>
	  </select>
	  <br>
	  Sets <br>
	  <input type="text" name="exerciseSets"><br>
	  Reps <br>
	  <input type="text" name="exerciseReps"><br>
	  <br>
	  <input type="submit" value="Add Exercise to Workout">
	  
  </form>
  <br>
  <form action="WorkoutCreationServlet" method="post">
  Workout Name<br>
  <input type="text" name="workoutName">
  <br>
  Workout Target Muscle Group <br>
  <select name="workoutTargetMuscleGroup" method="post">
  <%= Patterns.ServiceLayers.WorkoutCreationServiceLayer.populateDropDownWithAllTargetMuscleGroups() %>
  </select>
  <br><br>
  <input type="submit" value="Submit Workout">
</form>
<br>
<a href="ListClients.jsp">Back to Client List</a>
</body>
</html>