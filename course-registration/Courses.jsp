<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:forEach var="course" items="${courses}">
  ${course.name} - ${course.instructor}
  <form method="post" action="/register/${course.course_id}">
    <input type="submit" value="Register" />
  </form>
</c:forEach>

</head>
<body>

</body>
</html>