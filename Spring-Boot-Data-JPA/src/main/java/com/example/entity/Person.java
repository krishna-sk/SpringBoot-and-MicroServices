package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="persontab")
public class Person {
	
	@Id
	@Column(name="pid")
	private Integer perId;
	
	@Column(name="pname")
	private String perName;
	
	@Column(name="pbankbalance")
	private Double perBankBalance;
	
}
