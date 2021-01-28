<%@ page import="Security.AppSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Shop</title>
</head>
<body>
<h2>Book Shop</h2>

<% if (!AppSession.isAuthenticated()) {%>

<form action="ClientLoginControllerServlet" method="post">
	<input type="text" name="username">
	<input type="password" name="password">
	<input type="submit" value="Login">
</form>
<div>
	<a href="signup">Sign Up</a>
</div>

<% } else { %>

You are already logged in as <%=AppSession.getUser().getUsername() %>
<div>
	Link to go shopping
</div>

<%} %>
</body>
</html>