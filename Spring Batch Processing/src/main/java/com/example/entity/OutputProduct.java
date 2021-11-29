package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputProduct {

	private Integer id;

	private String name;

	private Double price;

	private Double discount;

	private Double gst;

}
