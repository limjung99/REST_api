// Java Program to Illustrate DemoController
 
// Importing package to code module
package com.example.demo;
// Importing required classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.StringTokenizer;
import java.util.Vector;

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
		int sid=0;

		Tmp() throws ClassNotFoundException, SQLException { // 생성될 때, 크롤링 후 파싱 & DB에 insert
			try {
				String URL = "https://apl.hongik.ac.kr/lecture/dbms";
				org.jsoup.Connection conn = Jsoup.connect(URL);
				Document html = conn.get();
				this.h=html;
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			Element lists[] = new Element[3];
			int elements_size = h.getElementsByClass("n8H08c UVNKR").size();
			
			for(int i=0;i<elements_size;i++) {
				lists[i] = h.getElementsByClass("n8H08c UVNKR").get(i);
			}
			for(int i=0;i<elements_size;i++) {
				for(Element e:lists[i].select("li")) {
					StringTokenizer st = new StringTokenizer(e.text(),",");
					
				}
			}
			
			//tokenizing
			
			
			DbController.insert_to_DB();
			/*
			
			while(v.get(0).hasMoreElements()) {
				System.out.println(v.get(0).nextToken());
			}
			
			
			
		/*	for(int i=0;i<3;i++) {
				while(v.get(i).hasMoreElements()) {
					String name;
					String email;
					String degree;
					int grad;
					int sid;
					
					
				}
			}*/
			
			
			
		
			
		}

		@RequestMapping("/degree")
		    //get  Method
		    @ResponseBody
		    //<name>:<degree>로return 
		    public String show_degree(@RequestParam(value="name") String name)
		    {	
				
			 	return name;
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
