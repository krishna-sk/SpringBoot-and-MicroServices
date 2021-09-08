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
@Table(name="course")
public class Course {
	
	@Id
	@Column(name="id")
	private Integer courseId;
	
	@Column(name="code")
	private String courseCode;
	
	@Column(name="cost")
	private Double courseFee;
	
	@Column(name="ven")
	private String courseVen;
	
}