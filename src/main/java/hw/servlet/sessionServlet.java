package hw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet ("/session")

public class sessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // получаем или создаём сессию
        HttpSession session = request.getSession(true);

        // инициализируем счётчик посещений, если его нет
        AtomicInteger visitCount = (AtomicInteger) session.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = new AtomicInteger(1);
            session.setAttribute("visitCount", visitCount);
        } else {
            visitCount.incrementAndGet();
        }

        // получаем и обновляем дату последнего доступа
        Date lastAccess = (Date) session.getAttribute("lastAccess");
        Date currentDate = new Date();
        session.setAttribute("lastAccess", currentDate);

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // выводим HTML с данными сессии
        pw.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\">");
        pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        pw.println("<link rel=\"stylesheet\" href=\"/styles.css\">");
        pw.println("<title>Информация о сессии</title></head><body>");
        pw.println("<div class=\"container\">");
        pw.println("<h2>Информация о сессии</h2>");
        if (lastAccess != null) {
            pw.println("<p>Последний доступ: " + lastAccess + "</p>");
        }
        pw.println("<p>Текущее время: " + currentDate + "</p>");
        pw.println("<p>Количество посещений: " + visitCount.get() + "</p>");
        pw.println("<p><a href=\"session.html\">Перейти к форме</a></p>");
        pw.println("</div></body></html>");

        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // получаем сессию
        HttpSession session = request.getSession(true);

        // получаем параметр из формы
        String userName = request.getParameter("userName");
        if (userName != null && !userName.trim().isEmpty()) {
            session.setAttribute("userName", userName);
        }

        // перенаправляем на страницу с информацией о сессии
        response.sendRedirect(request.getContextPath() + "/session");
    }

} 
