package com.example.demo;
import java.sql.*;

public class DbController {
	Statement stmt;
	
	
	DbController() throws ClassNotFoundException{
		Class.forName("org.postgresql.Driver");  
        String     connurl  = "jdbc:postgresql://localhost:5432/postgres";
        String     user     = "postgres";
        String     password = "1234";
 
        try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
            Statement stmt = connection.createStatement();
            this.stmt = stmt;
           /*   ResultSet rs = stmt.executeQuery("select version()");
 
              while (rs.next()) {
                  String version = rs.getString("version");
                  System.out.println(version);                  
              }           
            rs.close();
            stmt.close();
            connection.close();*/
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
 
	}
	
	void insert_to_DB(String s) { //파싱 후 insert to postgres
		
	}
	
	void execute_query(String s) { //요청된 query를 실행 
		
	}
	
	
	
}
