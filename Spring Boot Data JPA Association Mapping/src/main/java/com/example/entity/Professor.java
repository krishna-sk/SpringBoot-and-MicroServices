package com.example.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="professor")
public class Professor {
	@Id
	private Integer id;
	private String name;
	private Double sal;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="pidFk")
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="pidFk",unique = true)*/
	private Profile pro;
}