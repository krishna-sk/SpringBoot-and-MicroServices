package com.krishna.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.krishna.model.Product;

@Controller
@RequestMapping("/product")
public class ProductController {

	private Product prod;

	@PostConstruct
	public void init() {
		prod = new Product(999, "PEN", 500.0, "B", "NIT", "TEST SAMPLE");
	}

	@GetMapping("/showEdit")
	public String showEditProd(Model model) {
		model.addAttribute("product", prod);
		return "ProductEdit";
	}

	@GetMapping("/update")
	public String update(Model model) {
		model.addAttribute("product", prod);
		return "UpdatePage";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Product product, Model model) {
		prod = product;
		model.addAttribute("product", prod);
		return "UpdatePage";
	}
}
