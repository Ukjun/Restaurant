package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;

public class UserController {
	public String login(HttpServletRequest request) {
//		request.setAttribute(Const.TEMPLATE, null);
		request.setAttribute(Const.TITLE, "Login");
		request.setAttribute(Const.VIEW, "user/login");
		return ViewRef.TEMP_DEFAULT;
	}
}
