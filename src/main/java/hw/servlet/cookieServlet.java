package hw.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;



@WebServlet("/cookie") 
public class cookieServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 private static final int COOKIE_MAX_AGE = 24 * 60 * 60; // 24 часа в секундах
	
	 

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	    	
	    	response.sendRedirect(request.getContextPath() + "/cookie.html");      
	    }
	    
	    @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// получаем данные из формы
			String data = request.getParameter("cookieValue");
			if (data != null && !data.trim().isEmpty()) {
				// создаем уникальное имя для куки
				String name = "cookie_" + System.currentTimeMillis();
				Cookie cookie = new Cookie(name, data);
				cookie.setMaxAge(COOKIE_MAX_AGE);
				response.addCookie(cookie);
			}
			// перенаправляем назад на страницу
			response.sendRedirect(request.getContextPath() + "/cookie.html");
		}
}
