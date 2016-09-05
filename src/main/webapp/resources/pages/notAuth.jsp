<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <title></title>
</head>
<body>
You set incorrect AUTH data, try again.
<a href="<c:url value="/index.jsp"/>">AUTH</a> or <a href="<c:url value="/registration"/>"> register </a>
</body>
</html>