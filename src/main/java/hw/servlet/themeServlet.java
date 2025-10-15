package hw.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;



@WebServlet("/theme") 
public class themeServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 private static final int COOKIE_MAX_AGE = 24 * 60 * 60; // 24 часа в секундах
	
	 
	  @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	    	
	    	response.sendRedirect(request.getContextPath() + "/theme.html");      
	    }
	    
	 
	    @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
                 String color = request.getParameter("color");
                 Cookie cookie = new Cookie("bgColor", color);
                 cookie.setMaxAge(COOKIE_MAX_AGE);
                 response.addCookie(cookie);
                 response.sendRedirect("theme.html");
		}
}
