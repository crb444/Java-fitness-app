<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Exercises for Workout</title>
</head>
<body>
This workout contains the following exercises: <br>
<%= Patterns.ServiceLayers.WorkoutDetailServiceLayer.populateListWithWorkoutComponents() %>
<br>
<a href="ClientProfile.jsp">Return to Client Profile Page</a>
</body>
</html>