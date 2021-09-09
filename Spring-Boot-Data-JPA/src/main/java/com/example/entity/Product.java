package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@Column(name="expDate")
	private Date expDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="createdDate")
	private Date createdDate;
	
	@Temporal(TemporalType.TIME)
	@Column(name="packingTime")
	private Date packingTime;
	
}