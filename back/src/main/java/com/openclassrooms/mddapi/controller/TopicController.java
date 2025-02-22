package com.openclassrooms.mddapi.controller;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.dto.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.service.ITopicService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/topic")
@AllArgsConstructor
public class TopicController {
	
	private ITopicService topicService;
	private JWTService jwtService;

	@GetMapping
	public List<Topic> getTopics() {
		return topicService.getAllTopics();
	}

	@GetMapping("/sub")
	public List<TopicDto> getTopicsByUserEmail(
			HttpServletRequest request
	) {
		String token = request.getHeader("Authorization").substring(7);
		String email = jwtService.getSubjectFromToken(token);
		return topicService.getSubscriebTopicsByUserEmail(email);
	}

	@PostMapping("/subscribe/{topicId}")
	public ResponseEntity<MessageResponse> subscribe(
			@PathVariable Long topicId,
			HttpServletRequest request
	) {
		String token = request.getHeader("Authorization").substring(7);
		String userEmail = jwtService.getSubjectFromToken(token);

		SubscriptionRequest subscriptionRequest = new SubscriptionRequest(
				topicId,
				userEmail
		);

		topicService.subscribe(subscriptionRequest);
		return ResponseEntity.ok(new MessageResponse("Subscribed successfully"));
	}

	@PostMapping("/unsubscribe/{topicId}")
	public ResponseEntity<MessageResponse> unSubscribe(
			@PathVariable Long topicId,
			HttpServletRequest request
	) {
		String token = request.getHeader("Authorization").substring(7);
		String userEmail = jwtService.getSubjectFromToken(token);

		SubscriptionRequest subscriptionRequest = new SubscriptionRequest(
				topicId,
				userEmail
		);

		topicService.unSubscribe(subscriptionRequest);
		return ResponseEntity.ok(new MessageResponse("unsubscribed successfully"));
	}

	
	
}
