package com.krishna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
