package hw.servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;



@WebServlet("/login") 
public class loginServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	 User users[] = {  new User("leanx", "777"), new User("dimvxs", "111"), new User("fix", "555")};

	 
	 

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	    	
	    	String login = request.getParameter("login");
	        String password = request.getParameter("password");
	        boolean found = false;

	      
	        try {
	            for(User user: users)
	            {
	            	if(login.equals(user.getLogin()) && password.equals(user.getPassword()))
	            	{
	            		 
	         	         found = true;
	         	         break;
	            	}
	          
	            }
	            
	            if(found)
	            {
	            	PrintWriter printWriter  = response.getWriter();
           		 response.setContentType("text/html; charset=UTF-8");
           		 response.setCharacterEncoding("UTF-8");
        	         printWriter.println("<h1>Добрый день, " + login + "</h1>");
	            }
	            
	            else
	            {
	              	     request.setAttribute("error", "Неверный логин или пароль!");
	            	     request.getRequestDispatcher("/error.html").forward(request, response);
	            
	            }
	            
	        } catch (NumberFormatException e) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "что-то пошло не так");
	            return;
	        }

	    
	       
	    }
}
