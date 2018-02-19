<%@ page import="java.util.List" %>
<%@ page import="java.io.*" %><%--
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
    <div class="w3-round-large">
        <h3>Good Luck!<br>Rules of the game:<br>You have to guess the number envisioned by the computer.<br>Enter the
            number until there are 4 bulls.<br>All
            numbers are different.</h3>

        <div>
            <div class="w3-container w3-center">
                <div class="w3-bar w3-padding-large w3-padding-24">
                    <form method="post">
                        <label>Your number:
                            <input class="w3-hover-light-blue w3-round-large" type="number" name="number1">
                            <input class="w3-hover-light-blue w3-round-large" type="number" name="number2">
                            <input class="w3-hover-light-blue w3-round-large" type="number" name="number3">
                            <input class="w3-hover-light-blue w3-round-large" type="number" name="number4">
                        </label>
                        <button class="w3-hover-light-blue w3-round-large" type="submit">Submit</button>
                    </form>
                </div>
                <div>
                    <label>Result:
                        <%
                            if (request.getAttribute("congratulations") != null) {
                                out.println("<p>" + request.getAttribute("congratulations") + "</p>");
                            }
                        %>
                        <%
                            if (request.getAttribute("warning") != null) {
                                out.println("<p>" + request.getAttribute("warning") + "</p>");
                            }
                        %>
                        <%
                            if (request.getAttribute("Bull") != null && request.getAttribute("Cow") != null) {
                                out.println("<p>" + "Bull: " + request.getAttribute("Bull") + " Cow: " + request.getAttribute("Cow") + " My number: " + request.getAttribute("MyNumber") + "</p>" + "<br/>");
                                /*out.println("<p>"+request.getAttribute("result")+"</p>");*/
                            }
           /* if (request.getAttribute("number") != null) {
                out.println("<p>" + request.getAttribute("number") + "</p>");
            }*/
                        %>
                    </label>
                    <div>
                        <%

                        %>
                    </div>
                </div>
                <div>
                    <button class="w3-hover-light-blue w3-round-large" onclick="location.href='/'">Back to menu</button>
                </div>
</body>
</html>
