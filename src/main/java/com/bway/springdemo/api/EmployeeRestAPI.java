package com.bway.springdemo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springdemo.model.Employee;
import com.bway.springdemo.service.EmployeeService;

//return type in bydefault in json format vs controller return type in view format
@RestController  
public class EmployeeRestAPI {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/emp/api/list")
	public List<Employee> getAll() {
		return service.getAllEmp();
	}
	
	@PostMapping("/emp/api/add")
//	@RequestBody => convert the emp data below to json format and 
//	it is also used to connect the api of react, angular and many more.
	public String addEmp(@RequestBody Employee emp) {
		
		service.addEmp(emp);
		return "added success";
	}
	
	@PutMapping("/update")
	public String updateEmployee(@RequestBody  Employee emp) {
		service.updateEmp(emp);
		return "update success";
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		service.deleteEmp(id);
		return "delete success";
	}
	
	@GetMapping("/jsonobj")
	public String jsonToOBject() {
	RestTemplate rest = new RestTemplate();
	Employee emp = rest.getForObject("", Employee.class);
	return "FirstName = "+emp.getFname();
	}
	
	public Employee getOneEmp(@PathVariable Long id)
	{
		return service.getById(id);
	}
	
	public String jsonArrayToObjectArray() {
		RestTemplate rest = new RestTemplate();
		Employee[] emplist = rest.getForObject("http://localhost::1080/emp/api/list", Employee[].class);
		return "FirstName =" +emplist[1].getFname();
	}
}
