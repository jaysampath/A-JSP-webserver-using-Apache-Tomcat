package users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	
	ArrayList<String> un = new ArrayList();
	ArrayList<String> pwd = new ArrayList();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		
		
		//context.setAttribute("unames",un);  
		//context.setAttribute("passwords",pwd);
		
		if(confirm.equals(pass)){
			
			if(un.contains(uname)) {
				   out.println("<p>User already exists. Login with same details </p>");
		 		   out.println("<a href=\"login.jsp\">Login</a>");
			}else {
			un.add(uname);
			pwd.add(pass);
			session.setAttribute("unames",un);  
			session.setAttribute("passwords",pwd);
			response.sendRedirect("login.jsp");
			}
		}else {
			response.sendRedirect("signup.jsp");
		}
	}
}