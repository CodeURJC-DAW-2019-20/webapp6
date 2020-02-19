package com.webapp.animeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.model.Address;
import com.webapp.animeshop.model.User;
import com.webapp.animeshop.repositories.UserRepository;
import com.webapp.animeshop.user.UserComponent;
import com.webapp.animeshop.user.UserService;
import com.webapp.animeshop.user.UserComponent;


@Controller
public class UserController extends WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserComponent userSession;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;

	@PostMapping("/register")
	public String addNewUser(@RequestBody User user) {

		User newUser = new User(user.getName(), user.getPasswordHash(),new Address(), "ROLE_USER");
		if (userRepository.findByName(user.getName()) == null) {
			userService.save(newUser);
			
		}return "/register"; //else
			//return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	/*public void addUserToModel(Model model) {
		boolean logged = userSession.getLoggedUser() != null;
		model.addAttribute("logged", logged);
	}*/
	
	@RequestMapping("/user")
	public String userPage(Model model) {
		
		System.out.println("wwww");
		if(userComponent.isLoggedUser()) {
		model.addAttribute("user", this.userComponent.getLoggedUser());
		}
		return "userPage";
	}
	
	
	@GetMapping("/currentuser")
	public User getCurrentUser() {
		return userSession.getLoggedUser();
	}
}