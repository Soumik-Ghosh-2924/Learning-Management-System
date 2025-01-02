package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService 
{
	@Autowired
	private TopicRepository topicRepository;
	
	
	//to get all the topics 
	public List<Topic> getAllTopics()
	{
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}
	
	
	
	
	//to get a specific topic
	public Optional<Topic> getTopic(String id)
	{
		return (Optional<Topic>)topicRepository.findById(id); 
	}
	
	
	
	
	
	//to add a specific topic to the database
	public void addTopic(Topic topic)
	{
		topicRepository.save(topic);
	}
	
	
	
	
	
	//to update the topic in the dB:
	public void updateTopic(Topic topic, String id)
	{
		topicRepository.save(topic);
	}
	
	
	
	
	
	//to delete the topic from the repository
	public void deleteTopic(String id)
	{
		topicRepository.deleteById(id);
	}	

}
