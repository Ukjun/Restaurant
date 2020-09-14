package com.koreait.matzip.restaurant;

import java.util.List;

import com.google.gson.Gson;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantService {
	private RestaurantDAO dao;
	
	public RestaurantService() {
		dao = new RestaurantDAO();
	}
	
	public int insertCategory(RestaurantVO rest) {
		
		System.out.println(rest.getAddr());
		
		
		return dao.insertCate(rest);
	}
	
	public String getRestList(){
		List<RestaurantDomain> list = dao.selRestList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
