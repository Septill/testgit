package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theemployeeService) {
		employeeService = theemployeeService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		//get employees in DB
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Employee thEmployee = new Employee();
		
		theModel.addAttribute("employee",thEmployee);
		
		return "employees/employee-form";
	}
	
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		//get the employees from the service/ DB
		Employee theEmployee = employeeService.findById(theId);
		
		// set employee in the model to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		//send over to our form
	
		return "employees/employee-form";
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return "redirect:/employees/list";
	}
	
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId")int theId) {
		//delete employee
		employeeService.deleteById(theId);
		
		//redirect to list employees
		return "redirect:/employees/list";
	}
	
	
}









