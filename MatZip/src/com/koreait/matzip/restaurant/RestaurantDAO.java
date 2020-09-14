package com.koreait.matzip.restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.db.JdbcUpdateInterface;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantDAO {
	
	public int insertCate(RestaurantVO reset) {
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
	
	
	public List<RestaurantDomain> selRestList(){
		List<RestaurantDomain> list = new ArrayList();
		String sql = "select i_rest, nm, lat, lng from t_restaurant";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public List<?> selBoard(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				while(rs.next()) {
					RestaurantDomain vo = new RestaurantDomain();
					vo.setI_rest(rs.getInt("i_rest"));
					vo.setNm(rs.getNString("nm"));
					vo.setLat(rs.getDouble("lat"));
					vo.setLng(rs.getDouble("lng"));
					list.add(vo);
				}
			}
		});
		return list;
	}
}
