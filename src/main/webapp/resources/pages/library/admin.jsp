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
<p>Archive</p>
<table>
    <tbody>
    <tr><th>ID</th><th>Author</th><th>Title</th><th>User name</th><th>Rent date</th><th>Return date</th></tr>
    <c:forEach items="${sessionScope.reports}" var="report">
        <tr><td><c:out value="${report.id}"></c:out></td>
            <td><c:out value="${report.author}"></c:out></td>
            <td><c:out value="${report.title}"></c:out></td>
            <td><c:out value="${report.userName}"></c:out></td>
            <td><c:out value="${report.rentDate}"></c:out></td>
            <td><c:out value="${report.returnDate}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Books</p>
<table>
    <tbody>
    <tr><th>ID</th><th>Author</th><th>Title</th><th>Balance</th></tr>
    <c:forEach items="${sessionScope.books}" var="book">
        <tr><td><c:out value="${book.id}"></c:out></td>
            <td><c:out value="${book.author}"></c:out></td>
            <td><c:out value="${book.title}"></c:out></td>
            <td><c:out value="${book.balance}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form name="bookDeleteForm" method="POST" action="/book_delete">
    Enter book number: <input type="text" name="bookId"> <br>
    <input type="submit" value="Delete book">

</form>
<form name="addBookForm" method="POST" action="resources/pages/library/add_book.jsp">
    <input type="submit" value="Add book">
</form>

<p>Users</p>
<table>
    <tbody>
    <tr><th>ID</th><th>Name</th><th>Login</th><th>Password</th><th>Birthday</th></tr>
    <c:forEach items="${sessionScope.users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.password}"></c:out></td>
            <td><c:out value="${user.birthday}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<form name="refreshForm" method="GET" action="/admin">
    <input type="button" value="Refresh Page" onClick="document.location.reload(true)">
</form>

<form name="logoutForm" method="get" action="/logout">
    <input type="submit" value="Logout" />
</form>

</body>
</html>
