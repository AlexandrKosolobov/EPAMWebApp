<%@ page import="by.kosolobov.bshop.service.BookCommandService" %>
<%@ page import="by.kosolobov.bshop.entity.Book" %>
<%@ page import="java.util.Deque" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="pag" uri="/WEB-INF/tags/implicit.tld"%>
<html>
<head>
    <title>My Books</title>
</head>
<body>
<%
    BookCommandService bcs = new BookCommandService();
    Deque<Book> books = bcs.getBookByClientId(String.valueOf(request.getSession().getAttribute("user_id")));
    request.setAttribute("items", books);
%>
<pag:Pagination items="${requestScope.items}"/>
</body>
</html>
