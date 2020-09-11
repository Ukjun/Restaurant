package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;

public class RestaurantController {
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "MenuTitle");
		request.setAttribute(Const.VIEW, "restaurant/map");
		return ViewRef.TEMP_MAP;
	}
	
	public String restReg(HttpServletRequest request) {
		final int i_m = 1; // Category Code
		request.setAttribute("categoryList", CommonDAO.selCodeList(i_m));
		request.setAttribute(Const.TITLE, "Insert Category");
		request.setAttribute(Const.VIEW, "restaurant/restReg");
		return ViewRef.TEMP_MAP;
	}
	
	public String getLatLng(HttpServletRequest request) {
		
		
		return ViewRef.TEMP_MAP;
	}
}
