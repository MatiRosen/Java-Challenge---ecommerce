package io.github.matirosen.ecommercechallenge.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {

    private Long userId;
    private String subscriptionType;
}
