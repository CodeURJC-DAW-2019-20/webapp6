package com.webapp.animeshop.user;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private JavaMailSender sender;

	public void save(User user) {
		userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public void sendEmail(User userInfo) throws Exception{
    	String email = "Acabas de registrarte como: " + "\nUsuario --> " + userInfo.getName() + 
    					 "\n" + "\nTodos tus facturas serán enviadas al siguiente correo electrónico: " + userInfo.getDelivery().getEmail() + 
    					"\n" + "\nCualquier duda que tengas no dudes en contactarnos a través de nuestro correo electrónico.";
    	
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        ClassPathResource file = new ClassPathResource("/static/img/shop-logo.png");
        helper.addAttachment("/static/img/shop-logo.png", file);
        helper.setTo(userInfo.getDelivery().getEmail());
        helper.setText(email);
        helper.setSubject("¡Bienvenid@ a Anime Shop! ");
         
        sender.send(message);
    }

}