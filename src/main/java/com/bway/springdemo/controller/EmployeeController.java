package com.bway.springdemo.controller;

import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@GetMapping("/list")
	public String getAllEmployee( Model model)
	{
		model.addAttribute("emplist",empRepo.findAll());
		return "EmployeeList";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam Long id)
	{
		empRepo.deleteById(id);
		return "redirect:/list";
	}
	@GetMapping("/edit")
	public String delete(@RequestParam Long id, Model model)
	{
		model.addAttribute("emodel",empRepo.getById(id));
		return "EditForm";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute Employee emp)
	{
		empRepo.save(emp);
		return "redirect:/list";
	}

}
