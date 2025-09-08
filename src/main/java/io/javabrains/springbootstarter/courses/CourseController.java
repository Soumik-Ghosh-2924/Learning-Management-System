package io.javabrains.springbootstarter.courses;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import io.javabrains.springbootstarter.topic.Topic;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

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
		logger.info("Fetching course with course_id: "+id+" for topicId :"+topicId);
		if(id==null || id.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
        try {
			logger.info("Here's your specific course in the topic.");
            return courseService.getCourseIdWithinTopic(topicId, id);
        } catch (Exception e) {
			logger.error("Error fetching the specific course with course_id:"+id+" within the topicId :",topicId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	@PostMapping("/topics/{topicId}/courses")
	public ResponseEntity<StringBuilder> addCourse(@RequestBody Course course, @PathVariable String topicId)
	{
		logger.info("Adding a new course to the specific topic ...");
		if(course==null){
			return ResponseEntity
					.badRequest()
					.body(new StringBuilder("Course Payload is required and it can't be empty."));
		}
        try {
            course.setTopic(new Topic(topicId, "",""));
			logger.info("Just about to add the new course into the topic.....");
            courseService.addCourse(course);
            return ResponseEntity.created(null)
					.body(
							new StringBuilder("New course has been to the" +
									" specific topic with topic_id: "+topicId)
					);
        } catch (Exception e) {
            logger.error("Incorrect payload passed over for the course attributes as required, for the topicId: {}", topicId);
			logger.warn("Here's what was passed for the course payload that was in an attempt towards it's addition : "+
					"Course Id : "+course.getId()+"\n"+
					"Course Name : "+course.getName()+"\n"+
					"Course Description : "+course.getDescription()+"\n"+
					"Course's Parent Topic  : "+course.getTopic());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
