<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title></title>
</head>
<body>
<style>
    table,th,td
    {
        border:1px solid black;
    }
</style>

<p>Welcome to library, <c:out value="${sessionScope.user.name}"></c:out></p>
<p>Books can take:</p>
<table>
    <tbody>
    <tr><th>ID</th><th>Author</th><th>Title</th><th>Amount</th></tr>
<c:forEach items="${sessionScope.allBooks}" var="book">
    <tr><td><c:out value="${book.id}"></c:out></td>
        <td><c:out value="${book.author}"></c:out></td>
        <td><c:out value="${book.title}"></c:out></td>
        <td><c:out value="${book.balance}"></c:out></td>
    </tr>
</c:forEach>
    </tbody>
</table>

<p>
<form name="rentForm" method="POST" action="/rent">
    Enter book number: <input type="text" name="bookId"> <br>
    <input type="submit" value="Rent book">
</form>
</p>

<br>

<p>Books in your lease:</p>
<table>
    <tbody>
    <tr><th>ID</th><th>Author</th><th>Title</th></tr>
    <c:forEach items="${sessionScope.rentBooks}" var="book">
        <tr><td><c:out value="${book.id}"></c:out></td>
            <td><c:out value="${book.author}"></c:out></td>
            <td><c:out value="${book.title}"></c:out></td></tr>
    </c:forEach>
    </tbody>
</table>
<p>
<form name="returnForm" method="POST" action="/return">
    Enter book number: <input type="text" name="bookId"> <br>
    <input type="submit" value="Return book">
</form>
</p>

<br>
<br>
<form name="logoutForm" method="get" action="/logout">
    <input type="submit" value="Logout" />
</form>

</body>
</html>
