package io.javabrains.springbootstarter.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService 
{
	@Autowired
	private CourseRepository courseRepository;
	
	
	//to get all the topics 
	public List<Course> getAllCourses(String topicId)
	{
		List<Course> courses = new ArrayList<>();
		for(Course course : courseRepository.findByTopicId(topicId))
		{
			courses.add(course);
		}
		return courses;
	}
	
	
	
	
	//to get a specific topic
	public Optional<Course> getCourse(String id)
	{
		return (Optional<Course>)courseRepository.findById(id); 
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
