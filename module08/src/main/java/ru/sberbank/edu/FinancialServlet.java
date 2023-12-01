package ru.sberbank.edu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Hello world!
 *
 */
@WebServlet("/HelloWorld")
public class FinancialServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {
        //PrintWriter pw = response.getWriter();
        //pw.println("<html>");
        //pw.println("<h1> Hello World!</h1>");
        //pw.println("</html>");
        //request.getRequestDispatcher("/webapp/WEB-INF/index.jsp").forward(request, response);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }
}
