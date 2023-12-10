package ru.sberbank.edu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FinancialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/inputFinanceParams.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String amount = req.getParameter("amount");
        String interest = req.getParameter("interest");
        String tenor = req.getParameter("tenor");
        if (!isStringContainsOnlyNumeric(amount) || !isStringContainsOnlyNumeric(interest) || !isStringContainsOnlyNumeric(tenor)) {
            getServletContext().getRequestDispatcher("/errorInputData.jsp").forward(req, resp);
        } else {
            if (Double.parseDouble(amount) < 50000) {
                getServletContext().getRequestDispatcher("/less50k.jsp").forward(req, resp);
            } else {
                Deposit deposit = new Deposit(Double.parseDouble(amount), Double.parseDouble(interest), Double.parseDouble(tenor));
                double result = deposit.getAmountAtMaturity();
                req.setAttribute("result", result);
                getServletContext().getRequestDispatcher("/amountAtMaturity.jsp").forward(req, resp);
            }
        }
    }

    private boolean isStringContainsOnlyNumeric(String inputString) {
        return inputString.matches("(\\d+\\.\\d+)|(\\d+)");
    }
}
