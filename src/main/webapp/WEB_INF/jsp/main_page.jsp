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

    <h2>Users</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Second Name</th>
            <th>Was created at</th>
        </tr>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td class="id">${user.id}</td>
                <td class="first_name">${user.firstName}</td>
                <td class="last_name">${user.lastName}</td>
                <td class="created_at">${user.createdAt}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/delete_user">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="submit" name="delete" value="Delete"/>
                            <%--<td><a href="${pageContext.request.contextPath}/delete_user?id=${user.id}">Delete</a></td>--%>

                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/update_user_page">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="submit" name="update" value="Update"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <td>
            <form method="get" action="${pageContext.request.contextPath}/new_user">
                <input type="submit" name="createUser" value="Create New User"/>
            </form>
        </td>
    </table>

</div>
</body>

</html>

