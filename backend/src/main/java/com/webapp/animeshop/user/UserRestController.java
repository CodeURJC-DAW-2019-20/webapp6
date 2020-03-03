package com.webapp.animeshop.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	public UserService userService;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserComponent userComponent;
	
	@PostMapping("/register")
	public ResponseEntity<User> addNewUser(@RequestBody User user) {
		User newUser = new User(user.getName(), user.getPasswordHash(), user.getDelivery(), "ROLE_STUDENT");
		if (userRepository.findByName(user.getName()) == null) {
			userService.save(newUser);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/currentuser")
	public User getCurrentUser() {
		return userComponent.getLoggedUser();
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.findAll();
	}
	
	@PutMapping("/newInfo")
	public User newInfo(@RequestParam String shippingName,Address address, @RequestParam String passwordHash) {
		
		User user = this.userComponent.getLoggedUser();
		user.setDelivery(address);
		user.getDelivery().setName(shippingName);
		user.setPassword(passwordHash);
		this.userRepository.save(user);
		
		return userComponent.getLoggedUser();
	}
	
}
