package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.user.UserController;

public class HandlerMapper {
	private UserController user;
	public HandlerMapper() {
		
	}
	
	public String nav(HttpServletRequest request) {
		
		
		String[] uriArr = request.getRequestURI().split("/");
		
		if(uriArr.length <2) {
			return null;
		}
		
		switch(uriArr[1]) {
		case ViewRef.URI_USER:
			switch(uriArr[2]) {
			case "login":
				user.login(request);
				break;
			}
			break;
		}
		
		return null;
	}
}
