package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.user.UserController;

public class HandlerMapper {
	private UserController userCon;

	public HandlerMapper() {
		userCon = new UserController();
	}

	public String nav(HttpServletRequest request) {
		String[] uriArr = request.getRequestURI().split("/");

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
			}
		}

		return "404"; // NotFound
	}
}