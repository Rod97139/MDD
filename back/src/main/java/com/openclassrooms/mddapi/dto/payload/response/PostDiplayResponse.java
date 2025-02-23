package com.openclassrooms.mddapi.dto.payload.response;

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
    private Long topic;
    private UserDisplayDto author;
    private String createdAt;
    private String updatedAt;
}
