package com.openclassrooms.mddapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.ITopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
@AllArgsConstructor
public class TopicService implements ITopicService {

	private final UserRepository userRepository;
	private TopicRepository topicRepository;
	private SubscriptionRepository subscriptionRepository;
	private final LocalDateTime now = LocalDateTime.now();

	@Override
	public List<Topic> getAllTopics() {
		return topicRepository.findAll();
	}

	@Override
	public TopicDto getTopicByName(String topic) {
		Topic topicFromDatabse = topicRepository.findByName(topic);
		return TopicMapper.mapFromTopicToTopicDto(topicFromDatabse);
	}

	@Override
	public void subscribe(SubscriptionRequest subscriptionRequest) {
		Optional<User> user = userRepository.findByEmail(subscriptionRequest.getUserEmail());

		Optional<Topic> topic = topicRepository.findById(subscriptionRequest.getTopicId());


		if (user.isPresent() && topic.isPresent()) {
			Subscription subscription = new Subscription(
					null,
					user.get(),
					topic.get(),
					now,
					null,
					true
			);
			subscriptionRepository.save(subscription);
		}
	}

	@Override
	public void unSubscribe(SubscriptionRequest subscriptionRequest) {
		Optional<User> user = userRepository.findByEmail(subscriptionRequest.getUserEmail());

		Optional<Topic> topic = topicRepository.findById(subscriptionRequest.getTopicId());

		if (user.isPresent() && topic.isPresent()) {
			Subscription subscription = subscriptionRepository.findByUserAndTopicAndStatusEquals(user.get(), topic.get(), true);
			subscription.setStatus(false);
			subscription.setEndDate(now);
			subscriptionRepository.save(subscription);
		} else {
			throw new RuntimeException("User or Topic not found");
		}
	}

	@Override
	public List<TopicDto> getSubscriebTopicsByUserEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		List<Subscription> subscriptions;
		if (user.isPresent()) {
			subscriptions = subscriptionRepository.findByUserAndStatusEquals(user.get(), true);
		} else {
			throw new RuntimeException("Subscription not found");
		}


		List<Topic> topics = subscriptions.stream()
				.map(Subscription::getTopic)
				.toList();

		return topics.stream()
				.map(TopicMapper::mapFromTopicToTopicDto)
				.toList();
	}

}
