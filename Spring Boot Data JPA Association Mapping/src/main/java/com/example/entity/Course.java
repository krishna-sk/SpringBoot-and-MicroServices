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
@Table(name="coursetab")
public class Course {
	@Id
	@Column(name="cid")
	private Integer cid;
	
	@Column(name="cname")
	private String cname;
	
	@Column(name="camount")
	private Double camount;
	
}