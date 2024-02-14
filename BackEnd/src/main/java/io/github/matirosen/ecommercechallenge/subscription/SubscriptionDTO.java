package io.github.matirosen.ecommercechallenge.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {
    private Long id;
    private SubscriptionType type;
    private Double price;

    public SubscriptionDTO(Subscription subscription) {
        this.id = subscription.getId();
        this.type = subscription.getType();
        this.price = subscription.getPrice();
    }
}
