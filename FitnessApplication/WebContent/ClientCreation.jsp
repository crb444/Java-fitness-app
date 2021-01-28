<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client Creation</title>
</head>
<body>
<h3>Client Creation Page</h3>

<br>
<br>
<form action="ClientCreationServlet" method="post">
  First Name<br>
  <input type="text" name="firstName"><br>
  Second Name<br>
  <input type="text" name="lastName"><br>
  Goal <br>
  <select name="goal" method = "post">
  <%=new Patterns.ServiceLayers.ClientDataServiceLayer().populateDropDownWithAllGoals()%>
  </select> <br>
  Contact Number <br>
  <input type="text" name="contactnumber"><br><br>
  <input type="submit" value="Submit">
</form>
<br>
<a href="ListClients.jsp">Back to Client List</a>
</body>
</html>