package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.util.SessionUtil;
import com.example.demo.util.XlsDataSetLoader;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
		TransactionDbUnitTestExecutionListener.class // @DatabaseSetupや@ExpectedDatabaseなどを使えるように指定
})

class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private  MockMvc mockMvc;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Transactional
	@DisplayName("①ユーザー登録画面遷移(URLが一致ではない時エラー画面に遷移)")
	@DatabaseSetup("classpath:regist_url_01.xlsx")
	//@ExpectedDatabase(value = "classpath:regist_url_初期値.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
	void insert1() throws Exception {
		 MvcResult mvcResult = mockMvc.perform(post("/touroku/aaa")
	             .param("unique_key","1234r5t6u8i9"))
	     .andExpect(view().name("jaja"))
	     .andReturn();
		 
	}
	@Test
	@Transactional
	@DisplayName("②ユーザー登録画面遷移(keyは合っているが、URLが24時間以上経過してるのでエラー画面に遷移)")
	@DatabaseSetup("classpath:regist_url_01.xlsx")
	//@ExpectedDatabase(value = "classpath:regist_url_初期値.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
	void insert2() throws Exception {
		 MvcResult mvcResult = mockMvc.perform(post("/touroku/aaa")
	             .param("unique_key","4bfd7647-c8f7-43a8-8b87-945aedad3b0c")
				 .param("regist_date","2021-06-28 21:07:27.376"))
	     .andExpect(view().name("jaja"))
	     .andReturn();
		
	}
//	@Test
//	@Transactional
//	@DisplayName("③ユーザー登録画面遷移(Keyが合っていて、URLが24時間以内で登録画面遷移)")
//	@DatabaseSetup("classpath:regist_url_01.xlsx")
//	//@ExpectedDatabase(value = "classpath:regist_url_初期値.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
//	void insert3() throws Exception {
//		 MvcResult mvcResult = mockMvc.perform(post("/touroku/aaa")
//	             .param("unique_key","4bfd7647-c8f7-43a8-8b87-945aedad3b0c")
//				 .param("regist_date","2022-06-29 10:07:27.376")
//				 .param("user_email","aa@aa"))
//	     .andExpect(view().name("user"))
//	     .andReturn();
//
//}
	@Test
	@Transactional
	@DisplayName("④パスワードと、確認用のパスワードが間違っている場合)")
	//@DatabaseSetup("classpath:regist_url_01.xlsx")
	//@ExpectedDatabase(value = "classpath:regist_url_初期値.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
	void insert4() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/touroku/tourokukannryou").param("Password", "testnanoyo")
				.param("confirmpassword", "matigainanoyo")).andExpect(view().name("user")).andReturn();
	}
	
	
	
	@Test
	@Transactional
	@DisplayName("⑤ユーザー情報登録)")
	@DatabaseSetup("classpath:regist_touroku初期値.xlsx")
	//@ExpectedDatabase(value = "classpath:users_01.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
	void insert5() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/touroku/tourokukannryou")
				.param("name", "田代")
				.param("ruby", "たしろ")
				.param("zipcode", "252-1107")
				.param("address", "神奈川県")
				.param("telehone", "090-2901-8489")
				.param("Password", "heysayjump")
				.param("confirmpassword", "heysayjump"))
				.andExpect(view().name("redirect:/touroku/index3")).andReturn();
		
}
	


	
	
	
}

	
	
	

