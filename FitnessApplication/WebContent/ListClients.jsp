<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client List</title>
</head>
<body>
Hello trainer! Below are all of your registered clients <br>
<form action="ClientManagementServlet" method="post">
Clients:<br> <%=new Patterns.ServiceLayers.ClientDataServiceLayer().displayAllClientsAsList()%>
</form>
<br>
<a href="ClientCreation.jsp">Add New Client</a><br>
<a href="ListPrograms.jsp">View My Programs</a>
</body>
</html>