package com.example.demo;

public class Human {
	private int sid;
	private String myname;
	private String email;
	private String degree;
	private int graduation;
	
	 Human(int sid,String name,String email,String degree,int graduation){
		this.sid = sid;
		this.myname = name;
		this.degree =degree;
		this.email = email;
		this.graduation = graduation;
	}
	 
	 int get_sid() {return sid;}
	 String get_name() {return myname;}
	 String get_email() {return email;}
	 String get_degree() {return degree;}
	 int get_graduation() {return graduation;}
	 
	 
	 
}
