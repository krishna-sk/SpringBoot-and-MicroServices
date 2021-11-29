package com.example.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.batch_processing.JobListener;
import com.example.batch_processing.ProductProcessor;
import com.example.entity.InputProduct;
import com.example.entity.OutputProduct;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	// Autowire Step Builder Factory
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// Autowire Job Builder Factory
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	// Autowire Datasource Object
	@Autowired
	private DataSource datasource;

	// Reader class Object
	@Bean
	public FlatFileItemReader<InputProduct> reader() {
		FlatFileItemReader<InputProduct> reader = new FlatFileItemReader<>();
//		reader.setResource(new ClassPathResource("products.csv"));
		reader.setResource(new ClassPathResource("MOCK_DATA.csv"));
//		reader.setResource(new FileSystemResource("D://sample/product.csv"));
//		reader.setResource(new UrlResource("https://abcd.com/files/products.csv"));

//	 Instance Block {{}}
		reader.setLineMapper(new DefaultLineMapper<>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {

					{
						setDelimiter(DELIMITER_COMMA);
						setNames("id", "name", "price");
						/*
						 * include only first three columns from the file, names and fields count should
						 * match
						 */
						setIncludedFields(new int[] { 0, 1, 2 });
					}
				});

				setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
					{
						setTargetType(InputProduct.class);
					}
				});

			}

		});
//		 to skip the header in the file we skip the first line
		reader.setLinesToSkip(1);
		return reader;
	}

	// Processor class Object
	@Bean
	public ItemProcessor<InputProduct, OutputProduct> processor() {

		return new ProductProcessor();

// we can use lambda function instead of defining ProductProcessor

//		return inputProduct -> {
//			OutputProduct outputProduct = new OutputProduct();
//			Double price = inputProduct.getPrice();
//
//			outputProduct.setId(inputProduct.getId());
//			outputProduct.setName(inputProduct.getName());
//			outputProduct.setPrice(price);
//			outputProduct.setDiscount((price * 12 / 100));
//			outputProduct.setGst((price * 22 / 100));
//			return outputProduct;
//		};

	}

	// Writer class Object
	@Bean
	public JdbcBatchItemWriter<OutputProduct> writer() {
		JdbcBatchItemWriter<OutputProduct> writer = new JdbcBatchItemWriter<>();
		writer.setDataSource(datasource);
		writer.setSql("INSERT INTO PRODUCTS (id, name, price, discount, gst) VALUES (:id,:name,:price,:discount,:gst)");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return writer;
	}

	// Listener class Object
	@Bean
	public JobExecutionListener listener() {
		return new JobListener();

		// Anonymous Class
//		return new JobExecutionListener() {
//
//			@Override
//			public void beforeJob(JobExecution jobExecution) {
//				System.out.println("Started Date and Time : " + new Date());
//				System.out.println("Status at Starting : " + jobExecution.getStatus());
//			}
//
//			@Override
//			public void afterJob(JobExecution jobExecution) {
//				System.out.println("End Date and Time : " + new Date());
//				System.out.println("Status at Ending : " + jobExecution.getStatus());
//			}
//		};

	}

	// Step Object
	@Bean
	public Step stepOne() {
		return stepBuilderFactory.get("stepOne") // step name
				.<InputProduct, OutputProduct>chunk(10) // chunk size
				.reader(reader()) // reader object
				.processor(processor()) // processor object
				.writer(writer()) // writer object
				.build();
	}

	// Job Object
	@Bean
	public Job job() {
		return jobBuilderFactory.get("jobOne").incrementer(new RunIdIncrementer()).listener(listener()).start(stepOne())
//				.next(stepTwo())
//				.next(stepThree())
				.build();
	}
}
