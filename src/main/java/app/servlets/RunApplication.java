package app.servlets;

import app.model.NumComp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunApplication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/runApp.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int[] number = NumComp.getInstance().getNumber();
        String number1 = req.getParameter("number1");
        String number2 = req.getParameter("number2");
        String number3 = req.getParameter("number3");
        String number4 = req.getParameter("number4");
        Integer num1 = Integer.valueOf(number1);
        Integer num2 = Integer.valueOf(number2);
        Integer num3 = Integer.valueOf(number3);
        Integer num4 = Integer.valueOf(number4);
        int[] userNumber = {num1, num2, num3, num4};
        int countPosition = 0;
        int countNumber = 0;
        for (int i = 0; i < number.length; i++) {
            if (number[i] == userNumber[i]) {
                countPosition++;
            }
        }
        for (int j = 0; j < number.length; j++) {
            for (int i = 0; i < number.length; i++) {
                if (number[j]==userNumber[i]){
                    countNumber++;
                }
            }
        }
        req.setAttribute("Bull",countPosition);
        req.setAttribute("Cow",countNumber);

        List<Integer> list = new ArrayList<Integer>(number.length);
        for (int value : number) {
            list.add(value);
        }
        req.setAttribute("number",list);
        doGet(req,resp);

    }
}
