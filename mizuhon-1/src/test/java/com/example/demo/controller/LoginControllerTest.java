package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.awt.PageAttributes.MediaType;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
//@AutoConfigureMockMvc
public class LoginControllerTest {
//	@Autowired
	private MockMvc mockMvc;
	@Mock
	UserInfoRepository userInfoRepository;

	@InjectMocks
	LoginController controller;

	@Before
	public void init() {
		
	}
	
	@Test
	public void getLoginView_Success() throws Exception {
		String username = "admin";
		String pwd = "123";
		UserInfo userInfo = new UserInfo();
		userInfo.setPassword(pwd);
		userInfo.setUserId(1);
		userInfo.setUsername(username);

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		when(userInfoRepository.findByUsername(username)).thenReturn(userInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")
				.param("username", username)
				.param("Pwd", pwd);

		try {
			MvcResult result = mockMvc.perform(request)//
					.andExpect(model().attribute("userId", userInfo.getUserId())).andReturn();
//					.andDo(print())
//					.andExpect(status().isOk())
//					.andReturn();

//			ObjectMapper mapper = new ObjectMapper();
//			UserInfo actual = mapper.readValue(result.getResponse().getContentAsString(), UserInfo.class);
//
//			assertEquals(userInfo.getUserId(), actual.getUserId());
//			assertEquals(userInfo.getUsername(), actual.getUsername());
//			assertEquals(userInfo.getPassword(), actual.getPassword());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

	@Test
	public void getLoginView_Fail() throws Exception {
		String username = "admin";
		String pwd = "123";
		UserInfo userInfo = null;
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		when(userInfoRepository.findByUsername(username)).thenReturn(userInfo);
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")
				.param("username", username)
				.param("Pwd", pwd);

		try {
			MvcResult result = mockMvc.perform(request)//
					.andExpect(model().attribute("errmsg", "ユーザかパスワードが違います。"))
//					.andExpect(view().name("login"))
					.andReturn();

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

//	@Test
//	public void login_sucess()throws Exception{
//		String username="adimin";
//		String  pws= "1234";
//		UserInfo userInfo=UserInfo.builder()//
//				.username(username)//
//				.pwd(pwd)//
//				.build();
//		when(userInfoRepository.findByUsername(username)).thenReturn(userInfo);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders//
//				.post("login)//"
//				.param("username",username)//
//				.param("pwd")//
//				.accept(MediaType.APPLICATION_JSON);
//				mockMvc.perform(request)//
//				.andExpect(view().name("success"));
//	}
}
