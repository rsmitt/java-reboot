package ru.sberbank.edu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Hello world!
 *
 */
public class FinancialServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            double sum = Double.parseDouble(req.getParameter("sum"));
            double percentage = Double.parseDouble(req.getParameter("percentage"));
            double years = Double.parseDouble(req.getParameter("years"));

            if (sum < 50_000.00) {
                getServletContext().getRequestDispatcher("/smallSumResult.jsp").forward(req, resp);
            }

            double totalSum = sum + sum * percentage/100 * years;

            req.setAttribute("totalSum", new DecimalFormat("#0.00").format(totalSum));
            getServletContext().getRequestDispatcher("/correctResult.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            getServletContext().getRequestDispatcher("/incorrectInputResult.jsp").forward(req, resp);
        }
    }
}
