<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign-Up Page</title>
</head>
<body>
	<h2>Signup page</h2>
	<form action ="signup">
   
   User name : <input type="text" name="username" required><br>
   password : <input type="text" name="password" required><br>
   confirm password : <input type="text" name="confirm" required><br>
   <input type="submit" value="signup">
   </form>
   <p>If already a user<a href="login.jsp">Login here</a></p>
</body>
</html>