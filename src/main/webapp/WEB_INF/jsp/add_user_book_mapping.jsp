<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String message = (String) request.getAttribute("alert");%>

<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/shortcut_icon.png">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/shortcut_icon.png">
    <title>User-Book Mapping</title>
    <meta charset="utf-8">
    <script type="text/javascript">
        var msg = "<%=message%>";
        if (msg !== 'null') {
            alert(msg);
        }
    </script>
</head>
<body>


<div id="container">
    <div class="userBookMappingForm">
        <h1>User-Book Mapping</h1>
        <form method="post" action="${pageContext.request.contextPath}/user_book/add_mapping">
            Select User(s):&nbsp;
            <label>
                <select name="userSelect" required>
                    <c:forEach items="${requestScope.users}" var="user">
                        <option value="${user.id}">${user.firstName} ${user.lastName}</option>
                    </c:forEach>
                </select>
            </label>
            <br/>
            <br/>
            Select Book(s):&nbsp;
            <label>
                <select name="bookSelect" required>
                    <c:forEach items="${requestScope.books}" var="book">
                        <option value="${book.id}">${book.name}</option>
                    </c:forEach>
                </select>
            </label>
            <br/>
            <br/>
            <input type="submit" value="Associate"/>
        </form>
    </div>
</div>
<div>
    <br/>
    <br/>
    <form method="get" action="${pageContext.request.contextPath}/start">
        <input type="submit" name="back" value="Back"/>
    </form>
</div>

</body>

</html>

