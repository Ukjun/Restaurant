package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.restaurant.RestaurantController;
import com.koreait.matzip.user.UserController;

public class HandlerMapper {
	private UserController userCon;
	private RestaurantController restCon;

	public HandlerMapper() {
		userCon = new UserController();
		restCon = new RestaurantController();
	}

	public String nav(HttpServletRequest request) {
		String[] uriArr = request.getRequestURI().split("/");
		for (int i = 0; i < uriArr.length; i++) {
			System.out.println("uriArr[" + i + "] : " + uriArr[i]);
		}
		if (uriArr.length < 3) {
			return "405"; // Error
		}

		if ("ajax".equals(uriArr[1]))
			return "ajax";
		switch (uriArr[1]) {
		case ViewRef.URI_USER:
			System.out.println("uriArr[2]:" + uriArr[2]);
			switch (uriArr[2]) {
			case "login":
				return userCon.login(request);
			case "join":
				return userCon.join(request);
			case "joinProc":
				System.out.println("join result : " + userCon.joinProc(request));
				return userCon.joinProc(request);
			case "loginProc":
				System.out.println("login result : " + userCon.loginProc(request));
				return userCon.loginProc(request);
			case "ajaxIdChk":
				return userCon.ajaxIdChk(request);
			case "logout":
				return userCon.logOut(request);
			}
		case ViewRef.URI_RESTURANT:
			switch (uriArr[2]) {
			case "map":
				return restCon.restMap(request);
			case "restReg":
				return restCon.restReg(request);
			case "restregProc":
				return restCon.restregProc(request);
			case "restDetail":
				return restCon.restDetail(request);
			case "addMenuProc":
				return restCon.addMenuProc(request);
			case "addRecMenus":
				return restCon.addRecMenuProc(request);
			case "ajaxGetList":
				return restCon.ajaxGetList(request);
			case "ajaxDelRecMenu":
				return restCon.ajaxDelRecMenu(request);
			case "ajaxDelMenu":
				return restCon.ajaxDelMenu(request);
			}

		}

		return "404"; // NotFound
	}
}