<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Program Creation</title>
</head>
<body>
Below are your created programs:<br><br>
<%= Patterns.ServiceLayers.ProgramDisplayServiceLayer.test() %>
<br>
<form action="ProgramManagementServlet" method="post">
<input type="submit" name="newProgram" value="Create New Program"> <br>
<input type="submit" name="test" value="Test Workout Creation for Program">
</form>

</body>
</html>