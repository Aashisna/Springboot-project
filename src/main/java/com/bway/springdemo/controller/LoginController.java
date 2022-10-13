package com.bway.springdemo.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springdemo.model.User;
import com.bway.springdemo.repository.UserRepository;
import com.bway.springdemo.utils.VerifyRecaptcha;

@Controller
public class LoginController {
	//@RequestMapping(value ="/login", method = RequestMethod.GET)
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserRepository userRepo;
	@GetMapping("/login")
	public String showLoginPage() {
		logger.info("====Login Success=====");
		return "LoginForm";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute User u, Model model, HttpSession session,@RequestParam("g-recaptcha-response") String recaptchaCode)    //@ModelAttribute form ko data object ma bind garcha. Model carries the data to views is to home in this case
		 throws IOException {

		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		User usr = userRepo.findByUsernameAndPassword(u.getUsername(), u.getPassword());
		if(usr != null && VerifyRecaptcha.verify(recaptchaCode))
		{
			logger.info("====Login Success=====");
			session.setAttribute("validUser",usr);
			//model.addAttribute("uname",u.getUsername());
			return "Home";
		}else {
		model.addAttribute("msg", "plz.. select recaptcha");
		return "LoginForm";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "LoginForm";
	}

}
