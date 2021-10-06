package com.krishna.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "employee")
public class Employee {

	/*
	 * we can omit @Column since all our variables are well structured and same will
	 * be taken as column name by default
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String gender;

	private String email;

	private Double salary;

	private String department;

	private Double hra;

	private Double ta;
	
	@DateTimeFormat(iso =ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date doj;

}
