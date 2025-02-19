package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;

public class TopicMapper {

    public static Topic mapFromTopicDtoToTopic(Topic topic) {
        return new Topic(
                topic.getId(),
                topic.getName(),
                topic.getDescription(),
                topic.getCreatedAt(),
                topic.getUpdatedAt()
        );
    }

    public static TopicDto mapFromTopicToTopicDto(TopicDto topicDto) {
        return new TopicDto(
                topicDto.getId(),
                topicDto.getName(),
                topicDto.getDescription(),
                topicDto.getCreatedAt(),
                topicDto.getUpdatedAt()
        );
    }

}
