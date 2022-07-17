package com.bway.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bway.springdemo.model.User;
import com.bway.springdemo.repository.UserRepository;

@Controller
public class LoginController {
	//@RequestMapping(value ="/login", method = RequestMethod.GET)
	
	@Autowired
	private UserRepository userRepo;
	@GetMapping("/login")
	public String showLoginPage() {
		return "LoginForm";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute User u, Model model) {    //@ModelAttribute form ko data object ma bind garcha. Model carries the data to views is to home in this case
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		User usr = userRepo.findByUsernameAndPassword(u.getUsername(), u.getPassword());
		if(usr != null)
		{
			model.addAttribute("uname",u.getUsername());
			return "Home";
		}
		model.addAttribute("error","user not exist !!");
		return "LoginForm";
	}

}
