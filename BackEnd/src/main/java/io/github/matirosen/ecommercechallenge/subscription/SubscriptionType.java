package io.github.matirosen.ecommercechallenge.subscription;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    MONTHLY("Monthly", 9.99, "Pay monthly"),
    ANNUAL("Annual", 90.00, "Save 25% on your subscription by paying annually");

    private final String name;
    private final Double price;
    private final String description;

    SubscriptionType(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public static SubscriptionType fromName(String name) {
        for (SubscriptionType type : values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
