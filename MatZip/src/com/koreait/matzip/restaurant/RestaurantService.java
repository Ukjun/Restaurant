package com.koreait.matzip.restaurant;

import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantService {
	
	public static int insertCategory(RestaurantVO rest) {
		
		System.out.println(rest.getAddr());
		
		
		return RestaurantDAO.insertCate(rest);
	}
}
