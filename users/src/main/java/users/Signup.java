package users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import java.sql.*; 

@WebServlet("/signup")
public class Signup extends HttpServlet {
	
	ArrayList<String> un = new ArrayList();
	ArrayList<String> pwd = new ArrayList();
	String url="",uname1="",pass1="",query="";
	Connection con;
	PreparedStatement ps;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		String confirm = request.getParameter("confirm");

		try {
		 url = "jdbc:mysql://localhost:3306/db1";
		 uname1 = "root";
		 pass1 = "Jayasampath@200";
		 
		Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection(url,uname1,pass1);
		}
		catch(Exception e) {
			System.out.println("Exception while connecting: "+e);
		}
		
		//check for existence of user
		if(confirm.equals(pass)){
			
			ArrayList<String> check1 = new ArrayList<String>();
			try {
			query = "select * from task3_users";
			Statement stmt=con.createStatement(); 
			 ResultSet rs=stmt.executeQuery(query);
			 
			 while(rs.next()) { 
			 System.out.println(rs.getString(1)+"  | |   "+rs.getString(2)); 
			 check1.add(rs.getString(1));
			 }
			}
			catch(Exception e) {
				System.out.println("Error while checking existing user: "+e);
			}
			System.out.println("Existing users: "+check1);
			if(check1.contains(uname)) {
				   out.println("<p>User already exists. Login with same details </p>");
		 		   out.println("<a href=\"login.jsp\">Login</a>");
			}
			else {
			 try {
			un.add(uname);
			pwd.add(pass);
			session.setAttribute("unames",un);  
			session.setAttribute("passwords",pwd);
			response.sendRedirect("login.jsp");
			query = "insert into task3_users(username,passwords) values(?,?)";
			 ps = con.prepareStatement(query);
			ps.setString(1, uname );
			ps.setString(2, pass );
			int count = ps.executeUpdate();
			System.out.println("edited columns: " + count + " user(s) added");
			ps.close();
			con.close();
			
				}catch(Exception e){
					System.out.println(e);
				}
			
			}
		}else {
			response.sendRedirect("signup.jsp");
		}
	}
}
