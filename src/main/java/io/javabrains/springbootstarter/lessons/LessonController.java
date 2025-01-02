package io.javabrains.springbootstarter.lessons;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.courses.Course;

@RestController
public class LessonController 
{
	@Autowired
	private LessonService lessonService;
	
	
	
	
	
	
	@GetMapping("/topics/{topicId}/course/{courseId}/lessons")
	public List<Lesson> getAllLessons(@PathVariable String courseId )
	{
		List<Lesson> lessonList= lessonService.getAllLessons(courseId);		
		System.out.println("Here's all the lessons in the course.");
		
		return  lessonList;
	}
	
	
	
	
	
	
	
	@GetMapping("/topics/{topicId}/course/{courseId}/lessons/{id}")
	public Optional<Lesson> getLesson(@PathVariable String id)
	{
		System.out.println("Here's your sepcific lesson.");
		return (Optional<Lesson>) lessonService.getLesson(id);
	}
	
	
	
	
	
	
	
	@PostMapping("/topics/{topicId}/course/{courseId}/lessons")
	public String  addLesson(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId)
	{
		lesson.setCourse(new Course(courseId,"","", topicId));
		lessonService.addLesson(lesson);
		return "New Topic Added";
		
	}
	
	
	
	
	
	@PutMapping("/topics/{topicId}/course/{courseId}/lessons/{id}")
	public String updateLesson(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId)
	{
		lesson.setCourse(new Course(courseId,"","",topicId));
		lessonService.updateLesson(lesson);
		return "Updated the lesson.";
	}
	
	
	
	
	
	
	
	@DeleteMapping("topics/{topicId}/course/{courseId}/lessons/{id}")
	public String deleteLeson(@PathVariable String id)
	{
		lessonService.deleteLesson(id);
		return "Lesson has been deleted.";
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
