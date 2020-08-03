package com.example.thymeleaf.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.thymeleaf.thymeleafdemo.entity.Customer;
import com.example.thymeleaf.thymeleafdemo.service.CustomerService;

@Controller
@RequestMapping("/api")
@ComponentScan("com.example.thymeleaf")
public class CustomerRestController {

	@Autowired
	private CustomerService employeeService;
	private CustomerService customerService;

	// expose "/customers" and return list of customers
	@GetMapping("/customers")
	public String findAll(Model model, CustomerService customerService) {
		List list = customerService.findAll();
		model.addAttribute("list", list);
		return "customer-style";
	}

	// add mapping for showFormForAdd
	@GetMapping("/showFormFormAdd")
	public String showFormForAdd(Model model) {
		Customer theCustomer = new Customer();

		model.addAttribute("customer", theCustomer);
		return "customer-form";
	}

	// add mapping to save data
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// theEmployee.setId(0);

		
		customerService.save(theCustomer);

		return "redirect:/api/customers";
	}

	// add mapping for update a particular employee

	@GetMapping("/showFormForUpdate")
	@ResponseBody
	public Customer showFormForUpdate(@RequestParam("customerId") int id) {

		
		Customer theCustomer = customerService.findById(id);
		
		return theCustomer;
	}

	// add mapping for Delete a particular Customer

	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {

		customerService.deleteById(id);

		return "redirect:/api/customers";
	}
	// add mapping for GET /customers/{customerId}

}
