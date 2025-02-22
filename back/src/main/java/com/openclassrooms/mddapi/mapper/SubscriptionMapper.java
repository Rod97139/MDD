package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.model.Subscription;

public class SubscriptionMapper {
    public static Subscription mapFromSubscriptionDtoToSubscription(SubscriptionDto subscriptionDto) {
        return new Subscription(
                subscriptionDto.getId(),
                subscriptionDto.getUser(),
                subscriptionDto.getTopic(),
                subscriptionDto.getStartDate(),
                subscriptionDto.getEndDate(),
                subscriptionDto.getStatus()
        );
    }

    public static SubscriptionDto mapFromSubscriptionToSubscriptionDto(Subscription subscription) {
        return new SubscriptionDto(
                subscription.getId(),
                subscription.getUser(),
                subscription.getTopic(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatus()
        );
    }

}
