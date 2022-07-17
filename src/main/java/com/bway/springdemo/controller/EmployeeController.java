package com.bway.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bway.springdemo.model.Employee;
import com.bway.springdemo.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository empRepo;
	
	@GetMapping("/employee")
	public String ShowEmployeeForm() {
		return "EmployeeForm";
	}
	
	@PostMapping("/employee")
	public String saveEmployee(@ModelAttribute Employee  emp) {
		
		empRepo.save(emp);
		return "EmployeeForm";
	}

}
