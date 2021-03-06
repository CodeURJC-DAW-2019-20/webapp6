package com.webapp.animeshop.security;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.user.User;
import com.webapp.animeshop.user.UserComponent;

@RestController
public class LoginController {

    private static final Logger log = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    private UserComponent userComponent;

    @RequestMapping("/api/logIn")
    public ResponseEntity<User> logIn() {
        if (!userComponent.isLoggedUser()) {
            log.info("Not user logged");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            User loggedUser = userComponent.getLoggedUser();
            log.info("Logged as " + loggedUser.getName());
            return new ResponseEntity<>(loggedUser, HttpStatus.OK);
        }
    }

    @RequestMapping("/api/logOut")
    public ResponseEntity<Boolean> logOut(HttpSession session) {
        log.info("asdasd {}", userComponent);
        if (!userComponent.isLoggedUser()) {
            log.info("No user logged");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            session.invalidate();
            log.info("Logged out");
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }

    @RequestMapping("/new/api/logIn")
	void loogIn(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/logIn");
    }
    
    @RequestMapping("/new/api/logOut")
	void loogOut(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/logOut");
	}
}