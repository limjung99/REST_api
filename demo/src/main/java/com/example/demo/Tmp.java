// Java Program to Illustrate DemoController
 
// Importing package to code module
package com.example.demo;
// Importing required classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

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
public class Tmp {
	//처음 local서버가 구동될때, html값을 crawling해오고, db에 넣고 close
		Document h;
		DbController dcon;
		int sid;
		Element lists[];
		Vector<Human> v;
		
		//constructor
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
			//human객체를 저장하는 vector생성
			v= new Vector<Human>();
			
			sid=0;
			Element lists[] = new Element[3]; //index 0,1,2 순으로 phd, graduate, undergraduate
			
			int elements_size = h.getElementsByClass("n8H08c UVNKR").size();
			
			for(int i=0;i<elements_size;i++) {
				lists[i] = h.getElementsByClass("n8H08c UVNKR").get(i);
			}
			
			//human object 생성
			for(int i=0;i<elements_size;i++) {
				for(Element e:lists[i].select("li")) {
					StringTokenizer st = new StringTokenizer(e.text(),",");
					while(st.hasMoreElements()) {
						String name = st.nextToken().trim();
					
						String email = st.nextToken().trim();
					
						int graduation = Integer.parseInt(st.nextToken().trim());
						int tmp_sid = sid;
						sid++;
						String degree = "";
						if(i==0) {
							degree="PhD";
						}
						else if(i==1) {
							degree="Master";
						}
						else if(i==2) {
							degree="Undergraduate";
						}
						Human tmp_h = new Human(tmp_sid,name,email,degree,graduation);
						v.add(tmp_h);
					}
					
				}
			}
			
			/*DbController.initialize_DB();*/ //DB생성 hongik->students
			
			//human object vector를 순회하면서, 각 human을 데이터베이스에 인풋
			//처음 긁어온 html을 li별로 parsing 후 human 객체를 생성해주었다.
			//이 후 , human객체의 배열을 DB에 순회하면서 넣어준다. 
			
			
		/*	for(int i=0;i<v.size();i++) {
				DbController.insert_to_DB(v.get(i)); 
			}*/
			
	
			
		}

		@RequestMapping("/degree")
		    //get  Method
		    @ResponseBody
		    //<name>:<degree>로return 
		    public String show_degree(@RequestParam(value="name") String name) throws SQLException
		    {	
				ResultSet rs;
				Vector<String> degree_v = new Vector<String>();
				String ans=null;
				String sql = "select degree from students where name='"+name+"'";
				rs=DbController.execute_query(sql);
				while(rs.next()) {
					String tmp = rs.getString("degree");
					degree_v.add(tmp);
				}
				if(degree_v.size()==0) {
					ans="No such student";
				}
				
				else if(degree_v.size()==1) {
					ans=name+":"+degree_v.get(0);
				}
				else {
					ans="There are multiple students with the same name. Please contact the administrator by phone.";	
				}
				
				return ans;
				
			 }

		 @RequestMapping("/email")
		    //get  Method
		    @ResponseBody
		    public String show_email(@RequestParam(value="name") String name) throws SQLException
		    {
			 	ResultSet rs;
			 	Vector<String> email_v = new Vector<String>();
				String ans=null;
				String sql = "select email from students where name='"+name+"'";
				rs=DbController.execute_query(sql);
				//resultSet을 순회하면서,이메일의 집합을 벡터에 push해준다.
				while(rs.next()) {
					String tmp = rs.getString("email");
					email_v.add(tmp);
				}
				if(email_v.size()==0) {
					ans="No such student";
				}
				
				else if(email_v.size()==1) {
					ans=name+":"+email_v.get(0);
				}
				else {
					ans="There are multiple students with the same name. Please contact the administrator by phone.";	
				}
			
				
				return ans;
		    }
		 @RequestMapping("/stat")
		    // get Method
		    @ResponseBody
		    public String show_stat(@RequestParam(value="degree") String degree) throws SQLException
		    {
			 	ResultSet rs;
				String ans="Number of "+degree+"'s student:";
				String sql = "select count(*) from students where degree='"+degree+"'";
	
				rs=DbController.execute_query(sql);
				rs.next();
				int tmp = rs.getInt(1);
				
				ans=ans+tmp;
				
				return ans;
		        
		    }
		 
		 @RequestMapping(value="/register", method = RequestMethod.PUT)
		    // put Method
		    @ResponseBody
		    public String regist (@RequestParam String name,@RequestParam String email,@RequestParam int grad ) throws SQLException 
		    {
			 	//동명이인 찾기
			 	String ans="";
			 	String sql = "select count(*) from students where name='"+name+"'";
			 	ResultSet rs = DbController.execute_query(sql);
			 	rs.next();
			 	if(rs.getInt(1)==0) {//동명이인이 없음 
			 		//다음 sid 찾아오기
				 	sql = "select max(sid) from students";
				 	rs = DbController.execute_query(sql);
				 	rs.next();
				 	int new_sid=rs.getInt(1)+1;
				 	
				 	sql = "insert into students values ("+new_sid+",'"+name+"','"+email+"','undergraduate',"+grad+")";
				 	int t= DbController.update_query(sql);
				 	ans = "Registration successful";
			 	}
			 	else {//동명이인 존재 
			 		ans =  "Already registered";
			 	}
		        return ans;
		    }
		 
		 	

		 
		
		 	
		 	
		 
		 

		    
	

	

   
    
    
	
	
	
   

}
