package com.springboot;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomApplicationRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println();
		System.out.println("Application Runner :: args.getSourceArgs()    :: " + Arrays.asList(args.getSourceArgs()));
		Set<String> optionNames = args.getOptionNames();
		System.out.println("Application Runner :: args.getOptionNames()   :: " + Arrays.asList(optionNames));
		Consumer<? super List<String>> consumer = (s) -> System.out
				.println("Application Runner :: args.getOptionValues()  :: " + s);
		optionNames.stream().map((s) -> args.getOptionValues(s)).forEach(consumer);
		System.out.println("Application Runner :: args.getNonOptionArgs() :: " + Arrays.asList(args.getNonOptionArgs()));
		System.out.println();

	}

}
