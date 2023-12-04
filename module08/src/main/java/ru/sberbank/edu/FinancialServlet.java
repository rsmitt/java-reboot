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
        String sum = request.getParameter("sum");
        String percentage = request.getParameter("percentage");
        String years = request.getParameter("years");
        DepositInfo depositInfo = new DepositInfo(sum, percentage, years);
        HttpSession httpSession = request.getSession();
        //request.getRequestDispatcher("/index.jsp").forward(request, response);
        Writer writer = response.getWriter();
        writer.write("" + Integer.parseInt(sum) * (1 + (Integer.parseInt(percentage) / 100))  * Integer.parseInt(years));
    }
}
