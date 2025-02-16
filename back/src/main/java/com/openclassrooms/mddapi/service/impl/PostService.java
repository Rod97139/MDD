package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService implements IPostService {

	private PostRepository postRepository;

}
