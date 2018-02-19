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
    <style>
        div {
            border: 1px solid black; /* Параметры рамки */
            padding: 5px; /* Поля вокруг текста */
            margin-bottom: 5px; /* Отступ снизу */
        }

        #left {
            text-align: left;
        }

        #right {
            text-align: right;
        }

        #center {
            text-align: center;
        }

        .content {
            width: 75%; /* Ширина слоя */
            background: #fc0; /* Цвет фона */
        }
    </style>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body>
<body class="w3-light-grey">
<div id="center">
    <div class="w3-round-large"><h1>LOGIN</h1>

        <div>
                <%
        if (request.getAttribute("message")!=null){
            out.println("<p>"+request.getAttribute("message")+"</p>");
        }
    %>
            <div>
                <div class="w3-container w3-center">
                    <div class="w3-bar w3-padding-large w3-padding-24">
                        <form method="post">
                            <label>Login:
                                <input class="w3-hover-light-blue w3-round-large" type="text" name="login"><br/>
                            </label>
                            <br>
                            <label>Password:
                                <input class="w3-hover-light-blue w3-round-large" type="password" name="password"><br/>
                            </label>
                            <button class="w3-hover-light-blue w3-round-large" type="submit">Submit</button>
                        </form>
                        <%
                            if (request.getAttribute("next") != null) {
                                RequestDispatcher requestDispatcher = request.getRequestDispatcher("afterReg.html");
                                requestDispatcher.forward(request, response);
                            }
                        %>
                    </div>
                </div>
                <div>
                    <button onclick="location.href='/'">Back to main</button>
                </div>
</body>
</html>
