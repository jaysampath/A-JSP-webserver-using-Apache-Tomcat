package users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

@WebServlet("/history")
public class History extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ArrayList<String> his_search1 = (ArrayList<String>)session.getAttribute("his_filename");
		ArrayList<String> his_search2 = (ArrayList<String>)session.getAttribute("his_filecontent");
		ArrayList<String> downloadfiles1 = new ArrayList<String>();
		downloadfiles1 = (ArrayList<String>)session.getAttribute("downloadfiles");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<h1>History Page</h1>");
		out.println("<h2> Search History </h2>");
		System.out.println("File name History : "+his_search1);
		System.out.println("Content name History : "+his_search2);
		
		if(his_search1.size()>0) {
		out.println("<table>");
		out.println("<tr> <th> File Name....|.... </th>  <th> File Content </th>");
		for(int i =0;i<his_search1.size();i++) {
			
			out.println("<p><tr> <td>" + his_search1.get(i) + " </td> <td> " + his_search2.get(i)  + " </td></tr></p>");
			
			
		}
		out.println("</table>");
		}else {
			out.println("<p>Nothing searced yet</p>");
		}
		out.println("<h2> Download History </h2>");
		
		if(downloadfiles1.size()>0) {
			out.println("<ul>");
        for(int i=0;i<downloadfiles1.size();i++) {
       	 out.println("<p><li>\r\n"
       			 + downloadfiles1.get(i)+
       			 "</p></li>\r\n");
        }
        out.println("</ul>");
		}else {
			out.println("<p>Nothing Downloaded yet</p>");
		}
	}


}
