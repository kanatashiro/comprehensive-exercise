package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.util.XlsDataSetLoader;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.DataSetLoader;

@SpringBootTest
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
		TransactionDbUnitTestExecutionListener.class // @DatabaseSetupや@ExpectedDatabaseなどを使えるように指定
})

class RegistControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

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
	@DisplayName("①画面表示(正常系)")
	void testIndex() throws Exception {
		mockMvc.perform(post("/regist")).andExpect(view().name("regist")).andReturn();
	}


	@Test
	@Transactional
	@DisplayName("②メール登録画面遷移(メールアドレスが24時間以内にURLを発行している場合入力画面に戻る)")
    @DatabaseSetup("classpath:regist_url_01.xlsx")
	//@ExpectedDatabase(value = "classpath:regist_url_01.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
	void testRegistConfirm() throws Exception {
		 MvcResult mvcResult = mockMvc.perform(post("/regist/mailkannryou")
                 .param("user_email","aa@aa"))
         .andExpect(view().name("regist"))
         .andReturn();
	
	}
	@Test
	@DisplayName("③メール登録完了画面遷移(メールアドレスが重複している場合メール入力画面に戻る)")
    @DatabaseSetup("classpath:users_01.xlsx")
	//@ExpectedDatabase(value = "classpath:regist_url_01.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
	void testRegistConfirm2() throws Exception {
		 MvcResult mvcResult = mockMvc.perform(post("/regist")
                 .param("user_email","aa@aa"))
         .andExpect(view().name("regist"))
         .andReturn();
		
	}

@Test
@Transactional
@DisplayName("④メール登録完了画面遷移(メールアドレスを空白で入力した場合、入力画面に戻る)")
@DatabaseSetup("classpath:regist_url_初期値.xlsx")
//@ExpectedDatabase(value = "classpath:regist_url_初期値.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
void testRegistConfirm3() throws Exception {
	 MvcResult mvcResult = mockMvc.perform(post("/regist/mailkannryou")
             .param("user_email",""))
     .andExpect(view().name("regist"))
     .andReturn();
}
		

@Test
@Transactional
@DisplayName("⑤メール登録完了画面遷移(メールアドレスが登録できる)(正常系)")
@DatabaseSetup("classpath:regist_url_初期値.xlsx")
@ExpectedDatabase(value = "classpath:regist_url_02.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
//入力した内容
void testRegistConfirm4() throws Exception {
	 MvcResult mvcResult = mockMvc.perform(post("/regist/mailkannryou")
             .param("user_email","m@m"))
     .andExpect(view().name("redirect:/regist/index2"))
     .andReturn();
}

@Test
@Transactional
@DisplayName("⑥メール登録完了画面遷移後にメールが飛んで完了画面に遷移(正常系)")
@DatabaseSetup("classpath:regist_url_初期値.xlsx")
//@ExpectedDatabase(value = "classpath:regist_url_初期値.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
void testRegistConfirm5() throws Exception {
	 MvcResult mvcResult = mockMvc.perform(post("/regist/mailkannryou")
             .param("user_email","tcc@k"))
     .andExpect(view().name("redirect:/regist/index2"))
     .andReturn();
	
}
}
	
	
