package io.github.matirosen.ecommercechallenge.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionCheckoutRequest {

    private SubscriptionType subscriptionType;
    private Long userId;

}