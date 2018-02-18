<%@ page import="java.util.List" %>
<%@ page import="app.entities.User" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 15.02.2018
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div>
    <h1>Perfect application!</h1>
</div>
<div>
    <%
        if (request.getAttribute("users") != null) {
            out.println("<p>User " + request.getAttribute("users") + request.getAttribute("message") + "</p>");
        }
    %>
    <div>
        <div>
            <h2>Add user</h2>
        </div>
        <form method="post">
            <label>Login:
                <input type="text" name="login"><br/>
            </label>
            <br>
            <label>Password:
                <input type="password" name="password"><br/>
            </label>
            <button type="submit">Submit</button>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
