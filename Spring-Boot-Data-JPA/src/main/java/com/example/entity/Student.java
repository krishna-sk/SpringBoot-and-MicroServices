package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@Column(name="id")
	private Integer stdId;
	
	@Column(name="stdname",nullable=false,length=30)
	private String stdName;
	
	@Column(name="stdfee",nullable=false)
	private Double stdFee;
	
}
