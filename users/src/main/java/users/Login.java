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

import java.sql.*;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	String url="",uname1="",pass1="",query="";
	Connection con;
	PreparedStatement ps;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("Input: "+uname+" "+pass);
		ArrayList<String> un1 = (ArrayList<String>)session.getAttribute("unames");
		ArrayList<String> pwd1 = (ArrayList<String>)session.getAttribute("passwords");
		PrintWriter out = response.getWriter();
		ArrayList<String> check_unames = new ArrayList<>();
		ArrayList<String> check_pw = new ArrayList<>();
		
		try {
			 url = "jdbc:mysql://localhost:3306/db1";
			 uname1 = "root";
			 pass1 = "Jayasampath@200";
			 
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection(url,uname1,pass1);
			 query = "select * from task3_users";
				Statement stmt=con.createStatement(); 
				 ResultSet rs=stmt.executeQuery(query);
				 System.out.println("Table after new User: ");
				 while(rs.next()) { 
				 System.out.println(rs.getString(1)+"  | |   "+rs.getString(2)); 
				 check_unames.add(rs.getString(1));
				 check_pw.add(rs.getString(2));
				 }
			}
			catch(Exception e) {
				System.out.println("Exception while connecting: "+e);
			}
		
		if(!check_unames.contains(uname)) {
			out.println("<p>Not a registered User </p>");
			out.println("<a href=\"signup.jsp\">Signup here!</a>");
		}
		else {
	    int x=-1;
	    int y=-1;
		for(int i=0;i<check_unames.size();i++) {
			//System.out.println("Iteration: "+check_unames.get(i)+" "+check_pw.get(i));
	    	if(check_unames.get(i).equals(uname)&&check_pw.get(i).equals(pass)){
	    		
	    		 x = check_unames.indexOf(uname); 
	    		 y = check_pw.indexOf(pass);
                
	    		 System.out.println("index: "+x+" "+y);
	    	
	    	}
	    }
		
		//System.out.println("Usernames List:  "+check_unames);
		//System.out.println("Passwords Lits:   "+check_pw);
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
