package com.krishna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krishna.entity.Employee;
import com.krishna.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// 1. show Register page
	@GetMapping("/register")
	public String showReg(Model model) {
		model.addAttribute("emp", new Employee());
		return "EmployeeRegister";
	}

	// 2. save operation
	/*
	 * here we need to specify the name as "emp" for @ModelAttribute, as we bind
	 * th:object as "emp" in the EmployeeRegister form || \/ <form method="post"
	 * th:action="@{/employee/save}" th:object="${emp}">
	 */
	@PostMapping("/save")
	public String saveData(@ModelAttribute(name = "emp") Employee employee, // reading form data
			Model model) {
		Integer id = employeeService.saveEmployee(employee); // store in db
		String message = "Employee '" + id + "' Created!"; // create message
		model.addAttribute("message", message); // send message to ui
		model.addAttribute("emp", new Employee());
		return "EmployeeRegister";
	}

	// 3. display data
	@GetMapping("/all")
	public String getAll(@PageableDefault(page = 0, size = 10) Pageable pageable,
			@RequestParam(required = false) String message, Model model) {
		Page<Employee> page = employeeService.getAllEmployees(pageable);
		model.addAttribute("list", page.getContent());
		model.addAttribute("page", page);
		model.addAttribute("message", message);
		return "EmployeeData";
	}

	// 4. remove operation
	@GetMapping("/remove")
	public String removeData(@RequestParam Integer id, RedirectAttributes attributes) {
		employeeService.deleteEmployee(id);
		attributes.addAttribute("message", "Employee '" + id + "' deleted");
		return "redirect:all";
	}

	// 5. show edit
	@GetMapping("/edit")
	public String showEdit(@RequestParam Integer id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "EmployeeEdit";
	}

	// 6. do update
	@PostMapping("/update")
	public String updateData(@ModelAttribute Employee employee, RedirectAttributes attributes) {
		employeeService.updateEmployee(employee);
		attributes.addAttribute("message", "Employee '" + employee.getId() + "' Updated!");
		return "redirect:all";
	}

	// 7. emialId exists or not
	/*
	 * this method will give reponse as string not as html page. since we annotated
	 * it with @ResponseBody
	 */
	@GetMapping("/validate")
	@ResponseBody
	public String validateEmail(@RequestParam String email) {
		String message = "not exists";
		if (employeeService.isEmployeeEmailExists(email)) {
			return email + " already exits ";
		}
		return message;
	}

	// 8.

}
