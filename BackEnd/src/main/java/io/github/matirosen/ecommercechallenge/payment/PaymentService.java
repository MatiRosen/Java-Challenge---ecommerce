package io.github.matirosen.ecommercechallenge.payment;

public interface PaymentService {

    PaymentResponse charge(PaymentRequest paymentRequest) throws Exception;
}
