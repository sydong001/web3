package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.BlogInfoRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.model.BlogInfo;

@Service
@Controller
public class BlogController {
	@Autowired
	private BlogInfoRepository blogInfoRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;

	@RequestMapping("/showblog")
	public String showBlog(@RequestParam("userId") Long userId, Model model) {

		BlogInfo blogInfo = blogInfoRepository.findByUserId(userId);
		if (blogInfo == null) {
			blogInfo = new BlogInfo();
			blogInfo.setUserId(userId);
			blogInfo.setTitle("");
			blogInfo.setOverview("");
			blogInfo.setEditorcontent("");
		}
		model.addAttribute("userId", userId);
		model.addAttribute("title", blogInfo.getTitle());
		model.addAttribute("overview", blogInfo.getOverview());
		model.addAttribute("editorcontent", blogInfo.getEditorcontent());

		UserInfo userInfo = userInfoRepository.findByUserId(userId);
		model.addAttribute("username", userInfo.getUsername());
		return "blog";

	}
}
