<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="message" value="${message}"/>
<html>
<head>
  <title>SignUp</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/libs/bootstrap/css/bootstrap.css"/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value="/stylesheet/style.css"/>"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="<c:url value="/libs/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>
<div class="center-block">
  <div class="formContainer">
    <form class="form" method="post" action="/registration">
      <div class="form-group">
        <p>
          <label for="name">Name:</label>
          <input type="text" name="name" id="name" required/>
        </p>
        <p>
          <label for="login">Login:</label>
          <input type="text" name="login" id="login" required/>
          <br/>
        </p>
        <p>
          <label for="password">Password:</label>
          <input type="password" name="password" id="password" required/>
        </p>
        <p>
          <label for="birthday">Birthday:</label>
          <input type="date" name="birthday" id="birthday" required/>
        </p>
        <div class="error"><c:out value="${message}"/></div>
        <input class="btn btn-default" type="submit" value="Registration"/>
        <p>Already have an account? <a href="/index.jsp">Log in</a></p>
      </div>
    </form>
  </div>
</div>
</body>
</html>