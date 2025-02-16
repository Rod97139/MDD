package com.openclassrooms.mddapi.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.service.ITopicService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/topic")
@AllArgsConstructor
public class TopicController {
	
	private ITopicService topicService;

	@GetMapping
	public List<Topic> getTopics() {
		return topicService.getTopics();
	}
	
	
}
