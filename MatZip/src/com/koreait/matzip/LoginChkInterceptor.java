package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInterceptor {
	public static String routerChk(HttpServletRequest request) {
		String[] chkUriArr = {"login", "loginProc", "join", "joinProc","ajaxIdChk"};
		
		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");

		if (targetUri.length < 3) {return null;}
		if (isLogout) {
			if (targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri: chkUriArr) {
					if(uri.equals(targetUri[2])) {
						return null;
					}
				}
			}
			return "/user/login";
		} else { // 로그인상태
			if (targetUri[1].equals(ViewRef.URI_USER)) {
				switch (targetUri[2]) {
				case "login":
				case "join":
					return "/restaurant/map";
				}
			}

			return null;
		}
	}
}
