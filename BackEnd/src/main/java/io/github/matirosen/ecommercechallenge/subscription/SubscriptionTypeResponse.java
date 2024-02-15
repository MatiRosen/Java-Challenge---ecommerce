package io.github.matirosen.ecommercechallenge.subscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionTypeResponse {

    private String name;
    private Double price;
    private String description;
}