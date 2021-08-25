package com.example.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerService {

	int count = 1;

//	@Scheduled(fixedDelay = 3000)
	@Scheduled(fixedRate = 6000)
	@Scheduled(cron="")
	public void showReport() {

		System.out.println("START " + count + " :: " + new Date());
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("END   " + count++ + " :: " + new Date());
	}
}
