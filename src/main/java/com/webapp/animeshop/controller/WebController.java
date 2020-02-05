package com.webapp.animeshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.webapp.animeshop.user.UserComponent;

public class WebController {
	
	@Autowired
	    private UserComponent userComponent;
	 
	@ModelAttribute
    public void addUserToModel(Model model){
        boolean logged=userComponent.getLoggedUser()!=null;
        model.addAttribute("logged",logged);
        if(logged){
            model.addAttribute("admin",userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
            model.addAttribute("userName",userComponent.getLoggedUser().getName());
            //model.addAttribute("logged");
        }
    }

	@GetMapping("/blog")
	public String blog(Model model) {		
		return "blog";
	}
	
	@GetMapping("/category")
	public String category(Model model) {		
		return "category";
	}
	
	@GetMapping("/singleBlog")
	public String singleBlog(Model model) {		
		return "singleBlog";
	}
	
	@GetMapping("/singleProduct")
	public String singleProduct(Model model) {		
		return "singleProduct";
	}
	
}