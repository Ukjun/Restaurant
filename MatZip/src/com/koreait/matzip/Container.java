package com.koreait.matzip;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class Container extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HandlerMapper mapper;
	
	//Container 클래스가 생성될때 생성자가 생성 Tomcat이 생성 
	public Container() {
		mapper = new HandlerMapper();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 브라우저 uri랑 같은지 확인
		System.out.println("uri: " + request.getRequestURI());
//		System.out.println("url: " + request.getRequestURL());
		
		// uri의 값을 "/"로 분리해 문자배열에 저장
		String[] uriArr = request.getRequestURI().split("/");
		
		// for문 돌려서 배열값 추출
		for(int i=0; i<uriArr.length; i++) {
			System.out.println("uriArr["+ i+ "]: " + uriArr[i]);
		}
		proc(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proc(request,response);
	}
	
	
	private void proc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = mapper.nav(request);
		System.out.println("temp: " + temp);
		
		
		if(temp.indexOf("/")>=0) {
			String isRedirect = temp.substring(0,temp.indexOf("/"));
			System.out.println("isRedirect : " + isRedirect);
			if("redirect:".equals(isRedirect)) {
				response.sendRedirect(temp.substring(temp.indexOf("/")));
				return;
			}
			
		}
		
		
		switch(temp) {
		case "405":
			temp = "/WEB-INF/view/error.jsp";
			break;
		case "404":
			temp = "/WEB-INF/view/notFound.jsp";
			break;
		}
		
		request.getRequestDispatcher(temp).forward(request, response);
	}
}
