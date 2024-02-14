package io.github.matirosen.ecommercechallenge.subscription;

import io.github.matirosen.ecommercechallenge.user.User;
import io.github.matirosen.ecommercechallenge.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    public SubscriptionResponse create(SubscriptionRequest request) throws RuntimeException {
        SubscriptionType subscriptionType = SubscriptionType.fromName(request.getSubscriptionType());

        if (subscriptionType == null) {
            throw new RuntimeException("Invalid subscription type");
        }

        User user = userService.getUserById(request.getUserId());

        if (userHasSubscription(user.getId())) {
            throw new RuntimeException("User already has a subscription");
        }

        Subscription subscription = Subscription.builder()
                .user(user)
                .type(subscriptionType)
                .price(subscriptionType.getPrice())
                .build();

        subscriptionRepository.save(subscription);

        return SubscriptionResponse.builder()
                .message("Subscription created successfully")
                .build();
    }

    public SubscriptionDTO getSubscriptionByUserId(Long userId) throws RuntimeException{
        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User does not have a subscription"));

        return new SubscriptionDTO(subscription);
    }

    private boolean userHasSubscription(Long userId) {
        try {
            getSubscriptionByUserId(userId);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}