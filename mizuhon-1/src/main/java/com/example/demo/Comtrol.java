package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Comtrol {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}