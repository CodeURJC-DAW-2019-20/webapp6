package com.webapp.animeshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.animeshop.model.Address;
import com.webapp.animeshop.model.User;
import com.webapp.animeshop.user.UserService;
import com.webapp.animeshop.controller.WebController;

@Controller
public class WebControllerLogin extends WebController {

	
	@Autowired
	private UserService userService;
	
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("hideLogin",true);
        return "login";
    }

    @GetMapping("/loginerror")
    public String loginError(){
        return "loginError";
    }
    
	@RequestMapping("/register")
	public String register(Model model) {
		return "register";
	}

	@PostMapping("/newUser")
	public String newUser(Model model, HttpServletRequest req, @RequestParam String name,
			@RequestParam String password) {
		userService.save(new User(name, password, new Address(), "ROLE_USER"));
		return "login";
}
	}

