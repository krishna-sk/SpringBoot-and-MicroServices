package com.example.batch_processing;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Started Date and Time : " + new Date());
		System.out.println("Status at Starting : "+ jobExecution.getStatus());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("End Date and Time : " + new Date());
		System.out.println("Status at Ending : "+ jobExecution.getStatus());
	}

}
