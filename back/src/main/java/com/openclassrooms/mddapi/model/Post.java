package com.openclassrooms.mddapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "topic_id")
	private Topic topic;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// TODO : to finish...


}
