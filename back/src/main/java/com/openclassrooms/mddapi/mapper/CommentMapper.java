package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.payload.response.CommentResponse;
import com.openclassrooms.mddapi.model.Comment;

public class CommentMapper {

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(
            comment.getId(),
            comment.getContent(),
            comment.getPost(),
            comment.getUser(),
            comment.getCreatedAt(),
            comment.getUpdatedAt()
        );
    }

    public static Comment toEntity(CommentDto commentDto) {
        return new Comment(
            commentDto.getId(),
            commentDto.getContent(),
            commentDto.getCreatedAt(),
            commentDto.getUpdatedAt(),
            commentDto.getPost(),
            commentDto.getUser()
        );
    }

    public static CommentResponse toResponse(CommentDto commentDto) {
        return new CommentResponse(
            commentDto.getId(),
            commentDto.getContent(),
            commentDto.getPost().getId(),
            commentDto.getUser().getId(),
            commentDto.getCreatedAt().toString(),
            commentDto.getUpdatedAt().toString()
        );
    }
}
