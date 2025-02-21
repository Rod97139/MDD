package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.model.Topic;

public interface ITopicService {

	List<Topic> getAllTopics();

    TopicDto getTopicByName(String topic);

    void subscribe(SubscriptionRequest subscriptionRequest);

    void unSubscribe(SubscriptionRequest subscriptionRequest);

    List<TopicDto> getSubscriebTopicsByUserEmail(String email);
}
