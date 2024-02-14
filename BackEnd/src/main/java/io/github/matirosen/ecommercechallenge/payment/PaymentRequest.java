package io.github.matirosen.ecommercechallenge.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private double amount;
    private String currency;
    private String description;
    private String token;
}
