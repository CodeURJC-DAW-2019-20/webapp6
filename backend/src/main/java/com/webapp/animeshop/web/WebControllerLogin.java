package com.webapp.animeshop.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.animeshop.order.OrderService;
import com.webapp.animeshop.user.Address;
import com.webapp.animeshop.user.User;
import com.webapp.animeshop.user.UserService;
import com.webapp.animeshop.web.WebController;

@Controller
public class WebControllerLogin extends WebController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("hideLogin",true);
        model.addAttribute("cartSize",orderService.getCartSize());
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

