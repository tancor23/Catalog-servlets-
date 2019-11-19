<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>ITRex Group. Servlets. Catalog</title>
    <meta charset="utf-8">
</head>
<body>

<div id="container">

    <div id="menu">
        <div class="menuUserAction">
<%--            <p><a href="/newNotice">Add new flat</a></p>
            <p><a href="/controller?command=EDIT_USER_PROFILE">Edit profile</a></p>--%>
        </div>
    </div>

    <div id="content">
        <c:forEach items="${requestScope.users}" var="users">
            <div class="id">
                <c:out value="${users.id}"/>
            </div>
            <div class="first_name">
                <c:out value="${users.firstName}"/>
            </div>
            <div class="last_name">
                <c:out value="${users.lastName}"/>
            </div>
            <div class="created_at">
                <c:out value="${users.createdAt}"/>
            </div>
        </c:forEach>

        </div>

        <div id="rightBlock">
        </div>

        <div class="clear"></div>

    </div>
</div>
</body>

</html>