package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;

public class TopicMapper {

    public static Topic mapFromTopicDtoToTopic(TopicDto topicDto) {
        return new Topic(
                topicDto.getId(),
                topicDto.getName(),
                topicDto.getDescription(),
                topicDto.getCreatedAt(),
                topicDto.getUpdatedAt(),
                null
        );
    }

    public static TopicDto mapFromTopicToTopicDto(Topic topic) {
        return new TopicDto(
                topic.getId(),
                topic.getName(),
                topic.getDescription(),
                topic.getCreatedAt(),
                topic.getUpdatedAt()
        );
    }

}
