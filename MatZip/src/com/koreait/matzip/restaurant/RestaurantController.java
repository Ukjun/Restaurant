package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.Const;
import com.koreait.matzip.MyUtils;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantVO;
import com.mysql.cj.ParseInfo;

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
	
	public String restregProc(HttpServletRequest request) {
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		String strLat = request.getParameter("lat");
		String strLng = request.getParameter("lng");
		String strCd_category = request.getParameter("cd_category");
		
		// 현재 로그인한 유저의 i_user값을 들고온다
		int i_user = SecurityUtils.getLoginUser(request).getI_user();
		
		double lat = MyUtils.getDoubleParameter(request, strLat);
		double lng = MyUtils.getDoubleParameter(request, strLng);
		int cd_category = MyUtils.getIntParameter(request, strCd_category);
		
		
		// 값 잘 넘어왔는지 테스트용도  출력 
		RestaurantVO rest = new RestaurantVO();
		System.out.println("nm : " + nm);
		System.out.println("addr : " + addr);
		System.out.println("lat : " + lat);
		System.out.println("lng : " + lng);
		System.out.println("cd_category : " + cd_category);
		System.out.println("i_user : " + i_user);
		
		rest.setNm(nm);
		rest.setAddr(addr);
		rest.setLat(lat);
		rest.setLng(lng);
		rest.setCd_category(cd_category);
		rest.setI_user(i_user);
		
		int result = RestaurantService.insertCategory(rest);
		
		System.out.println("result : " + result);
		
		return "redirect:/restaurant/map";
	}
	
	public String ajacGetList(HttpServletRequest request) {
		
		
		return ViewRef.TEMP_MAP;
	}
}
