package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.payload.request.CreatePostRequest;
import com.openclassrooms.mddapi.dto.payload.response.PostDiplayResponse;
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

import java.time.LocalDateTime;
import java.util.List;

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

		PostDto postDto = new PostDto(
				null,
				createPostRequest.getTitle(),
				createPostRequest.getContent(),
				topic,
				author,
				now,
				now
		);
		Post post = PostMapper.mapFromPostDtoToPost(postDto);
		Post SavedPost = postRepository.save(post);

		return PostMapper.mapFromPostToPostDto(SavedPost);
	}

	@Override
	public List<PostDiplayResponse> getAllPosts() {
		List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(PostMapper::mapFromPostToPostDisplayList)
                .toList();
	}

	@Override
	public List<PostDiplayResponse> getSubPostsByUserEmail(String email) {
		List<User> user = List.of(UserMapper.mapFromUserDtoToUser(userService.getUserByEmail(email)));
		List<Topic> topics = topicService.getSubscriebTopicsByUserEmail(email).stream()
				.map(TopicMapper::mapFromTopicDtoToTopic)
				.toList();

		List<Post> posts = postRepository.findAllByUserInAndTopicIn(user, topics);

		return posts.stream()
				.map(PostMapper::mapFromPostToPostDisplayList)
				.toList();
	}

	@Override
	public PostDiplayResponse getPostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
		return PostMapper.mapFromPostToPostDisplay(post);
	}
}
