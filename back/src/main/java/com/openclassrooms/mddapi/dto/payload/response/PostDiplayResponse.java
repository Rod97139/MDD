package com.openclassrooms.mddapi.dto.payload.response;

import com.openclassrooms.mddapi.dto.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDiplayResponse {
    private Long id;
    private String title;
    private String content;
    private TopicDto topic;
    private UserDisplayDto author;
    private String createdAt;
    private String updatedAt;
}
