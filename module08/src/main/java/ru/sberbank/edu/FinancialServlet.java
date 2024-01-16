package ru.sberbank.edu;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class FinancialServlet extends HttpServlet
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Расчет доходности вклада</h2>");
        out.println("<form action='/finance' method='post'>");
        out.println("Сумма на момент открытия: <input type='text' name='sum'><br>");
        out.println("Годовая процентная ставка: <input type='text' name='percentage'><br>");
        out.println("Количество лет: <input type='text' name='years'><br>");
        out.println("<input type='submit' value='Рассчитать'>");
        out.println("</form>");
        out.println("</body></html>");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            double sum = Double.parseDouble(request.getParameter("sum"));
            double percentage = Double.parseDouble(request.getParameter("percentage"));
            int years = Integer.parseInt(request.getParameter("years"));

            double finalAmount = sum * Math.pow(1 + (percentage / 100), years);
            out.println("<html><body>");
            out.println("<h2>Результат расчета доходности вклада</h2>");
            out.println("<p>Итоговая сумма после " + years + " лет: " + finalAmount + "</p>");
            out.println("</body></html>");
        } catch (NumberFormatException e) {
            out.println("<html><body>");
            out.println("<h2>Ошибка ввода данных</h2>");
            out.println("<p>Пожалуйста, введите корректные данные.</p>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }
}
