<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
    <meta charset="utf-8">
</head>
<body>

<div id="container">
    <div class="createUserForm">
        <form method="post" action="${pageContext.request.contextPath}/create_user">
            First name: <label><input type="text" name="firstName"></label><br>
            Last name: <label><input type="text" name="lastName"></label><br>
            <input type="submit" value="Add">
        </form>
    </div>
</div>
</body>

</html>

