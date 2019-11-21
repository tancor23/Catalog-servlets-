<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Book-User</title>
    <meta charset="utf-8">
</head>
<body>

<div id="container">
    <div class="bookUserForm">
        <h1>The book '${requestScope.bookName}' is has: </h1>
        <c:choose>
            <c:when test="${requestScope.bookUserSize=='0'}">
                <h1>No one</h1>
                <br />
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.bookUsers}" var="bookUserName">
                    <h1> - '${bookUserName}' user</h1>
                </c:forEach>
                <br />
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div>
    <form method="get" action="${pageContext.request.contextPath}/start">
        <input type="submit" name="back" value="Back"/>
    </form>
</div>

</body>

</html>

