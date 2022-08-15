package com.ned.finalProject;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FinalProjectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}
}
