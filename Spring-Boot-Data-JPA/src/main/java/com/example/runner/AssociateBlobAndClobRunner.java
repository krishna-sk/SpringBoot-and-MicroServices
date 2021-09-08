package com.example.runner;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Associate;
import com.example.repository.AssociateRepository;

@Component
public class AssociateBlobAndClobRunner implements CommandLineRunner {

	@Autowired
	AssociateRepository associateRepository;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + AssociateBlobAndClobRunner +\n\n");
		
		FileInputStream fis = new FileInputStream("C:\\Users\\krish\\Desktop\\kola.jpeg");
		byte[] img = new byte[fis.available()];
		fis.read(img);

		String desStr = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
				+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
				+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
				+ "It has survived not only five centuries, but also the leap into electronic typesetting, "
				+ "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset"
				+ " sheets containing Lorem Ipsum passages, and more recently with desktop publishing software "
				+ "like Aldus PageMaker including versions of Lorem Ipsum.";
		
		char[] des = desStr.toCharArray();

		Associate associate = new Associate();
		associate.setId(100);
		associate.setName("sample");
		associate.setSalary(20000.00);
		associate.setImage(img);
		associate.setDescription(des);
		
		associateRepository.save(associate);
		fis.close();

	}

}
