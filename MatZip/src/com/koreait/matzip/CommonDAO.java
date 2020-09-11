package com.koreait.matzip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.vo.CodeDomain;

public class CommonDAO {
	public static List<CodeDomain> selCodeList(final int i_m){
		// List은 인터페이스 
		List<CodeDomain> list = new ArrayList();
		
		String sql = "select i_m, cd, val from c_code_d where i_m=?";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public List<?> selBoard(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, i_m);
			}
			
			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				while(rs.next()) {
					CodeDomain code = new CodeDomain();
					code.setI_m(rs.getInt("i_m"));
					code.setCd(rs.getInt("cd"));
					code.setVal(rs.getString("val"));
					
					list.add(code);
				}
			}
		});
		
		return list;	
	}
}
