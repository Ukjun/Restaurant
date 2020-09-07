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
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proc(request,response);
	}
	
	
	private void proc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = mapper.nav(request);
		
		request.getRequestDispatcher(temp).forward(request, response);
	}
}
