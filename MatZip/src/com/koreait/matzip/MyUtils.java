package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class MyUtils {
	
	public static int parseStringToInt(String str) {
		return parseStringToInt(str, 0);
	}
	public static int parseStringToInt(String str, int n) {
		try {
			Double.parseDouble(str);
			return Integer.parseInt(str);
		}catch(Exception e) {
			return n;
		}
	}
	
	public static double parseStringtoDouble(String str) {
		return Double.valueOf(str);
	}
}
