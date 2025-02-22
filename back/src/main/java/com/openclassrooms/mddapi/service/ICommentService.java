package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.payload.request.CommentRequest;

import java.util.List;

public interface ICommentService {
    List<CommentDto> getAllComments(Long postId);
    void createComment(CommentRequest commentRequest, String email);
}
