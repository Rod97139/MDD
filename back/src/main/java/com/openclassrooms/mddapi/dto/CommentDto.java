package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private Post post;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
