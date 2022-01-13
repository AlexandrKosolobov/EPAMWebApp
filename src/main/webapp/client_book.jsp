<%@ page import="by.kosolobov.barbershop.data.dao.BookDao" %>
<%@ page import="by.kosolobov.barbershop.entity.Book" %>
<%@ page import="java.util.Deque" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="pag" uri="/WEB-INF/tags/implicit.tld"%>
<html>
<head>
    <title>My Books</title>
</head>
<body>
<%
    BookDao bcs = new BookDao();
    Deque<Book> books = bcs.selectBookByClientId(String.valueOf(request.getSession().getAttribute("user_id")));
    request.setAttribute("items", books);
%>
<pag:Pagination items="${requestScope.items}"/>
</body>
</html>
