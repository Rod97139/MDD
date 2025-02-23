package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.payload.response.PostDiplayResponse;
import com.openclassrooms.mddapi.model.Post;

import java.util.List;

public class PostMapper {

    public static Post mapFromPostDtoToPost(PostDto postDto) {
        return new Post(
                postDto.getId(),
                postDto.getTitle(),
                postDto.getContent(),
                postDto.getTopic(),
                postDto.getUser(),
                postDto.getCreatedAt(),
                postDto.getUpdatedAt(),
                null
        );
    }

    public static PostDto mapFromPostToPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getTopic(),
                post.getUser(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    public static PostDiplayResponse mapFromPostToPostDisplayList(Post post) {
        return new PostDiplayResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                TopicMapper.mapFromTopicToTopicDto(post.getTopic()),
                UserMapper.mapFromUserToUserDisplayDto(post.getUser()),
                post.getCreatedAt().toString(),
                post.getUpdatedAt().toString()
        );

    }

    public static PostDiplayResponse mapFromPostToPostDisplay(Post post) {
        return new PostDiplayResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                TopicMapper.mapFromTopicToTopicDto(post.getTopic()),
                UserMapper.mapFromUserToUserDisplayDto(post.getUser()),
                post.getCreatedAt().toString(),
                post.getUpdatedAt().toString()
        );
    }
}
