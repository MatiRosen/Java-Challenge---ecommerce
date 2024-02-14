package io.github.matirosen.ecommercechallenge.subscription;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    MONTHLY("Monthly", 9.99),
    ANNUAL("Annual", 90.00);

    private final String name;
    private final Double price;

    SubscriptionType(String name, Double price) {
        this.name = name;
        this.price = price;
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
