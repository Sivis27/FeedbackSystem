package com.fms.fileUpload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
//@EnableScheduling
public class FileUploadApplication
{
	/*
	 * @Autowired JobLauncher jobLauncher;
	 * 
	 * @Autowired Job job;
	 */
      
    public static void main(String[] args)
    {
        SpringApplication.run(FileUploadApplication.class, args);
    }
      
 //   @Scheduled(cron = "0 */1 * * * ?")
    /*public void perform() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
    }*/
}