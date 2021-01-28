<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Program Creation</title>
</head>
<body>
Enter New Program Name:<br>
<form action="ProgramManagementServlet" method="post">
<input type="text" name="newProgramName"><br>
<input type="submit" name="nameSubmit" value="Submit">
</form>
</body>
</html>