package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.payload.request.CreatePostRequest;
import com.openclassrooms.mddapi.dto.payload.response.MessageResponse;
import com.openclassrooms.mddapi.dto.payload.response.PostDiplayResponse;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {

    private IPostService postService;
    private JWTService jwtService;

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<MessageResponse> createPost(
            @RequestBody CreatePostRequest createPostRequest,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);

        createPostRequest.setEmail(email);

        postService.createPost(createPostRequest);
        return new ResponseEntity<>(new MessageResponse("Post created successfully"), HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<PostDiplayResponse>> getAllPosts() {
//        return ResponseEntity.ok(postService.getAllPosts());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDiplayResponse> getPostById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/sub")
    public List<PostDiplayResponse> getPostsByUserEmail(
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);
//        return postService.getSubPostsByUserEmail(email);
        List<PostDiplayResponse> posts = postService.getSubPostsByUserEmail(email);
        return posts;
    }
}
