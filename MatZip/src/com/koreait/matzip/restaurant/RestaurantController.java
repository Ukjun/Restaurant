package com.koreait.matzip.restaurant;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.Const;
import com.koreait.matzip.MyUtils;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;
import com.mysql.cj.ParseInfo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RestaurantController {
	private RestaurantService service;
	
	public RestaurantController() {
		service = new RestaurantService();
	}
	
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "MenuTitle");
		request.setAttribute(Const.VIEW, "restaurant/map");
		return ViewRef.TEMP_MAP;
	}
	
	public String restReg(HttpServletRequest request) {
		final int i_m = 1; // Category Code
		request.setAttribute("categoryList", CommonDAO.selCodeList(i_m))
		;
		request.setAttribute(Const.TITLE, "Insert Category");
		request.setAttribute(Const.VIEW, "restaurant/restReg");
		return ViewRef.TEMP_MAP;
	}
	
	public String restregProc(HttpServletRequest request) {
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		double lat = MyUtils.getDoubleParameter("lat", request);
		double lng = MyUtils.getDoubleParameter("lng", request);
		
		// 현재 로그인한 유저의 i_user값을 들고온다
		UserVO Loginuser = SecurityUtils.getLoginUser(request);
		
		int cd_category = MyUtils.getIntParameter("cd_category",request);
		
		
		// 값 잘 넘어왔는지 테스트용도  출력 
		RestaurantVO rest = new RestaurantVO();
		System.out.println("nm : " + nm);
		System.out.println("addr : " + addr);
		System.out.println("lat : " + lat);
		System.out.println("lng : " + lng);
		System.out.println("cd_category : " + cd_category);
		System.out.println("i_user : " + Loginuser.getI_user());
		
		rest.setNm(nm);
		rest.setAddr(addr);
		rest.setLat(lat);
		rest.setLng(lng);
		rest.setCd_category(cd_category);
		rest.setI_user(Loginuser.getI_user());
		
		int result = service.insertCategory(rest);
		
		System.out.println("result : " + result);
		
		return "redirect:/restaurant/map";
	}
	
	public String ajaxGetList(HttpServletRequest request) {
		
		return "ajax:" + service.getRestList();
	}

	public String restDetail(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "Insert Category");
		request.setAttribute(Const.VIEW, "restaurant/restDetail");
		int i_rest = MyUtils.getIntParameter("i_rest", request);
//		UserVO Loginuser = SecurityUtils.getLoginUser(request);
		System.out.println("i_rest:" + i_rest);
//		System.out.println("i_user : " + Loginuser.getI_user());
		
		RestaurantDomain rest = new RestaurantDomain();
		rest.setI_rest(i_rest);
//		rest.setI_user(Loginuser.getI_user());
		
		request.setAttribute("data", service.detailList(rest));
		
		return ViewRef.TEMP_MAP;
	}
	
	
	public String addRecMenuProc(HttpServletRequest request) {
		String uploads = request.getRealPath("/res/img");
		MultipartRequest multi = null;
		String strI_rest = null;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		try {
			multi = new MultipartRequest(request, uploads,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
			strI_rest = multi.getParameter("i_rest");
			menu_nmArr = multi.getParameterValues("menu_nm");
			menu_priceArr = multi.getParameterValues("menu_price");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		if(menu_nmArr != null && menu_priceArr != null) {
			for(int i=0; i<menu_nmArr.length; i++) {
				System.out.println(i + ":" + menu_nmArr[i] + ", " + menu_priceArr[i]);
			}	
		}
		
		/*int i_rest = MyUtils.getIntParameter("i_rest", request);
		System.out.println("i_rest: " + i_rest);
		String[] menu_nmArr = request.getParameterValues("menu_nm");
		String[] menu_priceArr = request.getParameterValues("menu_price");
		//파일은 절대로 request.getParameter로 받을수없다.
*/		
//		for(int i=0; i<menu_nmArr.length; i++) {
//			System.out.println(i+ ": " + menu_nmArr[i] + " , " + menu_priceArr[i]);
//		}
		
		
		return "redirect:/restaurant/restDetail?i_rest=" + strI_rest;
	}
}
