package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.payload.request.CommentRequest;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private PostRepository postRepository;
    private final LocalDateTime now = LocalDateTime.now();


    @Override
    public List<CommentDto> getAllComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        return commentRepository.findAllByPost(post).stream().map(CommentMapper::toDto).toList();
    }

    @Override
    public void createComment(CommentRequest commentRequest, String email) {
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = new Comment(
                null,
                commentRequest.getContent(),
                now,
                now,
                post,
                user
        );
        commentRepository.save(comment);
    }
}
