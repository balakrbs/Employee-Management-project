package com.Employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Employee.model.Employee;
import com.Employee.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	//-----------------------Reload Landing page methods------------------------------
	@GetMapping("/")
	public String home(Model model) {

		//--------------Counting methods---------------
		model.addAttribute("countT", empService.getTotalCount());
		model.addAttribute("countM", empService.getMaleCount());
		model.addAttribute("countF", empService.getFemaleCount());
		model.addAttribute("countO", empService.getOtherCount());
        
		return "home";
	}
	
	//-----------------------navigation forward methods------------------------------
	
	@GetMapping("/forwardSearch")		
	  public String forwardSearch(Model model) {  
	  	 	model.addAttribute("employee", new Employee());
	         return "searchemp";
	     }
	
	//-----------------------Add employee methods------------------------------

	@GetMapping("/forwardAdd")		
	  public String forwardAddemployee(Model model) {  
	  	 	model.addAttribute("employee", new Employee());
	         return "addemployee";
	     }
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute Employee employee,Model model) {
		empService.saveEmployee(employee);
		return "redirect:/forwardAdd";
	}
	
	//-----------------------Employee List methods------------------------------
	
	@GetMapping("/displayAll")
	public String displayAllEmployee(Model model) {
		model.addAttribute("employees",empService.getAllEmployee());
		return "viewAll";
	}
	
	//-----------------------Employee Search methods------------------------------
	
	@GetMapping("/display")
	public String displayEmployeeById(@ModelAttribute(value="employee") Employee employee,Model model) {
		//System.out.println(employee.getId());  
		Optional<Employee> emp=empService.getEmployeeId(employee.getId());
		if(emp.isPresent()) {
			model.addAttribute("employee",emp.get());
			return "searchemp";
		}else {
			return "error";
		}
	}
	
	
	//-------------------------Editing methods-------------------------
	
	@GetMapping("/displayEdit")
	public String editEmployee(Model model) {
		model.addAttribute("employees",empService.getAllEmployee());
		return "editemployee";
	}
	
	@GetMapping("/showformforupdate/{id}")
	public String showformforupdate(@PathVariable(value="id") String id,Model model) {
		Optional<Employee> employee=empService.getEmployeeId(id);
		model.addAttribute("employees",employee);
		return "employee_update";
	}
	
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee employee,Model model) {
		empService.saveEmployee(employee);
		return "redirect:/displayEdit";
	}
	
	
	//-------------------------delete methods-------------------------
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value="id") String id) {
		this.empService.deleteEmployeeById(id);
		return "redirect:/displayEdit";
		
	}
	
	


}
