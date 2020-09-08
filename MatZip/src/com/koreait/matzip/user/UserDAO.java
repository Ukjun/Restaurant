package com.koreait.matzip.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.db.JdbcUpdateInterface;
import com.koreait.matzip.vo.UserVO;

public class UserDAO {
	public int join(UserVO param) {

		String sql = "insert into t_user " + "(user_id, user_pw, nm, salt) " + "values (?, ?,?,?)";

		return JdbcTemplate.excuteupdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(Connection conn, PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getSalt());
			}
		});
	}

	public UserVO selUser(UserVO param) { // i_user,user_id
		UserVO result = new UserVO();

		String sql = "select i_user, user_id, user_pw, nm, salt, profile_img, r_dt, m_dt " 
				+ "from t_user " 
				+ "where ";
		if (param.getI_user() > 0) {
			sql += "i_user = " + param.getI_user();
		}else if (param.getUser_id() != null && !"".equals(param.getUser_id())) {
			sql += "user_id = '" + param.getUser_id() + "' ";
		}
		
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
				if(rs.next()) {
					result.setI_user(rs.getInt("i_user"));
					result.setUser_id(rs.getNString("user_id"));
					result.setUser_pw(rs.getNString("user_pw"));
					result.setNm(rs.getNString("nm"));
					result.setSalt(rs.getNString("salt"));
					result.setProfile_img(rs.getNString("profile_img"));
					result.setR_dt(rs.getString("r_dt"));
					result.setM_dt(rs.getString("m_dt"));
				}
				
			}
		});
		

		return result;
	}
}
