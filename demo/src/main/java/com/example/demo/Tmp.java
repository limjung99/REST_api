// Java Program to Illustrate DemoController
 
// Importing package to code module
package com.example.demo;
// Importing required classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.jsoup.*;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.sql.*;




// Annotation
@Controller
@RequestMapping("Endpoint/students")
// Class->tmp는 어노테이션에 의해 controller class 
public class Tmp {
		Document h; //문서객체 ->html... parsing해주어서 가공해줘야함 
		Element phds;
		Element masters;
		Element unders;
		
		Tmp() {
			try {
				String URL = "https://apl.hongik.ac.kr/lecture/dbms";
				Connection conn = Jsoup.connect(URL);
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
		
		}

		@RequestMapping("/degree")
		    //get  Method
		    @ResponseBody
		    //<name>:<degree>로return 
		    public String show_degree(@RequestParam(value="name") String name)
		    {	
			 	String m = phds.text();
			 	
			 	return "hi";
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
		 @RequestMapping("/register")
		    // put method
		    @ResponseBody
		    public String put_register(@RequestParam(value="register") String register)
		    {
	
		        // Print statement
		        return register;
		    }
		 
		
		 	
		 	
		 
		 

		    
	

	

   
    
    
	
	
	
   

}
