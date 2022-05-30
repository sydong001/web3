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
public class LoginController {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@GetMapping("/login")
	public String getLoginView() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(
			@RequestParam("username") String username,// 
			@RequestParam("Pwd") String pwd,//
			ModelAndView mv) {
		UserInfo userInfo = userInfoRepository.findByUsername(username);
		if ( userInfo !=null && pwd.equals(userInfo.getPassword())) {

			mv = new ModelAndView("redirect:/showblog");   
			mv.addObject("userId", userInfo.getUserId());
		    return mv;
		} else {   
			mv.addObject("errmsg", "ユーザかパスワードが違います。");
			mv.setViewName("login");
			return mv;
		}
	}
}

