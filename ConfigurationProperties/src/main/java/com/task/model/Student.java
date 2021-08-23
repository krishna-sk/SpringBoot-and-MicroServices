package com.task.model;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "my.key")
public class Student {

	private Integer sid;

	private String sname;

	private Integer sfee;

	private List<Double> marks;

	private Map<String, String> grades;

	private Address address;
}
