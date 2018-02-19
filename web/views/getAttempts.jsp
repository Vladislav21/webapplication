<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 15.02.2018
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>It's your previous attempts my friend</title>
    <style>
        table {
            width: 600px; /* Ширина таблицы */
            border: 1px solid green; /* Рамка вокруг таблицы */
            margin: auto; /* Выравниваем таблицу по центру окна  */
        }

        td {
            text-align: center; /* Выравниваем текст по центру ячейки */
        }
    </style>
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

<div>
    <div>
        <body class="w3-light-grey">
        <div id="center">
            <div class="w3-round-large">
                <h1>Attempts</h1>
                <div>
                    <%
                        List<Integer> attempts = (List<Integer>) request.getAttribute("attempts");
                        int count = 0;
                        if (attempts != null && !attempts.isEmpty()) {
                            out.println("<ui>");
                            out.println("<table>");
                            out.println("<TR>\n" +
                                    "                <TH>GAME</TH> <TH>RESULT</TH>\n" +
                                    "        </TR>");
                            for (Integer atp : attempts) {
                                count++;
                                out.println("<tr>");
                                out.println("<th>" + count + "</th>" + "<th>" + atp + "</th>");
                                out.println("</tr>");
                            }
                            out.println("</table>");
                            out.println("</ui>");
                        } else out.println("<p>No attempts!First, play the game!</p>");
                    %>
                </div>
            </div>
            <div>
                <button class="w3-hover-light-blue w3-round-large" onclick="location.href='/'">Back to main</button>
            </div>
        </body>
</html>
