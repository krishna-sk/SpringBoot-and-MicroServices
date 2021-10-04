package com.krishna.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	
	private Integer id;
	private String name;
	private Double cost;
	private String type;
	private String brand;
	private String note;
}