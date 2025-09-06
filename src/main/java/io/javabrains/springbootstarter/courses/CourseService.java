package io.javabrains.springbootstarter.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseService 
{
	private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

	@Autowired
	private CourseRepository courseRepository;
	
	
	//to get all the topics 
	public List<Optional<Course>> getAllCourses(String topicId)
	{
		logger.info("Fetching courses from the database : ");
		List<Optional<Course>> courses = new ArrayList<>();
        try {
            for(Optional<Course> course : courseRepository.findByTopicId(topicId))
            {
                courses.add(course);
            }
        } catch (Exception e) {
			throw new RuntimeException(e);
        }
        return courses;
	}
	
	
	
	
	//to get a specific topic
	public ResponseEntity<Optional<Course>> getCourseIdWithinTopic(String topicId, String id)
	{
        return ResponseEntity.ok(courseRepository.findByTopicIdAndId(topicId, id));
	}
	
	
	
	
	
	//to add a specific topic to the database
	public void addCourse(Course course)
	{
		courseRepository.save(course);
	}
	
	
	
	
	
	//to update the topic in the dB:
	public void updateCourse(Course course, String id)
	{
		List<Course> Courses = courseRepository.findAll();
		for(Course c : Courses)
		{
			if(c.getId().equals(id))
			{
				courseRepository.save(course);
			}
		}
		
		return;
	}
	
	
	
	
	
	//to delete the topic from the repository
	public void deleteCourse(String id)
	{
		courseRepository.deleteById(id);
	}	

}
