<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>User-Book</title>
    <meta charset="utf-8">
</head>
<body>

<div id="container">
    <div class="createBookForm">
        <h1>User '${requestScope.firstName} ${requestScope.lastName}' has: </h1>
        <c:choose>
            <c:when test="${requestScope.userBookNameSize=='0'}">
                <h1>No one book</h1>
                <br />
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.userBookNames}" var="userBookName">
                    <h1> - '${userBookName}' book</h1>
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

