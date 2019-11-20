<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Book</title>
    <meta charset="utf-8">
</head>
<body>

<div id="container">
    <div class="createBookForm">
        <form method="post" action="${pageContext.request.contextPath}/update_book">
            <input type="hidden" name="bookId" value="${requestScope.bookId}"/>
            First name: <label><input type="text" name="bookName"></label><br>
            Last name: <label><input type="text" name="authorName"></label><br>
            Last name: <label><input type="text" name="countOfPage"></label><br>
            <input type="submit" value="Update">
        </form>
    </div>
</div>
</body>

</html>

