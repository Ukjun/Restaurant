package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;

public class UserController {
	private UserService service;
	public UserController() {
		service = new UserService();
	}
	
	
	//     /user/login
	public String login(HttpServletRequest request) {
		String error = request.getParameter("error");
		if(error != null) {
			switch(error) {
			case"2":
				request.setAttribute("msg", "Id Check");
				break;
			case"3":
				request.setAttribute("msg", "Password Check");
				break;
			}
		}
		request.setAttribute(Const.TITLE, "로그인");
		request.setAttribute(Const.VIEW, "user/login");
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입");
		request.setAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
//	public String map(HttpServletRequest request) {
//		
//	}
	
	public String joinProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);
		
		int result = service.join(param);
		
		return "redirect:/user/login";
	}
	
	public String loginProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		UserVO param = new UserVO();
		
		System.out.println("user_id: " + user_id);
		System.out.println("user_pw: " + user_pw);
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		
		int result = service.login(param);
		
		if(result==1) {
			HttpSession hs = request.getSession();
			hs.setAttribute(Const.LOGIN_USER, param);
			
			return "redirect:/restaurant/map";
		}
		else {
			return "redirect:/user/login?user_id=" + user_id + "&error="+result;
		}
		
	}
	
	public String ajaxIdChk(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw("");
		
		
		int result = service.login(param);
		
		
		
		return String.format("ajax:{\"result\": %s}",result);
		
	}
	
	
	public String logOut(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		hs.invalidate();
		
		return "redirect:/user/login";
	}
}

