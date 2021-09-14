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
@Table(name="addrtab")
public class Address {
	
	@Id
	@Column(name="aid")
	private Integer addrId;

	@Column(name="hno")
	private String hno;
	
	@Column(name="loc")
	private String loc;
	
	@Column(name="pincode")
	private Long pinCode;
}