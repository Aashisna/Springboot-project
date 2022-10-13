package com.bway.springdemo.service;

import java.util.List;

import com.bway.springdemo.model.Employee;

public interface EmployeeService {
	
	void addEmp(Employee emp);
	void deleteEmp(Long id);
	void updateEmp(Employee emp);
	Employee getById(Long id);
	List<Employee> getAllEmp();

}
