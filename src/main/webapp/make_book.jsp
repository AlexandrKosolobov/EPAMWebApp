<%@ page import="by.kosolobov.bshop.service.UserCommandService" %>
<%@ page import="by.kosolobov.bshop.entity.User" %>
<%@ page import="java.util.Deque" %><%--
  Created by IntelliJ IDEA.
  User: sasha
  Date: 1/8/2022
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
</head>
<body>
<form>
    <h2>Step 1. Choose barber</h2>
    <table>
        <thead>
        <tr>
            <th>First Name</th>
            <th>Second Name</th>
            <th>Surname</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <%
            UserCommandService ucs = new UserCommandService();
            Deque<User> barbers = ucs.selectAllBarbers();
            for (User barber : barbers) {
                %>
        <tr>
            <td><%=barber.getFirstName()%></td>
            <td><%=barber.getSecondName()%></td>
            <td><%=barber.getSurName()%></td>
            <td><%=barber.getDescription()%></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <h2>Step 2. Choose available service</h2>
    <h2>Step 3. Choose day</h2>
    <h2>Step 4. Choose available time</h2>
    <input type="submit" value="Make a book">
</form>
</body>
</html>
