<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </div>
    </div>

    <div id="userTable">
        <h2>Users</h2>
        <table title="Users" border="1">
            <tr>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Was created at</th>
                <th colspan="3">Actions</th>
            </tr>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td class="first_name">${user.firstName}</td>
                    <td class="last_name">${user.lastName}</td>
                    <td class="created_at">${user.createdAt}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/delete_user">
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <input type="submit" name="delete" value="Delete"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/update_user_page">
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <input type="hidden" name="firstName" value="${user.firstName}"/>
                            <input type="hidden" name="lastName" value="${user.lastName}"/>
                            <input type="submit" name="update" value="Update"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/user_book_page">
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <input type="submit" name="books" value="Books"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form method="get" action="${pageContext.request.contextPath}/new_user">
            <input type="submit" name="createUser" value="Create New User"/>
        </form>
    </div>

    <div id="bookTable">
        <h2>Books</h2>
        <table border="1" title="Books">
            <tr>
                <th>Name of Book</th>
                <th>Name of Author</th>
                <th>Count of Page</th>
                <th colspan="3">Actions</th>
            </tr>
            <c:forEach items="${requestScope.books}" var="book">
                <tr>
                    <td class="book_name">${book.name}</td>
                    <td class="author_name">${book.author}</td>
                    <td class="count_of_page">${book.page}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/delete_book">
                            <input type="hidden" name="bookId" value="${book.id}"/>
                            <input type="submit" name="delete" value="Delete"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/update_book_page">
                            <input type="hidden" name="bookId" value="${book.id}"/>
                            <input type="hidden" name="bookName" value="${book.name}"/>
                            <input type="hidden" name="authorName" value="${book.author}"/>
                            <input type="hidden" name="countOfPage" value="${book.page}"/>
                            <input type="submit" name="update" value="Update"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/book_user_page">
                            <input type="hidden" name="bookId" value="${book.id}"/>
                            <input type="submit" name="users" value="Users"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form method="get" action="${pageContext.request.contextPath}/new_book">
            <input type="submit" name="createUser" value="Create New Book"/>
        </form>
    </div>
</div>
</body>

</html>

