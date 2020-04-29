
package com.fms.fileUpload.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fms.fileUpload.document.EventSummary;

@Configuration
@EnableBatchProcessing
public class FmsBatchConfigJob {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job fmsDataJob(Step firstStep) throws FileNotFoundException, IOException {
		return this.jobBuilderFactory.get("fmsDataJob").incrementer(new RunIdIncrementer()).flow(firstStep).end()
				.build();

	}

	@Bean
	public Step firstStep() throws FileNotFoundException, IOException {
		return this.stepBuilderFactory.get("firstStep").<EventSummary, EventSummary>chunk(20)
				.reader(eventDetailReader()).processor(eventDetailProcessor()).writer(eventDetailWriter()).build();
	}

	@Bean
	public ItemWriter<EventSummary> eventDetailWriter() {
		System.out.println("eventDetailWriter");
		return new EventWriter();
	}

	@Bean
	public ItemProcessor<EventSummary, EventSummary> eventDetailProcessor() {
		System.out.println("eventDetailProcessor");
		return new EventProcessor();
	}

	@Bean
	public ItemReader<EventSummary> eventDetailReader() {

		System.out.println("eventDetailReader");
		return new SpringBatchReader();

	}

}
