package io.github.matirosen.ecommercechallenge.subscription;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    MONTHLY("MONTHLY", 9.99, "Pay monthly", 30, "price_1OjpicGO7GTIESILEETxQYy9"),
    ANNUAL("ANNUAL", 90.00, "Save 25% on your subscription by paying annually", 365, "price_1Ojpx7GO7GTIESIL4lDCkNNJ");

    private final String name;
    private final Double price;
    private final String description;
    private final int days;
    private final String priceId;

    SubscriptionType(String name, Double price, String description, int days, String priceId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.days = days;
        this.priceId = priceId;
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
