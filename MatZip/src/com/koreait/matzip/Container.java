package com.koreait.matzip;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@WebServlet("/")
public class Container extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlerMapper mapper;

	public Container() {
		mapper = new HandlerMapper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proc(request, response);
	}

	private void proc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되어 있으면 login, join은 접속 x
		
//		boolean isLogout = SecurityUtils.isLogout(request);
		String routerCheckResult = LoginChkInterceptor.routerChk(request);
		
		if(routerCheckResult != null) {
			response.sendRedirect(routerCheckResult);
			return;
		}
			
		
		
		System.out.println("------");
		//로그인이 안 되어 있으면 전부 로그인이 되어 있어야함
		String temp = mapper.nav(request);
		
		System.out.println("temp: " + temp);
		System.out.println("const.view : " + Const.VIEW);
		if(temp.indexOf(":") >= 0) {
			String prefix = temp.substring(0, temp.indexOf(":"));
			String value = temp.substring(temp.indexOf(":") + 1);
			System.out.println("prefix : " + prefix);
			System.out.println("value : " + value);
			
			if("redirect".equals(prefix)) {
				//value값 "/"빠져서 재설정 
				value = temp.substring(temp.indexOf(":")+1);
				response.sendRedirect(value);
				return;
			} else if("ajax".equals(prefix)) {
				
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				
				
				// 서블릿 형태에서 바로 응답해오는 것 jsp파일이 PrintWriter로 변환되서 응답된다.
				PrintWriter out = response.getWriter();
				
				System.out.println("value : " + value);
				out.print(value);
				return;
			}
		}

//		if(temp.indexOf("/")>=0) {
//			String isRedirect = temp.substring(0,temp.indexOf("/"));
//			
//			if("redirect:".equals(isRedirect)) {
//				response.sendRedirect(temp.substring(temp.indexOf("/")));
//				return;
//			}
//		}

		switch (temp) {
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
