package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;

public class UserController {
	public String login(HttpServletRequest request) {
//		request.setAttribute(Const.TEMPLATE, null);
		
		
		//타이틀
		request.setAttribute(Const.TITLE, "로그인");
		
		request.setAttribute(Const.VIEW, "user/login");
		
		
		//어떤 Template을 열지
		return ViewRef.TEMP_DEFAULT;
	}
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.VIEW, "user/join");
		
		return ViewRef.TEMP_DEFAULT;
	}
	
	
	public String joinProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		UserVO vo = new UserVO();
		vo.setUser_id(user_id);
		vo.setUser_pw(user_pw);
		vo.setNm(nm);
		
		
		return "redirect:/user/login";
	}
	
	public String loginProc(HttpServletRequest request) { 
		  return "redirect:/user/join"; 
	}
	 
}

