package com.krishna.service;

import java.util.List;

import com.krishna.entity.Product;

public interface ProductService {

	Integer saveProduct(Product p);

	List<Product> getAllProducts();

	Product getOneProduct(Integer id);

	void deleteProduct(Integer id);

	void updateProduct(Product p);

}
