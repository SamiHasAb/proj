package org.study.pma.Controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.pma.Entities.Employee;
import org.study.pma.Services.EmployeeService;


@Controller
@RequestMapping("/employees")

public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	// used to display table when request "/projects"
		//its by default a get method
		@RequestMapping()
		public String showEmployees(Model model ,  Pageable pageable) {

			 Page<Employee> employees= empService.findAll(pageable) ;
			model.addAttribute("employeesList",employees);
			return"/employee/list-employees";
		}
		
	//@RequestMapping(value = "/new", method = RequestMethod.GET)

		@GetMapping("/new")
		public String displayEmployeesForm(Model model) {
			Employee aEmployee = new Employee();
			
			model.addAttribute("employee",aEmployee);
			return "/employee/new-employee";
		}
		
		@PostMapping("/save")
		public String createEmployee( Model model , @Valid Employee employee  ,  Errors errors) {
			
			if(errors.hasErrors())
				return "employee/new-employee";
			
			empService.save(employee);

			
			//use redirect to prevent duplicate submissions
			return "redirect:/employees/new";
		}
		@GetMapping("/update")
		public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
			
			Employee theEmp = empService.findByEmployeeId(theId);
			
			empService.delete(theEmp);
			
			model.addAttribute("employee", theEmp);
			
			
			return "/employee/new-employee";
		}
		
		@GetMapping("delete")
		public String deleteEmployee(@RequestParam("id") long theId, Model model) {
			Employee theEmp = empService.findByEmployeeId(theId);
				
			System.out.println(theEmp);
				
			empService.delete(theEmp);
			return "redirect:/employees";
		}	
	
		@GetMapping("/test")
		public String test( Model model ,  Pageable pageable) {

			 Page<Employee> employees= empService.findAll(pageable) ;
			 	
			model.addAttribute("employeesList",employees);
			return"main/h";
		}
}
