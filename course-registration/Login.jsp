<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/login">
  Email: <input type="text" name="email" /><br/>
  Password: <input type="password" name="password" /><br/>
  <input type="submit" value="Login" />
  ${error}
</form>

</body>
</html>