package com.example.demo;
import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) throws ClassNotFoundException {
		
		//ok연결완료->하하하
		Class.forName("org.postgresql.Driver");  
	
        String     connurl  = "jdbc:postgresql://localhost:5432/postgres";
        String     user     = "postgres";
        String     password = "1234";
 
        try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
              Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery("select version()");
 
              while (rs.next()) {
                  String version = rs.getString("version");
                  
                  System.out.println(version);                  
              }           
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	        
		

		
		SpringApplication.run(DemoApplication.class, args); //controller 애노테이션을 실행시킴 
	}

}
