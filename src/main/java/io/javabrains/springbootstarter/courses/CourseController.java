package io.javabrains.springbootstarter.courses;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.javabrains.springbootstarter.topic.Topic;

@RestController
public class CourseController 
{
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	@GetMapping("/topics/{topicId}/courses")
	public ResponseEntity<List<Optional<Course>>> getAllCourses(@PathVariable (required = true) String topicId)
	{
		logger.info("Fetching all courses for topicId : {}", topicId);
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
	public ResponseEntity<Optional<Course>> getCourse
			(@PathVariable(required = true) String id, @PathVariable(required = true) String topicId)
	{
		logger.info("Fetching course with course_id: "+id+" for topicId :"+topicId);
		if(id==null || id.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
        try {
			logger.info("Here's your specific course in the topic.");
            return ResponseEntity.ok(courseService.getCourseIdWithinTopic(topicId, id));
        } catch (Exception e) {
			logger.error("Error fetching the specific course with course_id:"+id+" within the topicId :"+topicId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	@PostMapping("/topics/{topicId}/courses")
	public ResponseEntity<StringBuilder> addCourse(
			@Validated @RequestBody Course course,
			@PathVariable(required = true) String topicId) {
		if(course.getId()==null || course.getName()==null || course.getDescription()==null
			||course.getId().trim().isEmpty()||course.getName().trim().isEmpty()||course.getDescription().trim().isEmpty()) {
			logger.error("Neither of a course object can be an empty string !");
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.build();
		}
		logger.info("Just about to add the new course into the topic.....");
        try {
            course.setTopic(new Topic(topicId, "",""));
            courseService.addCourse(course);
			logger.info("Course with courseId : {} has been added to the topicId : {}", course.getId(), topicId);
            return ResponseEntity.created(null)
					.body(
							new StringBuilder("New course has been to the" +
									" specific topic with topic_id: "+topicId)
					);
        } catch (Exception e) {
            logger.error("Oops!! Incorrect payload passed over for the course attributes as required, for the topicId: {}", topicId);
			logger.warn("Here's what was passed for the course payload that was in an attempt towards it's addition : "+
					"Course Id : "+course.getId()+"\n"+
					"Course Name : "+course.getName()+"\n"+
					"Course Description : "+course.getDescription()+"\n"+
					"Course's Parent Topic  : "+course.getTopic());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

	@PutMapping("/topics/{topicId}/course/{id}")
	public String updateCourse(
			@RequestBody Course course,
			@PathVariable(required=true) String topicId ,
			@PathVariable(required = true) String id)
	{
        try {
            course.setTopic(new Topic(topicId,"",""));
            Optional<Course> existingCourse = courseService.getCourseIdWithinTopic(topicId, id);
            if(existingCourse.isEmpty())
            {
                logger.info("No course exist within topicId: {} with courseId : {}",topicId, id);
                return ("No course to update with courseId :"+id + ". If this is a new course" +
                        " being added create the same separately using the created API [/topics/{topicId}/courses].");
            }else{
                logger.info("Updating the existing topic within topicId : {} and courseId : {}", topicId, id);
                courseService.updateCourse(course,id);
                return "Updated the Topic.";
            }
        } catch (Exception e) {
			logger.error("Encountered an error while updating a course: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

	@DeleteMapping("/topics/{topicId}/course/{id}")
	public String deleteCourse(
			@PathVariable String topicId,
			@PathVariable String id) {
		logger.info("Checking whether the course to delete is either present or not :");
		boolean exist;
		Optional<Course> existingCourse = courseService.getCourseIdWithinTopic(topicId, id);
		if(existingCourse.isEmpty()) {
			exist=false;
			logger.info("No course to delete with courseId : {} and topicId : {} and present in db : {}", id, topicId, exist);
			return ("No valid-course is present to delete for courseId : " +id+ "within topicId : "+topicId);
		}else {
			exist=true;
			try {
				logger.info("Found a valid course to delete with courseId : {} and exist-flag: {}", id, exist);
				courseService.deleteCourse(exist, topicId, id);
				return "Deleted the topic";
			} catch (Exception e) {
				logger.error("Encountered an error while deleting a course : {}", e.getMessage());
				throw new RuntimeException(e);
			}
		}
    }
}
