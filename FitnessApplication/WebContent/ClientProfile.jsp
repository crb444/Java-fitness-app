<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client Profile</title>
</head>
<body>
<h3>Client Profile Page</h3>
<br>
<form action="WorkoutManagementServlet" method="post">
<%=new Patterns.ServiceLayers.ClientProfileServiceLayer().showClientInformation()%>
</form>
<br>
<form action="ClientProfileServlet" method="post">

<input type="submit" name="ClientProfileAddWorkout" value="Add New Workout">

</form>
<br>
<a href="ListClients.jsp">Back to Client List</a>
</body>
</html>