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
@Table(name="projtab")
public class Project {
	@Id
	@Column(name="pid")
	private Integer projId;
	
	@Column(name="pname")
	private String projName;
	
	@Column(name="pclient")
	private String projClient;
	
	@Column(name="pversion")
	private Double projVersion;
	
}