package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.payload.request.CreatePostRequest;
import com.openclassrooms.mddapi.dto.payload.request.UserLoginRequest;
import com.openclassrooms.mddapi.dto.payload.response.MessageResponse;
import com.openclassrooms.mddapi.dto.payload.response.PostDiplayResponse;
import com.openclassrooms.mddapi.dto.payload.response.TokenResponse;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.JWTService;
import com.openclassrooms.mddapi.service.impl.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {

    private IPostService postService;
    private JWTService jwtService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<MessageResponse> createPost(
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart("topic") String topic,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);

        CreatePostRequest createPostRequest = new CreatePostRequest(title, content, topic, email);

        postService.createPost(createPostRequest);
        return new ResponseEntity<>(new MessageResponse("Post created successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDiplayResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

}
