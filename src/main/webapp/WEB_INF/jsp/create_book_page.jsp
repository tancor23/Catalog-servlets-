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
        <form method="post" action="${pageContext.request.contextPath}/create_new_book">
            Name of Book: <label><input type="text" name="bookName" required/></label><br>
            Name of Author: <label><input type="text" name="authorName" required/></label><br>
            Count of Page: <label><input type="text" name="countOfPage" required/></label><br>
            <input type="submit" value="Add">
        </form>
    </div>
</div>
</body>

</html>

