package com.example.demo;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class DbController {
	/*Class.forName("org.postgresql.Driver");  */
   static String    connurl  = "jdbc:postgresql://localhost:5432/postgres";
   static String     user     = "postgres";
   static String     password = "1234";
	
	

	static void insert_to_DB() throws SQLException { //human object를 전달받아, sql에 input 
		/*stmt.executeQuery("insert into students(sid,name,email,degree,graduation) values("+sid+","+name+","+email+","+degree+","+grad+");" );*/
		 try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("select version()");
	            while(rs.next()) {
	            	String r = rs.getString("version");
	            	System.out.println(r);
	            }
	            
	       
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	static void execute_query(String s) throws SQLException { //요청된 query를 실행 
		try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select version()");
            while(rs.next()) {
            	String r = rs.getString("version");
            	System.out.println(r);
            }
            
            
        }
        catch (SQLException e) {
            
	}
	
	
	
}
}
