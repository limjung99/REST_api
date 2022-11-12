package com.example.demo;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class DbController {
	/*Class.forName("org.postgresql.Driver");  */
   static String    connurl  = "jdbc:postgresql://localhost:5432/hongik";
   static String     user     = "postgres";
   static String     password = "1234";
   
	static void initialize_DB() {
		try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE students(sid INTEGER NOT NULL, name VARCHAR(100), email VARCHAR(100), degree VARCHAR(100), graduation INTEGER, PRIMARY KEY(sid)) ";
            stmt.executeQuery(sql);
            stmt.close();
            connection.close();
   
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	

	static void insert_to_DB(Human h) throws SQLException { //human object를 전달받아, 데이터베이스
		 try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
	            Statement stmt = connection.createStatement();
	            int sid = h.get_sid()	;
	            String name = h.get_name();
	            String email = h.get_email();
	            String degree = h.get_degree();
	            int grad = h.get_graduation();
	            String sql = "insert into students values ("+sid+",'"+name+"','"+email+"','"+degree+"',"+grad+")";
	            ResultSet rs= stmt.executeQuery(sql);
	            
	       
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	static ResultSet execute_query(String sql) throws SQLException { //요청된 query를 실행 
		ResultSet rs=null;
		try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
        	e.printStackTrace();
        	
        }
		return rs;
		
	}
	
	static int update_query(String sql) throws SQLException { //요청된 query를 실행 
		int r=0;
		try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
            Statement stmt = connection.createStatement();
            r = stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
        	e.printStackTrace();
        	
        }
		return r;
		
	}
	
	
	
}

