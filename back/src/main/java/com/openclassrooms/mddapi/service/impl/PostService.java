package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.payload.request.CreatePostRequest;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.ITopicService;
import com.openclassrooms.mddapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostService implements IPostService {

	private PostRepository postRepository;
	private IUserService userService;
	private ITopicService topicService;
	private final LocalDateTime now = LocalDateTime.now();

	@Override
	public PostDto createPost(CreatePostRequest createPostRequest) {

		User author = UserMapper.mapFromUserDtoToUser(userService.getUserByEmail(createPostRequest.getEmail()));
		Topic topic = TopicMapper.mapFromTopicDtoToTopic(topicService.getTopicByName(createPostRequest.getTopic()));

		PostDto postDto = new PostDto();
		postDto.setId(null);
		postDto.setTitle(createPostRequest.getTitle());
		postDto.setContent(createPostRequest.getContent());
		postDto.setTopic(topic);
		postDto.setUser(author);
		postDto.setCreatedAt(now);
		postDto.setUpdatedAt(now);

		Post post = PostMapper.mapFromPostDtoToPost(postDto);
		Post SavedPost = postRepository.save(post);

		return PostMapper.mapFromPostToPostDto(SavedPost);
	}

}
