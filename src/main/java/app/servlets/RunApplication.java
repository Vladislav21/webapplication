package app.servlets;


import app.model.*;
import app.utils.DBUtils;
import app.utils.PostgreSQLConnUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class RunApplication extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/runApp.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection con = PostgreSQLConnUtils.getPostgreSQLConnection();
            /**
             * Установка ID игры
             */
            if (StaticCollection.getGameId() == 0) {
                int gameId1 = DBUtils.getNewIdGame(con);
                StaticCollection.setGameId(gameId1);
            }

            /**
             * Счетчик попыток
             */
            Count instance1 = Count.getInstance();
            int count1 = instance1.getCount();
            count1++;
            instance1.setCount(count1);


            /**
             * Логика самой игры
             */
            int[] number = NumComp.getInstance().getNumber();
            String number1 = req.getParameter("number1");
            String number2 = req.getParameter("number2");
            String number3 = req.getParameter("number3");
            String number4 = req.getParameter("number4");
            String[] check = {number1, number2, number3, number4};
            Set<String> set = new HashSet<String>(Arrays.asList(check));
            if (check.length == set.size()) {
                Integer num1 = Integer.valueOf(number1);
                Integer num2 = Integer.valueOf(number2);
                Integer num3 = Integer.valueOf(number3);
                Integer num4 = Integer.valueOf(number4);
                int[] userNumber = {num1, num2, num3, num4};
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < userNumber.length; i++)
                    sb.append(userNumber[i]);
                long MyNumber = Long.parseLong(sb.toString());
                req.setAttribute("MyNumber", MyNumber);
                int countPosition = 0;
                int countNumber = 0;
                for (int i = 0; i < number.length; i++) {
                    if (number[i] == userNumber[i]) {
                        countPosition++;
                    }
                }
                for (int aNumber : number) {
                    for (int i = 0; i < number.length; i++) {
                        if (aNumber == userNumber[i]) {
                            countNumber++;
                        }
                    }
                }


                /**
                 * Заносим результат в БД и берем оттуда список для данного ID игры
                 */
                String resultStr = "My number: " + MyNumber + " Bull: " + countPosition + " Cow: " + (countNumber - countPosition);
                DBUtils.updateGame(con, StaticCollection.getGameId(), CurrentUser.getId(), resultStr);
                List<String> data = DBUtils.getData(con, StaticCollection.getGameId(), CurrentUser.getId());
                req.setAttribute("result", data);

                if (countPosition == 4) {
                    String congratulations = null;
                    number = new NumComp().getNumber();
                    NumComp.getInstance().setNumber(number);
                    int idUser = CurrentUser.getId();
                    DBUtils.putAttempts(con, StaticCollection.getGameId(), count1);
                    StaticCollection.setGameId(0);
                    int aver = DBUtils.countAverage(con, idUser, StaticCollection.getGameId());
                    DBUtils.setAverage(con, idUser, aver);
                    congratulations = "My congratulations!!!You win!!!";
                    req.setAttribute("congratulations", congratulations);
                    con.close();
                    instance1.setCount(0);
                }
            } else {
                String warning = null;
                warning = "The number must consist of different digits! Try again...";
                req.setAttribute("warning", warning);
            }
            doGet(req, resp);
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
