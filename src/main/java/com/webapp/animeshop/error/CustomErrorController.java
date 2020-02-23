package com.webapp.animeshop.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CustomErrorController implements ErrorController {

   	@RequestMapping("/error")
   	public String handleError(Model model, HttpServletRequest request) {
   		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
   		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
   		String statusMessage = (String) request.getAttribute("javax.servlet.error.message");
   		model.addAttribute("code", statusCode);
   		model.addAttribute("message", statusMessage);
   		String errorMessage;
   		if (exception == null)
   			errorMessage = "";
   		else
   			errorMessage = exception.getMessage();
   		model.addAttribute("errorM", errorMessage);
   		return "error";
   	}

   	@Override
   	public String getErrorPath() {
   		return "/error";
   	}

   @GetMapping("/throwErrorForTest")
   public String throwError() {
      throw new RuntimeException("my exception");
   }
}