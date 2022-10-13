package com.bway.springdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springdemo.model.Employee;
import com.bway.springdemo.repository.EmployeeRepository;
import com.bway.springdemo.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository empRepo;
	

	@Override
	public void addEmp(Employee emp) {
		empRepo.save(emp);
		
	}

	@Override
	public void deleteEmp(Long id) {
		empRepo.deleteById(id);
	}

	@Override
	public void updateEmp(Employee emp) {
	empRepo.save(emp);
		
	}

	@Override
	public Employee getById(Long id) {
		
		return empRepo.getById(id);
	}

	public List<Employee> getAllEmp() {
		
		return empRepo.findAll();
	}

}
