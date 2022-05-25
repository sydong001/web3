package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.BlogInfoRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.model.BlogInfo;

public class EditorController {
	@Autowired
	private BlogInfoRepository blogInfoRepository;

	@GetMapping("/editor")
	public String getLoginView() {
		return "editor";
	}

	@PostMapping("/editor")
	public ModelAndView login(@RequestParam("userId") Long userId, //
			@RequestParam("title") String title, //
			@RequestParam("overview") String overview, //
			@RequestParam("editorcontent") String editorcontent, //
			ModelAndView mv) {
		mv.addObject("userId", userId);
		BlogInfo blogInfo = blogInfoRepository.findByUserId(userId);
		
			mv.setViewName("editor");
		return mv;
	}
}
