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
@Table(name="book")
public class Book {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="bookname", nullable=false, length=30)
	private String bookName;
	
	@Column(name="bookcost", nullable=false)
	private Double bookCost;

}
