<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Page</title>
</head>
<body>
<h1>You are now viewing this client!</h1> 

<h3>Clients Workouts</h3>
<table style="width:100%">
  <tr>
    <th>Workout Name</th>
    <th>Target Muscle Group</th>  
     <th>View Workout</th>
  </tr>
  <tr>
    <td>Arm Blaster</td>
    <td>Biceps</td>  
    <td align="center" >
    	<a href="ViewWorkout.jsp">
		<input type="button" value="View">
		</a>
	</td> 
  </tr>
  <tr>
    <td>Six Pack Abs</td>
    <td>Abdominals</td> 
   <td align="center" >
    	<a href="ViewWorkout.jsp">
		<input type="button" value="View">
		</a>
	</td> 
  </tr> 
</table> 
<a href="SimpleServlet">
<button type="button" class="btn btn-primary">Primary</button>
</a>
</body>
</html>