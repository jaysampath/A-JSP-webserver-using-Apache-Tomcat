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

@WebServlet("/search")
public class Filesearch extends HttpServlet {
	 int file_count = 0;
     int count1 =0;
     ArrayList<String> searchres = new ArrayList<String>();
     ArrayList<String> searchres_filename = new ArrayList<String>();
     ArrayList<String> history_filename =  new ArrayList<>();
	 ArrayList<String> history_filecontent =  new ArrayList<>();
	 ArrayList<String> download_files =  new ArrayList<>();
	 
	 
	boolean searchtext(File f, String content)  {
	    if(content == ""){
	        return true;
	    }
	        if(f.isFile()) {
	            try{
	                FileReader fr = new FileReader(f.getAbsolutePath());
	                BufferedReader br = new BufferedReader(fr);
	                String s;

	                while((s=br.readLine())!=null){
	                    if(s.toLowerCase().contains(content.toLowerCase())){
	                        return true;
	                    }
	                }
	            }
	            catch(Exception e){
	            }
	        }
	        return false;
	    }

	     boolean file_search(File f, String filename){
	    if(filename == ""){
	        return true;
	    }
	        if(f.getName().toLowerCase().contains(filename.toLowerCase())) {
	            return true;
	        }
	        return false;
	    }
	    ArrayList<String> helper(File[] arr, String filename, String content){
	        ArrayList<String> a = new ArrayList<String>();
	    	 for(File f: arr){
	            if(f.isFile()){
	                file_count++;
	                if(file_search(f,filename) && searchtext(f,content)){
	                    count1++;
	                    System.out.println("found in filename or content :" + f.getPath());
	                    if(!a.contains(f.getPath())) {
	                    searchres.add(f.getPath());
	                    searchres_filename.add(f.getName());
	                    a.add(f.getName());
	                    }
	                    
	                }
	            }else{
	                helper(f.listFiles(),filename,content);
	            }
	           }
	     return a;
	     }
	    
	    
	    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context=getServletContext();
		session.setAttribute("his_filename",history_filename );
		session.setAttribute("his_content",history_filecontent );
		session.setAttribute("resfilename_list", searchres_filename);
		session.setAttribute("downloaded_files", download_files);
		
		String fileinput = "";
		fileinput = request.getParameter("filename");
		String contentinput = "";
		contentinput = request.getParameter("textcontent");
		PrintWriter out = response.getWriter();
		
		
		
		String path = "D:\\Practice_files\\eclipse_files\\eclipse_ee\\project1\\users\\src\\main\\java\\users\\test";
        File d = new File(path);
        File[] arr = d.listFiles();
		if(fileinput.length()==0 && contentinput.length()==0) {
			   out.println("<p>Either of the inputs is must</p>");
	 		   out.println("<a href=\"welcome.jsp\">Search Again</a>");
		}else {
			
			
			history_filename.add(fileinput);
			history_filecontent.add(contentinput);
			
			session.setAttribute("his_filename",history_filename );
			session.setAttribute("his_filecontent",history_filecontent );
			response.setContentType("text/html");
			out.println("<h2>Search Results </h2>");
			 ArrayList<String> res = helper(arr,fileinput,contentinput);
			for(int i =0;i<res.size();i++) {
				out.println("<p>");
				out.println("<a href=\"Download?filename="+res.get(i)+"\">"+res.get(i)+"</a>");
				out.println("</p>");
			}
		}
	
	}

}
