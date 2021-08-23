package com.example.runner;

import java.util.function.Consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class DataRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		StopWatch watch = new StopWatch("APP DATA TIMER");
		Consumer<String> output = System.out::println;

		watch.start("Block 1");
		output.accept("STARTED Block 1");
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Math.pow(i + 1, Math.pow((i + 1) * (i + 1), i + 1));
		}
		Thread.sleep(2000);
		watch.stop();
		output.accept("STOPPED Block 1");

		
		watch.start("Block 2");
		output.accept("STARTED Block 2");
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Math.pow(i + 1, Math.pow((i + 1) * (i + 1), i + 1));
		}
		Thread.sleep(3000);
		watch.stop();
		output.accept("STOPPED Block 2");

		
		watch.start("Block 3");
		output.accept("STARTED Block 3");
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Math.pow(i + 1, Math.pow((i + 1) * (i + 1), i + 1));
		}
		Thread.sleep(1000);
		watch.stop();
		output.accept("STOPPED Block 3");

		
		output.accept(String.valueOf(watch.prettyPrint()));
		/*
		 * output.accept(String.valueOf(watch.getTotalTimeSeconds()));
		 * output.accept(String.valueOf(watch.getTotalTimeMillis()));
		 * output.accept(String.valueOf(watch.getTotalTimeNanos()));
		 */
	}

}
