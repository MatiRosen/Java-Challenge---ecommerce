package io.github.matirosen.ecommercechallenge.subscription;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    MONTHLY("Monthly", 9.99, "Pay monthly", 30),
    ANNUAL("Annual", 90.00, "Save 25% on your subscription by paying annually", 365);

    private final String name;
    private final Double price;
    private final String description;
    private final int days;

    SubscriptionType(String name, Double price, String description, int days) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.days = days;
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
