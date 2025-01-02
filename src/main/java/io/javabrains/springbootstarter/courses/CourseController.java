package io.javabrains.springbootstarter.courses;

//import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.javabrains.springbootstarter.topic.Topic;

@RestController
public class CourseController 
{
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/topics/{topicId}/courses")
	public List<Course> getAllCourses(@PathVariable String topicId) 
	{
		System.out.println("Here's all your courses in the current topic.");
		return courseService.getAllCourses(topicId);
		
	}
	
	
	 
	@GetMapping("/topics/{topicId}/course/{id}")
	public Optional<Course> getCourse(@PathVariable String id)
	{
		System.out.println("Here's your specific course in the topic.");
		return (Optional<Course>)courseService.getCourse(id);
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
