package com.koreait.matzip.db;

import java.sql.*;
import java.util.*;

public interface JdbcSelectInterface {
	void prepared(PreparedStatement ps)throws SQLException;
	void executeQuery(ResultSet rs) throws SQLException;
	List<?> selBoard(ResultSet rs);
}
