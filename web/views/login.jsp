<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 16.02.2018
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <h1>Perfect application!</h1>
</div>
<div>
    <%
        if (request.getAttribute("message")!=null){
            out.println("<p>"+request.getAttribute("message")+"</p>");
        }
    %>
    <div>
        <div>
            <h2>Login user</h2>
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
        <%
            if (request.getAttribute("next")!=null){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("afterReg.html");
                requestDispatcher.forward(request,response);
            }
        %>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
