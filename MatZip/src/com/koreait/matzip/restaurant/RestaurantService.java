package com.koreait.matzip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.koreait.matzip.FileUtils;
import com.koreait.matzip.MyUtils;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
import com.koreait.matzip.vo.RestaurantVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RestaurantService {
	private RestaurantDAO dao;

	public RestaurantService() {
		dao = new RestaurantDAO();
	}

	public int insertCategory(RestaurantVO rest) {

		System.out.println(rest.getAddr());

		return dao.insertCate(rest);
	}

	public String getRestList() {
		List<RestaurantDomain> list = dao.selRestList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public RestaurantDomain detailList(RestaurantDomain rest) {

		dao.addHits(rest.getI_rest());

		return dao.detailList(rest);
	}

	public int addRecMenus(HttpServletRequest request) {

		// 업로드 경로
		// aplication 내장객체를 얻어옴
		String savePath = "/res/img/restaurant"; // 상대적 경로 
		String tempPath = request.getServletContext().getRealPath(savePath + "/temp");
		FileUtils.makeFolder(tempPath);
		// String uploads = request.getRealPath("/res/img");

		int maxFileSize = 10_485_760;
		MultipartRequest multi = null;
		int i_rest = 0;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		List<RestaurantRecommendMenuVO> list = null;
		try {
			// MultipartRequest 웹페이지에서 파일 업로드할때 쓰는 객체
			// DefaultFileRenamePolicy 파일이름 생성시 중복방지
			System.out.println("start");

			multi = new MultipartRequest(request, tempPath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
			i_rest = MyUtils.getIntParameter("i_rest", multi);
			System.out.println("i_rest: " + i_rest);
			//다중으로 입력시 0번부터 입력이 시작된다.
			menu_nmArr = multi.getParameterValues("menu_nm");
			menu_priceArr = multi.getParameterValues("menu_price");

			if(menu_nmArr== null || menu_priceArr == null) {
				return i_rest;
			}
			list = new ArrayList();
			for (int i = 0; i < menu_nmArr.length; i++) {
				System.out.println(i + ":" + menu_nmArr[i] + ", " + menu_priceArr[i]);
				RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();
				vo.setI_rest(i_rest);
				vo.setMenu_nm(menu_nmArr[i]);
				vo.setMenu_price(MyUtils.parseStringToInt(menu_priceArr[i]));
				list.add(vo);
			}

			String targetPath = request.getServletContext().getRealPath(savePath + "/" + i_rest);
			FileUtils.makeFolder(targetPath);

			System.out.println("targetPath : " + targetPath);

			String fileNm = "";
			String saveFileNm = "";

			Enumeration<?> files = multi.getFileNames();

			while (files.hasMoreElements()) { // Element가 더 있는지 없는지 확인(제일 마지막들간 내용부터 찾는다)
				System.out.println("!!들어옴!!");
				String key = (String) files.nextElement(); // 키값이 넘어온다
				System.out.println("str: " + key);

				fileNm = multi.getFilesystemName(key);
				System.out.println("fileNm: " + fileNm);
				// saveFileNm = multi.getOriginalFileName(key);
				// 파일 확장자명 가져오기
				// 문자열의 인덱스값(왼쪽기준) : ~.indexOf();
				// 오른쪽 기준 : ~.lastIndexOf();

				if (fileNm != null) {
					String ext = FileUtils.getExt(fileNm);
					saveFileNm = UUID.randomUUID() + ext;

					System.out.println("saveFileNm : " + saveFileNm);
					// 파일을 저장시키고 올릴때 oldFile이름이 newFile이름으로 덮어지게된다.
					// UUID.randomUUID() : 파일이름을 랜덤으로 정하게 된다 (중복 방지 + 매번 다르게 이름이 지어진다.)
					File oldFile = new File(tempPath + "/" + fileNm);
					// 파일을 저장할때 이미지이름 -> 확장자까지 DB에 저장
					File newFIle = new File(targetPath + "/" + saveFileNm);

					oldFile.renameTo(newFIle);

					int idx = MyUtils.parseStringToInt(key.substring(key.lastIndexOf("_") + 1));
					RestaurantRecommendMenuVO vo = list.get(idx);
					vo.setMenu_pic(saveFileNm);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (list != null) {
			for (RestaurantRecommendMenuVO vo : list) {
				dao.insRecommendMenu(vo);

				System.out.println(vo.getI_rest());
			}
		}

		return i_rest;
	}
	public List<RestaurantRecommendMenuVO> getRecommendMenuList(int i_rest) {
		return dao.selRecommendMenuList(i_rest);
	}
}
