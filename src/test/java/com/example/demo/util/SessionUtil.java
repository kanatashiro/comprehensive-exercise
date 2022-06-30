package com.example.demo.util;
	
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.mock.web.MockHttpSession;

import com.example.demo.domain.User;





public class SessionUtil {
	public static MockHttpSession createFinishedIdandFinishedSession() {
		Map<String, Object> sessionMap = new LinkedHashMap<String, Object>();
		sessionMap.put("user_email","aa@aa");
		return createMockHttpSession(sessionMap);

	}
	

private static MockHttpSession createMockHttpSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		return null;
	}





// PasswordResetTestのテストで使いました
public static MockHttpSession createPasswordResetSession() {
	Map<String, Object> sessionMap = new LinkedHashMap<String, Object>();

	sessionMap.put("user_email", "aa@aa");
	sessionMap.put("uniqueUrl", "4bfd7647-c8f7-43a8-8b87-945aedad3b0c");
	return createMockHttpSession(sessionMap);
}






public class SessionUtil2 {
	public static MockHttpSession createFinishedIdandFinishedSession() {
		Map<String, Object> sessionMap = new LinkedHashMap<String, Object>();
		sessionMap.put("user_email","aa@aa");
		
		return createMockHttpSession(sessionMap);

	}
	

	}
	
	public static MockHttpSession createUserIdAndUserSession() {
		Map<String, Object> sessionMap = new LinkedHashMap<String, Object>();
		User user = new User();
		user.setId(1);
		user.setName("テストユーザ");
		user.setAddress("テスト住所");
		user.setZipcode("1111111");
		user.setTelephone("テスト電話番号");
		sessionMap.put("userId", user.getId());
		sessionMap.put("user", user);
		return createMockHttpSession(sessionMap);
	}
	
	
	
}

	


	
	
	

