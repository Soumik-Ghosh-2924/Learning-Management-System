package io.javabrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;


@SpringBootApplication
public class CourseApiDataApplication 
{
	private static final Logger logger = Logger.getLogger(CourseApiDataApplication.class.getName());
	public static void main(String[] args) 
	{
		logger.info("Starting the Learning-Management-System application .");
		SpringApplication.run(CourseApiDataApplication.class, args);

		logger.info("Application is up and running...!");

	}

}
