package com.webapp.animeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.model.User;
import com.webapp.animeshop.repositories.UserRepository;
import com.webapp.animeshop.user.UserComponent;
import com.webapp.animeshop.user.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserComponent userSession;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public String addNewUser(@RequestBody User user) {

		User newUser = new User(user.getName(), user.getPassword(), "ROLE_USER");
		if (userRepository.findByName(user.getName()) == null) {
			userService.save(newUser);
			
		}return "register"; //else
			//return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	public void addUserToModel(Model model) {
		boolean logged = userSession.getLoggedUser() != null;
		model.addAttribute("logged", logged);
	}
	
	@GetMapping("/currentuser")
	public User getCurrentUser() {
		return userSession.getLoggedUser();
	}
}