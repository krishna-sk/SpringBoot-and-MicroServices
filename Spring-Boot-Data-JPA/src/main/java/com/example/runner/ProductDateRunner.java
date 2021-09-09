package com.example.runner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Product;
import com.example.repository.ProductRepository;

@Component
public class ProductDateRunner implements CommandLineRunner {
	
	@Autowired
	ProductRepository productRepo;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + ProductDateRunner +\n\n");
		
		Date date = new Date();
		Product product = new Product();
		product.setId(100);
		product.setName("sample");
		product.setCost(200.00);
		product.setCreatedDate(date);
		product.setExpDate(date);
		product.setPackingTime(date); 
		
		productRepo.save(product);
		
		productRepo.findAll().forEach(System.out::println);
	}

}
