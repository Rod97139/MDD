package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.payload.request.CommentRequest;
import com.openclassrooms.mddapi.dto.payload.response.CommentResponse;

import java.util.List;

public interface ICommentService {
    List<CommentDto> getAllComments(Long postId);
    CommentResponse createComment(CommentRequest commentRequest, String email);
}
