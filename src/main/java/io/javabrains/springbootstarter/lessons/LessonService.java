package io.javabrains.springbootstarter.lessons;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService 
{
	
	@Autowired
	private LessonRepository lessonRepository;
	
	
	
	
	public List<Lesson> getAllLessons(String courseId)
	{
		List<Lesson> lessList=lessonRepository.findByCourseId(courseId);
		return lessList;
	}
	
	
	
	
	
	
	public Optional<Lesson> getLesson(String lessonId)
	{
		System.out.println("Here's your specific lesson.");
		return (Optional<Lesson>) lessonRepository.findById(lessonId);
	}

	
	
	
	
	
	
	public void addLesson(Lesson lesson)
	{
		lessonRepository.save(lesson);
	}
	
	
	
	
	
	
	
	public void updateLesson(Lesson lesson)
	{
		List<Lesson> lessonL= lessonRepository.findAll();
		for(Lesson l : lessonL )
		{
			if(l.getId().equals(lesson.getId()))
			{
				lessonRepository.save(lesson);
			}
		}
		
		return;
	}
	
	
	
	
	
	
	
	
	public void deleteLesson(String id)
	{
		lessonRepository.deleteById(id);
	}	
	
}
