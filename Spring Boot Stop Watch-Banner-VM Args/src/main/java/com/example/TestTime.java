package com.example;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class TestTime {

	private static Consumer<String> output = System.out::println;
	
	public static void main(String[] args) {
		
		output.accept(String.valueOf(TimeUnit.MINUTES.toMillis(3)));
		output.accept(String.valueOf(TimeUnit.DAYS.toMinutes(1)));
		output.accept(String.valueOf(TimeUnit.HOURS.toNanos(1)));
		
	}

}
