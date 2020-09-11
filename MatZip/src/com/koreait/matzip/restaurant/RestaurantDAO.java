package com.koreait.matzip.restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.db.JdbcUpdateInterface;
import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantDAO {
	
	public static int insertCate(RestaurantVO reset) {
		String sql = "insert into t_restaurant(nm, addr, lat,lng, cd_category,i_user) values(?,?,?,?,?,?)";
		return JdbcTemplate.excuteupdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(Connection conn, PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setNString(1, reset.getNm());
				ps.setNString(2, reset.getAddr());
				ps.setDouble(3, reset.getLat());
				ps.setDouble(4, reset.getLng());
				ps.setInt(5, reset.getCd_category());
				ps.setInt(6, reset.getI_user());
			}
		});
		
		
	}
}
