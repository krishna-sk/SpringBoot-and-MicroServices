package com.example.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usertab")
public class User {
	
	@Id
	@Column(name="uid")
	private Integer uid;
	
	@Column(name="uname")
	private String uname;
	
	@Column(name="uaddr")
	private String uaddr;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="usrcrstab",
	 joinColumns = @JoinColumn(name="uidFk"),
	 inverseJoinColumns = @JoinColumn(name="cidFk")
	)
	private List<Course> cobs;//HAS-A


}