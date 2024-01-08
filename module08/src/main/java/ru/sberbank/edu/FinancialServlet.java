package ru.sberbank.edu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Hello world!
 *
 */
@WebServlet("/finance")
public class FinancialServlet extends HttpServlet {
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        try {
            DepositInfo depositInfo = new DepositInfo(Integer.parseInt(request.getParameter("sum")),
                    Integer.parseInt(request.getParameter("percentage")),
                    Integer.parseInt(request.getParameter("years")));
            if (depositInfo.getSum() > 900000000 || depositInfo.getSum() < 0
                    || depositInfo.getPercentage() < 0
                    || depositInfo.getYears() < 0) {
                request.getRequestDispatcher("/resultError.jsp").forward(request, response);
            } else {
                if (depositInfo.getSum() >= 50000) {
                    request.getRequestDispatcher("/resultSucsess.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/resultFail.jsp").forward(request, response);
                }
            }
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/resultError.jsp").forward(request, response);
        }
    }
}
