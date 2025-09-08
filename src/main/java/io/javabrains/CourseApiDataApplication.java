package io.javabrains;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class CourseApiDataApplication 
{
	private static final Logger logger = LoggerFactory.getLogger(CourseApiDataApplication.class.getName());
	public static void main(String[] args) {
        try {
            logger.info("Starting the Learning-Management-System application .");
            SpringApplication.run(CourseApiDataApplication.class, args);
            logger.info("Application is up and running...!");
        } catch (Exception e) {
			logger.error("Failure in starting the application : ");
            throw new RuntimeException(e);
        }
    }
}
