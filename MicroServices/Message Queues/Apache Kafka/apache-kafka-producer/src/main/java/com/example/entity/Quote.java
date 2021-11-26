package com.example.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Quote {

	private String code;

	private Double cost;

	private Date date;
}