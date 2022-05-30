package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.BlogInfoRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.model.BlogInfo;

@Service
@Controller
public class EditorController {
	@Autowired
	private BlogInfoRepository blogInfoRepository;

	@PostMapping("/showeditor")
	public String showEditor(@RequestParam("userId") Long userId, Model model) {
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
		return "editor";
	}


	@RequestMapping(value = "editor", params = "update", method = RequestMethod.POST)
	public ModelAndView commitUpdate(@RequestParam("userId") Long userId, //
			@RequestParam("title") String title, //
			@RequestParam("overview") String overview, //
			@RequestParam("editorcontent") String editorContent, //
			ModelAndView mv) {
		mv.addObject("userId", userId);
		BlogInfo blogInfo = blogInfoRepository.findByUserId(userId);
		if (blogInfo == null) {
			blogInfo = new BlogInfo();
		}
		blogInfo.setUserId(userId);
		blogInfo.setTitle(title);
		blogInfo.setOverview(overview);
		blogInfo.setEditorcontent(editorContent);
		blogInfoRepository.saveAndFlush(blogInfo);

		ModelAndView model = new ModelAndView("redirect:/showblog");
		model.addObject("userId", userId);
		return model;
	}

	@RequestMapping(value = "editor", params = "delete", method = RequestMethod.POST)
	public ModelAndView commitdelete(@RequestParam("userId") Long userId, //
			@RequestParam("title") String title, //
			@RequestParam("overview") String overview, //
			@RequestParam("editorcontent") String editorContent, //
			ModelAndView mv) {
		mv.addObject("userId", userId);
		BlogInfo blogInfo = blogInfoRepository.findByUserId(userId);
		blogInfoRepository.deleteById(blogInfo.getId());
		
		ModelAndView model = new ModelAndView("redirect:/showblog");
		model.addObject("userId", userId);
		return model;
	}
}
