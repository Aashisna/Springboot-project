package com.bway.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springdemo.model.Employee;
import com.bway.springdemo.model.User;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
