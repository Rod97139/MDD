package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.payload.request.CreatePostRequest;
import com.openclassrooms.mddapi.dto.payload.response.PostDiplayResponse;

import java.util.List;

public interface IPostService {


    PostDto createPost(CreatePostRequest createPostRequest);

    List<PostDiplayResponse> getAllPosts();
}
