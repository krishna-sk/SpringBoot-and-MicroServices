package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="associate")
@NoArgsConstructor
@AllArgsConstructor
public class Associate {
	
	@Id
	@Column(name="ascId")
	private Integer id;
	
	@Column(name="ascName")
	private String name;
	
	@Column(name="ascSal")
	private Double salary;
	
	@Column(name="ascImg")
	@Lob
	private byte[] image;

	@Column(name="ascDes")
	@Lob
	private char[] description;
}
