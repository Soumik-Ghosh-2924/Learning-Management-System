package io.javabrains.springbootstarter.topic;

//import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopicController 
{
	@Autowired
	private TopicService topicService;
	
	@GetMapping("/topics")
	public List<Topic> getAllTopics() 
	{
		System.out.println("Here's all the topics available in the system.");
		return topicService.getAllTopics();
				
	}
	 
	@GetMapping("/topics/{id}")
	public Optional<Topic> getTopic(@PathVariable String id)
	{
		System.out.println("Here's the specific topicyou requested for.");
		return (Optional<Topic>)topicService.getTopic(id);
	}
	
	
	
	
	
	
	//@RequestMapping(method=RequestMethod.POST, value="/topics")
	@PostMapping("/topics")
	public String addTopic(@RequestBody Topic ntopic) {
		topicService.addTopic(ntopic);
		return "New Topic Added";
	}
	
	
	
	
	@PutMapping("/topics/{id}")
	public String updateTopic(@RequestBody Topic topic, @PathVariable String id){
		topicService.updateTopic(topic, id);
		return "Updated the Topic.";
	}
	
	
	
	
	
	@DeleteMapping("/topics/{id}")
	public String deleteTopic(@PathVariable String id)
	{
		topicService.deleteTopic(id);
		
		return "Deleted the topic";
	}
	
	

}
