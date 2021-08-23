package com.example;

import java.util.function.Consumer;

public class VMargs {

	private static Consumer<String> output = System.out::println;
	
	public static void main(String[] args) {
		
		String value = System.getProperty("title");
		output.accept("Data is : "+value);
		
	}

}
