package com.koreait.matzip.restaurant;

import java.util.List;

import com.google.gson.Gson;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantService {
	
	public static int insertCategory(RestaurantVO rest) {
		
		System.out.println(rest.getAddr());
		
		
		return RestaurantDAO.insertCate(rest);
	}
	
	public String getRestList(){
		List<RestaurantDomain> list = RestaurantDAO.selRestList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
