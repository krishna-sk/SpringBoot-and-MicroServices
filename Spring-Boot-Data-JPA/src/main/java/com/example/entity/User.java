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
@Table(name="user")
public class User {
	
	@Id
	@Column(name="id")
	private Integer userid;
	
	@Column(name="username", nullable=false, length=30)
	private String userName;
	
	@Column(name="password", nullable=false)
	private Double password;

}
