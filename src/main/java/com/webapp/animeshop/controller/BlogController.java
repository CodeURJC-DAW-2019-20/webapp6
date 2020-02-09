package com.webapp.animeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webapp.animeshop.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/")
	public String showBlogs(Model model) {
		model.addAttribute("blog", this.blogService.getBlogs());
		return "/index";
	}
	
}

