<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/shortcut_icon.png">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/shortcut_icon.png">
    <title>Create Book</title>
    <meta charset="utf-8">
</head>
<body>

<div id="container">
    <div class="createBookForm">
        <form method="post" action="${pageContext.request.contextPath}/book/create">
            Name of Book: <label><input type="text" name="bookName" required/></label><br>
            Name of Author: <label><input type="text" name="authorName" required/></label><br>
            Count of Page: <label><input type="text" name="countOfPage" required/></label><br>
            <input type="submit" value="Add">
        </form>
    </div>
</div>
<br>
<div>
    <form method="get" action="${pageContext.request.contextPath}/start">
        <input type="submit" name="back" value="Back"/>
    </form>
</div>
</body>

</html>

