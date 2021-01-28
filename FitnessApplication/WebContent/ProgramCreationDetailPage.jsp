<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Program Creation</title>
</head>
<body>
Program (<%= Patterns.ServiceLayers.ProgramCreationServiceLayer.getProgramName() %>)
 contains the following workouts:
 <%= Patterns.ServiceLayers.ProgramCreationServiceLayer.displayWorkoutsForProgram() %> 
 <br><br>
 <form action="ProgramCreationServlet" method="post">
 	<input type="submit" name="newWorkout" value="Add Workout"> <br>
 	<input type="submit" name="submitProgram" value="Submit Program">
 </form>

</body>
</html>