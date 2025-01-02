package io.javabrains.springbootstarter.lessons;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LessonRepository extends JpaRepository<Lesson, String>
{
	public List<Lesson> findByCourseId(String courseId);
}
