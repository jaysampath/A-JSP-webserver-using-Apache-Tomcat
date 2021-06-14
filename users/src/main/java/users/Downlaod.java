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

@WebServlet("/Download")
public class Downlaod extends HttpServlet {

	ArrayList<String> downloaded = new ArrayList<String>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter(); 
        
		out.println("hello");
		String filepath ="D:\\Practice_files\\eclipse_files\\eclipse_ee\\project1\\users\\src\\main\\java\\users\\test\\";
		String filename=request.getParameter("filename");
		downloaded.add(filename);
		session.setAttribute("downloadfiles",downloaded);
        System.out.println("Path: "+filepath);
        
      
        
        response.setContentType("APPLICATION/OCTET-STREAM");   
        response.setHeader("Content-Disposition","attachment; filename=\"" +  filename + "\"");   
        
        java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath+filename);  
                  
        int i;   
        while ((i=fileInputStream.read()) != -1) {  
          out.write(i);   
        }   
        fileInputStream.close();   
		
	}
	

}
