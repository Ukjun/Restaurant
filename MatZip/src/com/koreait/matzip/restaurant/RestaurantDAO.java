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
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
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

	public List<RestaurantDomain> selRestList() {
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
				while (rs.next()) {
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

	public RestaurantDomain detailList(RestaurantDomain rest) {
		final RestaurantDomain vo = new RestaurantDomain();
		String sql = " SELECT A.i_rest, A.nm, A.addr, A.i_user, A.cntHits "
				+ " , B.val AS cd_category_nm, ifnull(C.cnt, 0) AS cntFavorite " 
				+ " FROM t_restaurant A "
				+ " LEFT JOIN c_code_d B " 
				+ " ON A.cd_category = B.cd " 
				+ " AND B.i_m = 1 " 
				+ " LEFT JOIN ( "
				+ "		SELECT i_rest, COUNT(i_rest) AS cnt " 
				+ "		FROM t_user_favorite "
				+ "		WHERE i_rest = ? " 
				+ "		GROUP BY i_rest " + " ) C " 
				+ " ON A.i_rest = C.i_rest "
				+ " WHERE A.i_rest = ? ";

		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public List<?> selBoard(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, rest.getI_rest());
				ps.setInt(2, rest.getI_rest());
				// ps.setInt(2, rest.getI_user());
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					vo.setI_rest(rs.getInt("i_rest"));
					vo.setNm(rs.getNString("nm"));
					vo.setAddr(rs.getNString("addr"));
					// vo.setCd_category(rs.getInt("cd_category"));
					vo.setI_user(rs.getInt("i_user"));
					vo.setCntHits(rs.getInt("cntHits"));
					vo.setCd_category_nm(rs.getNString("cd_category_nm"));
				}

			}
		});
		return vo;
	}

	public List<RestaurantRecommendMenuVO> selMenuList(int i_rest) {
		List<RestaurantRecommendMenuVO> list = new ArrayList();
		String sql = "select i_rest, seq, menu_pic " 
				+ "from t_restaurant_menu " 
				+ "where i_rest = ?";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public List<?> selBoard(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, i_rest);
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				while (rs.next()) {
					RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();
					vo.setI_rest(i_rest);
					vo.setSeq(rs.getInt("seq"));
					vo.setMenu_pic(rs.getNString("menu_pic"));
					list.add(vo);
				}
			}
		});

		return list;
	}

	public List<RestaurantRecommendMenuVO> selRecommendMenuList(int i_rest) {
		List<RestaurantRecommendMenuVO> list = new ArrayList();

		String sql = "select seq, menu_nm, menu_price, menu_pic " 
				+ "from t_restaurant_recommend_menu "
				+ "where i_rest = ?";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public List<?> selBoard(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, i_rest);
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				while (rs.next()) {
					RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();
					vo.setSeq(rs.getInt("seq"));
					vo.setMenu_nm(rs.getNString("menu_nm"));
					vo.setMenu_price(rs.getInt("menu_price"));
					vo.setMenu_pic(rs.getNString("menu_pic"));
					list.add(vo);
				}
			}
		});

		return list;
	}

	public void addHits(final int i_rest) {
		String sql = "update t_restaurant set cntHits = cntHits + 1 where i_rest =?";
		JdbcTemplate.excuteupdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(Connection conn, PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, i_rest);
				;
			}
		});

	}

	public int insRecommendMenu(RestaurantRecommendMenuVO vo) {

		String sql = " INSERT INTO t_restaurant_recommend_menu " 
				+ " (seq, i_rest, menu_nm, menu_price, menu_pic) "
				+ " SELECT IFNULL(MAX(seq), 0) + 1, ?, ?, ?, ? " 
				+ " FROM t_restaurant_recommend_menu "
				+ " WHERE i_rest = ? ";
		// TODO Auto-generated method stub
		return JdbcTemplate.excuteupdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(Connection conn, PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, vo.getI_rest());
				ps.setNString(2, vo.getMenu_nm());
				ps.setInt(3, vo.getMenu_price());
				ps.setNString(4, vo.getMenu_pic());
				ps.setInt(5, vo.getI_rest());
			}
		});
	}

	public int delRecommendMenu(RestaurantRecommendMenuVO param) {
		// TODO Auto-generated method stub
		String sql = " DELETE A " + " FROM t_restaurant_recommend_menu A " 
				+ " INNER JOIN t_restaurant B "
				+ " ON A.i_rest = B.i_rest " 
				+ " AND B.i_user = ? " 
				+ " WHERE A.i_rest = ? AND A.seq = ? ";
		return JdbcTemplate.excuteupdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(Connection conn, PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_rest());
				ps.setInt(3, param.getSeq());
				;
			}
		});
	}
	
	public int delMenu(RestaurantRecommendMenuVO param) {
		// TODO Auto-generated method stub
		String sql = " DELETE A " + " FROM t_restaurant_menu A " 
				+ " INNER JOIN t_restaurant B "
				+ " ON A.i_rest = B.i_rest " 
				+ " AND B.i_user = ? " 
				+ " WHERE A.i_rest = ? AND A.seq = ? ";
		return JdbcTemplate.excuteupdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(Connection conn, PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_rest());
				ps.setInt(3, param.getSeq());
				;
			}
		});
	}

	public int insRestaurantMenu(RestaurantRecommendMenuVO param) {
		String sql = "insert into t_restaurant_menu(i_rest, seq, menu_pic) " 
				+ "select ?, ifnull(max(seq),0)+1, ? "
				+ "from t_restaurant_menu " 
				+ "where i_rest=? ";
		return JdbcTemplate.excuteupdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(Connection conn, PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, param.getI_rest());
				ps.setNString(2, param.getMenu_pic());
				ps.setInt(3, param.getI_rest());

			}
		});
	}
}
