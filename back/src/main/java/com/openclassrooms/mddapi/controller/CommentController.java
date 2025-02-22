package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.payload.response.CommentResponse;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.service.impl.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    @PostMapping("/{postId}")
    public void addComment(@PathVariable Long postId) {

    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        List<CommentDto> commentsDtos = commentService.getAllComments(postId);
        List<CommentResponse> comments = commentsDtos.stream().map(CommentMapper::toResponse).toList();
        return ResponseEntity.ok(comments);
    }


}
