package io.github.matirosen.ecommercechallenge.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private String id;
    private double amount;
    private String currency;
    private PaymentStatus status;

    public static PaymentResponse from(Payment payment, String id) {
        return new PaymentResponse(id, payment.getAmount(), payment.getCurrency(), payment.getStatus());
    }
}