package io.github.matirosen.ecommercechallenge.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {
    private Long id;
    private SubscriptionType type;
    private Double price;
    private Date startDate;
    private Date expirationDate;

    public SubscriptionDTO(Subscription subscription) {
        this.id = subscription.getId();
        this.type = subscription.getType();
        this.price = subscription.getPrice();
        this.startDate = subscription.getStartDate();
        this.expirationDate = subscription.getExpirationDate();
    }
}
