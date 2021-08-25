package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="stdtab")
public class Student {
	
	@Id
	@Column(name="sid")
	private Integer stdId;
	
	@Column(name="stdName")
	private String stdName;
	
	@Column(name="stdFee")
	private Double stdFee;
	
}
