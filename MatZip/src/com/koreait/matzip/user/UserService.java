package com.koreait.matzip.user;

import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.vo.UserVO;

public class UserService {
	private final UserDAO dao;
	
	public UserService() {
		dao = new UserDAO();
	}
	
	public int join(UserVO param) {
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String encryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setUser_pw(encryptPw);
		param.setSalt(salt);
		
		
		return dao.join(param);
	}
	
	// result = 1: 성공 3: 비밀번호 틀림 2: 아이디틀림 
	public int login(UserVO param) {
//		String user_id = param.getUser_id();
//		String pw = param.getUser_pw();
//		
//		param.setUser_id(user_id);
//		param.setUser_pw(pw);
//		String user_id = param.getUser_id();
//		System.out.println("before id : " + user_id);
		
		UserVO result = new UserVO();
		
		result = dao.selUser(param);
		
		
		
		System.out.println("i_user : " + result.getI_user());
		// 아이디 없음
		if(result.getI_user()==0) {
			return 2;
		}else {
			String salt = result.getSalt();

			String encryptPw = SecurityUtils.getEncrypt(param.getUser_pw(), salt);
			System.out.println("before pw : " + result.getUser_pw());
			System.out.println("after pw : " + encryptPw);
			if(encryptPw.equals(result.getUser_pw())) {
				param.setUser_pw(null);
				param.setI_user(result.getI_user());
				param.setNm(result.getNm());
				param.setProfile_img(result.getProfile_img());
				
				param = result;
				
				return 1;
			}else { // 비밀번호 틀림 
				return 3;
			}
		}
	}
}
