package com.krishna.model;
import java.util.List;

import lombok.Data;

@Data
public class Employee {

	private String empName;
	private String empPwd;
	private String empGen;
	private String empAddr;
	private String empProj;
	
	private List<String> empLangs;
	private List<String> empClients;
	
}