package com.krishna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.krishna.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Modifying
	@Query("UPDATE Product SET code=:code WHERE id=:id") // Non-Select Operation
	public Integer updateCodeById(String code, Integer id);

}
