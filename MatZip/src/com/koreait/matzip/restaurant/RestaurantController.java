package com.koreait.matzip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.Const;
import com.koreait.matzip.FileUtils;
import com.koreait.matzip.MyUtils;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;
//import com.mysql.cj.ParseInfo;
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
		request.setAttribute("categoryList", CommonDAO.selCodeList(i_m));
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

		int cd_category = MyUtils.getIntParameter("cd_category", request);

		// 값 잘 넘어왔는지 테스트용도 출력
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
		RestaurantVO param = new RestaurantVO();
		int i_rest = MyUtils.getIntParameter("i_rest", request);
		
		param.setI_rest(i_rest);
		System.out.println("i_rest:" + i_rest);

		
		RestaurantDomain rest = new RestaurantDomain();
		rest.setI_rest(i_rest);
		//common.css가 아니라 restaurant.css를 들고온다
		request.setAttribute("css", new String[] {("restaurant")});
		List<RestaurantRecommendMenuVO> list1 = service.getRecommendMenuList(i_rest);
		List<RestaurantRecommendMenuVO> list2 = service.getMenuList(i_rest);
		request.setAttribute("recommendMenuList", list1);
		request.setAttribute("menuList", list2);
		request.setAttribute("data", service.detailList(rest));

		return ViewRef.TEMP_MAP;
	}

	public String addRecMenuProc(HttpServletRequest request) {
		int i_rest = service.addRecMenus(request);
		return "redirect:/restaurant/restDetail?i_rest=" + i_rest;
	}
	public String addMenuProc(HttpServletRequest request) {
		int i_rest = service.addMenus(request);
		String fileNm = request.getParameter("fileNm");
		String savePath = "/res/img/restaurant";
		String tempPath = request.getServletContext().getRealPath(savePath + "/" + i_rest + "/menu/" + fileNm);
		File file = new File(tempPath);
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("Delete Success");
			}else {
				System.out.println("Delete Fail");
			}
		}else {
			System.out.println("Not found File");
		}
		
		
		return "redirect:/restaurant/restDetail?i_rest=" + i_rest;
	}

	public String ajaxDelRecMenu(HttpServletRequest request) {
		int i_rest = MyUtils.getIntParameter("i_rest", request);
		int seq = MyUtils.getIntParameter("seq", request);
		String fileNm = request.getParameter("fileNm");
		int i_user = SecurityUtils.getLoginUserPk(request);
		
		RestaurantRecommendMenuVO param = new RestaurantRecommendMenuVO();
		param.setI_rest(i_rest);
		System.out.println("i_rest: " + i_rest);
		param.setSeq(seq);
		System.out.println("seq: " + seq);
		param.setI_user(i_user);
		System.out.println("i_user: " + i_user);
		
		// 파일까지 같이 삭제
		int result = service.delRecMenu(param);
		String savePath = "/res/img/restaurant";
		String tempPath = request.getServletContext().getRealPath(savePath + "/" + i_rest + "/" + fileNm);
		
		File file = new File(tempPath);
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("Delete Success");
			}else {
				System.out.println("Delete Fail");
			}
		}else {
			System.out.println("Not found File");
		}
		return String.format("ajax:{\"result\": %s}",result);
	}

	public String ajaxDelMenu(HttpServletRequest request) {
		int i_rest = MyUtils.getIntParameter("i_rest", request);
		int seq = MyUtils.getIntParameter("seq", request);
		String fileNm = request.getParameter("fileNm");
		int i_user = SecurityUtils.getLoginUserPk(request);
		
		RestaurantRecommendMenuVO param = new RestaurantRecommendMenuVO();
		param.setI_rest(i_rest);
		System.out.println("i_rest: " + i_rest);
		param.setSeq(seq);
		System.out.println("seq: " + seq);
		param.setI_user(i_user);
		System.out.println("i_user: " + i_user);
		
		// 파일까지 같이 삭제
		int result = service.delMenu(param);
		String savePath = "/res/img/restaurant";
		String tempPath = request.getServletContext().getRealPath(savePath + "/" + i_rest + "/menu/" + fileNm);
		
		File file = new File(tempPath);
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("Delete Success");
			}else {
				System.out.println("Delete Fail");
			}
		}else {
			System.out.println("Not found File");
		}
		return String.format("ajax:{\"result\": %s}",result);
	}
	
}
