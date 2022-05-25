package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserInfoRepository;
@Service
@Controller
public class RedisterController {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@GetMapping("/register")
	public String getLoginView() {
		return "register";
	}
	@PostMapping("/register")
	public ModelAndView login(
			@RequestParam("username") String username,// 
			@RequestParam("Pwd") String pwd,//
			ModelAndView mv) {
		mv.addObject("username",username);
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setPassword(pwd);
		userInfoRepository.saveAndFlush(userInfo);
			mv.setViewName("login");
		return mv;
	}
}

