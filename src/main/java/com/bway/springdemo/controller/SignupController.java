package com.bway.springdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springdemo.model.User;
import com.bway.springdemo.repository.UserRepository;
import com.bway.springdemo.utils.VerifyRecaptcha;

@Controller
public class SignupController {
	// @RequestMapping(value ="/login", method = RequestMethod.GET)
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/signup")
	public String showSignupPage() {
		return "Signup";
	}

	@PostMapping("/signup")
	public String saveUser(@ModelAttribute User u, Model model,
			@RequestParam("g-recaptcha-response") String recaptchaCode) throws IOException {

		if (VerifyRecaptcha.verify(recaptchaCode)) {

			u.setRepassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
			u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
			userRepo.save(u);

			return "LoginForm";

		}

		model.addAttribute("msg", "plz.. select recaptcha");
		return "Signup";
	}

}

//		public String saveUser(@ModelAttribute User u, Model model) {    //@ModelAttribute form ko data object ma bind garcha. Model carries the data to views is to home in this case
//			if(u.getFname().equals("Aashisna") && u.getLname().equals("Thapaliya") && u.getPassword().equals("777") && u.getRepassword().equals("777"))
//			{
//				model.addAttribute("name",u.getFname());
//				return "Profile";
//			}
//			model.addAttribute("error","user not exist !!");
//			return "Signup";
//		}

// }
