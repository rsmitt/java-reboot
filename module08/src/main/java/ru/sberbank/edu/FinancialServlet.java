package ru.sberbank.edu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/finance")
public class FinancialServlet extends HttpServlet {

    double sumMin = 50000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double sum = 0;
        double percentage = 0;
        int years = 0;

        try{
            sum = Double.parseDouble(request.getParameter("sum"));
            percentage = Double.parseDouble(request.getParameter("percentage"));
            years = Integer.parseInt(request.getParameter("years"));
        } catch (NullPointerException | NumberFormatException e) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        if (sum <= sumMin) {
            getServletContext().getRequestDispatcher("/errorsum.jsp").forward(request, response);
        } else {

            double interest = sum * percentage / 100 * years;
            double total = sum + interest;

            request.setAttribute("calc", total);
            getServletContext().getRequestDispatcher("/calculation.jsp").forward(request, response);
        }
    }
}
