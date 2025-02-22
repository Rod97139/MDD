package com.openclassrooms.mddapi.dto.payload.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {
    private Long topicId;
    private String userEmail;
}
