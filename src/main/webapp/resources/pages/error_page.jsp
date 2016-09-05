<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <title></title>
</head>
<body>
<h2>Error: <c:out value="${requestScope.errorMessage}"></c:out></h2>
<form name="errForm" method="GET" action="${requestScope.redirectUrl}">
    <input type="submit" value="refresh">
</form>
</body>
</html>