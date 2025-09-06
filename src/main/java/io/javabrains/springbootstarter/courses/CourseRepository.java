package io.javabrains.springbootstarter.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseRepository extends JpaRepository<Course, String>
{
	public List<Optional<Course>> findByTopicId(String topicId);
	public Optional<Course>	findByTopicIdAndId(String topicId, String id);

}
