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


@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("Input: "+uname+" "+pass);
		ArrayList<String> un1 = (ArrayList<String>)session.getAttribute("unames");
		ArrayList<String> pwd1 = (ArrayList<String>)session.getAttribute("passwords");
		PrintWriter out = response.getWriter();
		if(!un1.contains(uname)) {
			out.println("<p>Not a registered User </p>");
			out.println("<a href=\"signup.jsp\">Signup here!</a>");
		}
		else {
	    int x=-1;
	    int y=-1;
		for(int i=0;i<un1.size();i++) {
			System.out.println("Iteration: "+un1.get(i)+" "+pwd1.get(i));
	    	if(un1.get(i).equals(uname)&&pwd1.get(i).equals(pass)){
	    		
	    		 x = un1.indexOf(uname); 
	    		 y = pwd1.indexOf(pass);
                
	    		 System.out.println("index: "+x+" "+y);
	    	
	    	}
	    }
		
		System.out.println("Usernames List:  "+un1);
		System.out.println("Passwords Lits:   "+pwd1);
		 if(  x!=-1 && x==y){
	    		response.sendRedirect("welcome.jsp");
 	   }else {
 		   out.println("<p>The username and Password combination is incorrect </p>");
 		   out.println("<a href=\"login.jsp\">Try Again</a>");
 		   response.setContentType("text/html");  
 		   //response.sendRedirect("login.jsp");
 	   }
		//PrintWriter out = response.getWriter();
		//out.println(uname);
		}
	}
}