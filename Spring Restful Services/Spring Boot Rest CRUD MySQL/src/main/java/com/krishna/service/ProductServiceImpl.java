package com.krishna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishna.entity.Product;
import com.krishna.exception.ProductNotFoundException;
import com.krishna.repository.ProductRepository;
import com.krishna.util.ProductUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Integer saveProduct(Product p) {
		ProductUtil.calculate(p);
		return productRepository.save(p).getId();
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getOneProduct(Integer id) {
		/*
		 * Optional<Product> opt = productRepository.findById(id); if(opt.isPresent()) {
		 * return opt.get(); } else { throw new ProductNotFoundException(id+
		 * "- not exist"); }
		 */
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id + " - not exist"));
	}

	public void deleteProduct(Integer id) {
		// productRepository.deleteById(id);
		productRepository.delete(getOneProduct(id));
	}

	public void updateProduct(Product p) {
		if (p.getId() != null && productRepository.existsById(p.getId())) {
			ProductUtil.calculate(p);
			productRepository.save(p);
		} else {
			throw new ProductNotFoundException(p.getId() + " - not exist");
		}
	}

}
