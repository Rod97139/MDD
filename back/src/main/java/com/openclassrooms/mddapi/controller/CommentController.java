package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.payload.request.CommentRequest;
import com.openclassrooms.mddapi.dto.payload.response.CommentResponse;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.service.JWTService;
import com.openclassrooms.mddapi.service.impl.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;
    private JWTService jwtService;

    @PostMapping()
    public CommentResponse addComment(
        @RequestBody CommentRequest commentRequest,
        HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);
        return commentService.createComment(commentRequest, email);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        List<CommentDto> commentsDtos = commentService.getAllComments(postId);
        List<CommentResponse> comments = commentsDtos.stream().map(CommentMapper::toResponse).toList();
        return ResponseEntity.ok(comments);
    }
}
