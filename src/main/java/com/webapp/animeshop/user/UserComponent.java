package com.webapp.animeshop.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.webapp.animeshop.model.User;

@Component
@SessionScope
public class UserComponent {
	
	private User user;
	
	public User getLoggedUser() {
		return user;
	}
	
	public void setLoggedUser(User user) {
		this.user = user;
	}
	
	public boolean isLoggedUser() {
		return this.user != null;
	}
	
	public boolean isAdmin() {
    	return user.getRoles().contains("ROLE_ADMIN");
    }

}

