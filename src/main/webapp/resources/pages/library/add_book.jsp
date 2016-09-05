<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form name="addBookForm" method="POST" action="/add_book">

  Author * <input type="text" name="author"> <br>
  Title * <input type="text" name="title"><br>
  Balance * <input type="text" name="balance"><br>
  <input type="submit" value="Add book">
</form>
</body>
</html>
