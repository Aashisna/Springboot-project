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
import com.bway.springdemo.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/employee")
	public String ShowEmployeeForm() {
		return "EmployeeForm";
	}
	
	@PostMapping("/employee")
	public String saveEmployee(@ModelAttribute Employee  emp) {
		
		service.addEmp(emp);
		return "EmployeeForm";
	}
	@GetMapping("/list")
	public String getAllEmployee( Model model)
	{
		model.addAttribute("emplist",service.getAllEmp());
		return "EmployeeList";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam Long id)
	{
		service.deleteEmp(id);
		return "redirect:/list";
	}
	@GetMapping("/edit")
	public String delete(@RequestParam Long id, Model model)
	{
		model.addAttribute("emodel",service.getById(id));
		return "EditForm";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute Employee emp)
	{
		service.updateEmp(emp);
		return "redirect:/list";
	}

}
