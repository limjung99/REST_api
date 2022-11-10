// Java Program to Illustrate DemoController
 
// Importing package to code module
package com.example.demo;
// Importing required classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;


// Annotation
@Controller
@RequestMapping("Endpoint/students")
// Class->tmp는 어노테이션에 의해 controller class 
public class Tmp {
	//처음 local서버가 구동될때, html값을 crawling해오고, db에 넣고 close
		Document h;
		Element phds;
		Element masters;
		Element unders;
		DbController dcon;
		

		Tmp() throws ClassNotFoundException { // 생성될 때, 크롤링 후 파싱 & DB에 insert
			try {
				String URL = "https://apl.hongik.ac.kr/lecture/dbms";
				org.jsoup.Connection conn = Jsoup.connect(URL);
				Document html = conn.get();
				this.h=html;
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			phds = h.getElementsByClass("n8H08c UVNKR").get(0); //phds element
			masters = h.getElementsByClass("n8H08c UVNKR").get(1); //masters element
			unders = h.getElementsByClass("n8H08c UVNKR").get(2); //unders element
			//constructor로 parsing한 element 삽입 
			
			this.dcon = new DbController();
			
			String phds_list = phds.text();
			String masters_list = masters.text();
			String unders_list = unders.text();
			
			
			
			
			
			
			
			
			
		}

		@RequestMapping("/degree")
		    //get  Method
		    @ResponseBody
		    //<name>:<degree>로return 
		    public String show_degree(@RequestParam(value="name") String name)
		    {	
			 	String t=unders.
			 	return t;
			 }

		 @RequestMapping("/email")
		    //get  Method
		    @ResponseBody
		    public String show_email(@RequestParam(value="email") String email)
		    {
		        // Print statement
		        return email;
		    }
		 @RequestMapping("/stat")
		    // get Method
		    @ResponseBody
		    public String show_stat(@RequestParam(value="stat") String stat)
		    {
		        // Print statement
		        return stat;
		    }
		 
		 

		 
		
		 	
		 	
		 
		 

		    
	

	

   
    
    
	
	
	
   

}
