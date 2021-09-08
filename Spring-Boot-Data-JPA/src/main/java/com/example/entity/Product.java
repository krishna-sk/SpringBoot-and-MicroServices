package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="productTab")
public class Product {
	@Id
	@Column(name="prodid")
	private Integer id;
	
	@Column(name="prodname")
	private String name;
	
	@Column(name="prodCost")
	private Double cost;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expDate;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Temporal(TemporalType.TIME)
	private Date packingTime;
	
}