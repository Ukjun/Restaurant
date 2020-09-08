package com.koreait.matzip.db;

import java.sql.*;
import java.util.*;

public class JdbcTemplate {
	public static int excuteupdate(String sql,JdbcUpdateInterface jdbc) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBManager.getCon();
			ps = conn.prepareStatement(sql);
			jdbc.update(conn, ps);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBManager.close(conn, ps);
		}
		return result;
	}
	public static int executeQuery(String sql, JdbcSelectInterface jdbc) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result =0;
		try {
			conn = DBManager.getCon();
			ps = conn.prepareStatement(sql);
			jdbc.prepared(ps);
			
			rs = ps.executeQuery();
			result = jdbc.executeQuery(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, ps,rs);
		}
		return result;
		
	}
	public static List<?> executeQueryList(String sql, JdbcSelectInterface jdbc){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List <?> list = new ArrayList();
		try {
			conn = DBManager.getCon();
			ps = conn.prepareStatement(sql);
			jdbc.prepared(ps);
			
			rs = ps.executeQuery();
			list = jdbc.selBoard(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, ps,rs);
		}
		return list;
	}
}

