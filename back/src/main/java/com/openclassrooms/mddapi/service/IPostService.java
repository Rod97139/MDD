package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.payload.request.CreatePostRequest;

public interface IPostService {


    PostDto createPost(CreatePostRequest createPostRequest);
}
