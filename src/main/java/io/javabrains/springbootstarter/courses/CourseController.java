package io.javabrains.springbootstarter.courses;

//import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.javabrains.springbootstarter.topic.Topic;

@RestController
public class CourseController 
{
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/topics/{topicId}/courses")
	public ResponseEntity<List<Optional<Course>>> getAllCourses(@PathVariable String topicId)
	{
		logger.info("Fetching all courses for topicId :");
        try {
			List<Optional<Course>> courses = courseService.getAllCourses(topicId);
			if(courses == null || courses.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(courses);
        } catch (Exception e) {
			logger.error("Error fetching the courses for the specific topicId :");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	

	@GetMapping("/topics/{topicId}/course/{id}")
	public ResponseEntity<Optional<Course>> getCourse(@PathVariable String id, @PathVariable String topicId)
	{
		logger.info("Fetching course with course_id: "+id+" for topicId :",topicId);
		if(id==null || id.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
        try {
			logger.info("Here's your specific course in the topic.");
			ResponseEntity<Optional<Course>> courseWithId=courseService.getCourseIdWithinTopic(topicId, id);
			return courseWithId
					.equals(ResponseEntity.ok().build())
					? courseWithId : ResponseEntity.notFound().build();
        } catch (Exception e) {
			logger.error("Error fetching the specific course with course_id:"+id+" within the topicId :",topicId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	
	
	
	
	
	@PostMapping("/topics/{topicId}/courses")
	public String addCourse(@RequestBody Course course, @PathVariable String topicId) 
	{
		course.setTopic(new Topic(topicId, "",""));
		courseService.addCourse(course);
		return "New Topic Added";
	}
	
	
	
	
	
	
	@PutMapping("/topics/{topicId}/course/{id}")
	public String updateCourse(@RequestBody Course course, @PathVariable String topicId , @PathVariable String id)
	{
		course.setTopic(new Topic(topicId,"",""));
		courseService.updateCourse(course,id);
		return "Updated the Topic.";
	}
	
	
	
	
	
	
	
	
	@DeleteMapping("/topics/{topicId}/course/{id}")
	public String deleteCourse(@PathVariable String id)
	{
		courseService.deleteCourse(id);
		return "Deleted the topic";
	}
	
	

}
