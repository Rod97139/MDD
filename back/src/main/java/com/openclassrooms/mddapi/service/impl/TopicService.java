package com.openclassrooms.mddapi.service.impl;

import java.util.List;

import com.openclassrooms.mddapi.service.ITopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
@AllArgsConstructor
public class TopicService implements ITopicService {

	private TopicRepository topicRepository;

	@Override
	public List<Topic> getTopics() {
		return topicRepository.findAll();
	}
	
}
