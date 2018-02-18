<%@ page import="java.util.List" %>
<%@ page import="app.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 15.02.2018
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rating of users (average number of attempts)</title>
</head>
<body>
<div>
    <h1>Perfect application!</h1>
</div>
<div>
    <div>
        <div>
            <h2>Users</h2>
        </div>
        <%
            List<User> users = (List<User>) request.getAttribute("users");

            if (users != null && !users.isEmpty()) {
                out.println("<ui>");
                for (User user : users) {
                    out.println("<li>" + user + "</li>");
                }
                out.println("</ui>");
            } else out.println("<p>There are no users yet!</p>");
        %>
    </div>
</div>
<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
