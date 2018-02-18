<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 15.02.2018
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nice game</title>
</head>
<body>
<div>
    <h1>Welcome to Bull-cow game!</h1>
</div>
<div>
    <h2>Rules of the game:</h2>
    <h3>You have to guess the number envisioned by the computer.<br>Enter the number until there are 4 bulls.<br>All
        numbers are different.</h3>
</div>
<div>
    <form method="post">
        <label>Your number:
            <input type="number" name="number1"><input type="number" name="number2"><input type="number" name="number3"><input
                    type="number" name="number4">
        </label>
        <button type="submit">Submit</button>
    </form>
</div>
<div>
    <label>Result:
        <%
            if (request.getAttribute("Bull") != null && request.getAttribute("Cow") != null) {
                out.println("<p>" + "Bull: " + request.getAttribute("Bull") + " Cow: " + request.getAttribute("Cow") + "</p>" + "<br/>");
            }
            if (request.getAttribute("number") != null) {
                out.println("<p>" + request.getAttribute("number") + "</p>");
            }
        %>
    </label>
</div>
<div>
    <button onclick="location.href='/login'">Back to login</button>
</div>
</body>
</html>
