package com.koreait.matzip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
	
	public static void main(String [] args) {
		try {
			getCon();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getCon() throws Exception{
		String url= "jdbc:mysql://localhost:3306/matzip";
		String user = "root";
		String pw = "koreait2020";
		String className = "com.mysql.cj.jdbc.Driver";
		
		Class.forName(className);
		Connection con = DriverManager.getConnection(url,user,pw);
		System.out.println("DB Connection Success");
		return con;
	}
	
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs!=null){
			try{
				rs.close();
			}catch(Exception e){}
		}
		close(conn,ps);
	}
	
	
	public static void close(Connection conn, PreparedStatement ps) {
		
		if(ps!=null){
			try{
				ps.close();
			}catch(Exception e){}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){}
		}
	}
}