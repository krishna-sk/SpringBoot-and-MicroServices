package com.krishna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.entity.Product;
import com.krishna.exception.ProductNotFoundException;
import com.krishna.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/rest/product")
@Api(description = "Test Product Operation")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;

	// 1. create product
	@PostMapping("/save")
	@ApiOperation("Create Product")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		log.info("Entered into Create Product Method");
		Integer id = productService.saveProduct(product);
		String body = "Product '" + id + "' created!";
		log.info("About to leave the Create Product Method");
		return new ResponseEntity<String>(body, HttpStatus.CREATED); // 201 new object is created
	}

	// 2. fetch all products
	@GetMapping("/all")
	@ApiOperation("Fetch all Products as List")
	public ResponseEntity<List<Product>> fetchAllProducts() {
		log.info("Entered into fetch All Products Method");
		List<Product> list = productService.getAllProducts();
		log.info("About to leave the fetch All Products Method");
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	// 3. fetch one by id
	@GetMapping("/one/{id}")
	@ApiOperation("Fetch Product by Id")
	public ResponseEntity<?> fetchOneProduct(@PathVariable Integer id) {
		log.info("Entered into Fetch One Product Method");
		ResponseEntity<?> response = null;
		try {
			Product product = productService.getOneProduct(id);
			log.debug("Data found with id {}", id);
			response = new ResponseEntity<Product>(product, HttpStatus.OK);

		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			log.error("error is {} ", e.getMessage());
			throw e;
		}
		log.info("About to leave the Fetch One Product Method");
		return response;
	}

	// 4. remove one by id
	@DeleteMapping("/remove/{id}")
	@ApiIgnore
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		log.info("Entered into Delete Product Method");
		ResponseEntity<String> response = null;
		try {
			productService.deleteProduct(id);
			log.debug(" Product deleted with id {}", id);
			response = new ResponseEntity<String>(id + " - Removed", HttpStatus.OK);

		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			log.error("error is {} ", e.getMessage());
			throw e;
		}
		log.info("About to leave the Delete Product Method");
		return response;
	}

	// 5. update product
	@PutMapping("/modify")
	@ApiOperation("Update Product")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		log.info("Entered into Update Product Method");
		ResponseEntity<String> response = null;
		try {
			productService.updateProduct(product);
			log.debug(" Product Updated with id {}", product.getId());
			response = new ResponseEntity<String>("Updated !!!", HttpStatus.OK);

		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			log.error("error is {} ", e.getMessage());
			throw e;
		}
		log.info("About to leave the Update Product Method");
		return response;
	}

	// 6. (PATCH) partial update
	@PatchMapping("/update/{id}/{code}")
	@ApiOperation("Update Product Code by using Id")
	public ResponseEntity<String> updateProductCode(@PathVariable Integer id, @PathVariable String code) {
		log.info("Entered into Update Product Code Method");
		ResponseEntity<String> response = null;
		try {
			productService.updateCodeById(code, id);
			log.debug("Product Code Updated with id {}", id);
			response = new ResponseEntity<String>("Code is Updated !!!", HttpStatus.OK);

		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			log.error("error is {} ", e.getMessage());
			throw e;
		}

		log.info("About to leave the Update Product Code Method");
		return response;
	}

}