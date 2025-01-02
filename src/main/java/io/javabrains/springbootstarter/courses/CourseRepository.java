package io.javabrains.springbootstarter.courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseRepository extends JpaRepository<Course, String>
{
	public List<Course> findByTopicId(String topicId);

}
