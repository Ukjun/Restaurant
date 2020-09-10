package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInterceptor {
	public static String routerChk(HttpServletRequest request) {
		String[] chkUriArr = {"login", "loginProc", "join", "joinProc","ajaxIdChk"}; // Maphandler에 있는 switch 경우
		
		boolean isLogout = SecurityUtils.isLogout(request);
		
		//null이 리턴되면 아무일 없음
		// 문자열이 리턴되면 그 문자열로 sendRedirect된다.
		String[] targetUri = request.getRequestURI().split("/"); // 주소를 "/" 단위로 분리해서 배열에 저장

		if (targetUri.length < 3) {return null;}
		if (isLogout) { //로그아웃상태
			if (targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri: chkUriArr) {
					if(uri.equals(targetUri[2])) { // 2번째의 값이 배열과 일치하면 null
						return null;
					}
				}
			}
			return "/user/login";
		} else { // 로그인상태
			if (targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri: chkUriArr) {
					if(uri.equals(targetUri[2])) { // 2번째의 값이 배열과 일치하면 null
						return "/restaurant/map";
					}
				}
			}
			return null;
		}
	}
}
