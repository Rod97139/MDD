package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;

public class PostMapper {

    public static Post mapFromPostDtoToPost(PostDto postDto) {
        return new Post(
                postDto.getId(),
                postDto.getTitle(),
                postDto.getContent(),
                postDto.getTopic(),
                postDto.getUser(),
                postDto.getCreatedAt(),
                postDto.getUpdatedAt()
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
}
