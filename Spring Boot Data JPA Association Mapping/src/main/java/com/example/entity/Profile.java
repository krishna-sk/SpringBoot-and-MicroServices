package com.example.entity;
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
@Table(name="proftab")
public class Profile {
	@Id
	private Integer pid;
	private String role;
	private Double exp;
	private String desg;
}