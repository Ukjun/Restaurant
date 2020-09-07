package com.koreait.matzip.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	
	public static void main(String [] args) {
		try {
			getCon();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getCon() throws Exception{
		String url= "jdbc:mysql://127.0.0.1/matzip";
		String user = "root";
		String pw = "koreait2020";
		String className = "com.mysql.cj.jdbc.Driver";
		
		Class.forName(className);
		Connection con = DriverManager.getConnection(url,user,pw);
		System.out.println("DB Connection Success");
		return con;
	}
}
