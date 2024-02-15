package io.github.matirosen.ecommercechallenge.payment.stripe;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import io.github.matirosen.ecommercechallenge.payment.*;
import io.github.matirosen.ecommercechallenge.subscription.SubscriptionType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StripeService implements PaymentService {

    @Value("${stripe.secret}")
    private String secretKey;

    @Value("${front_base_url}")
    private String baseUrl;

    private final PaymentRepository paymentRepository;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public String createCheckoutSession(SubscriptionType subscriptionType, Long userId) throws Exception {
        if (subscriptionType == null || userId == null) {
            throw new IllegalArgumentException("Subscription type and userId are required");
        }

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                        .setSuccessUrl(baseUrl +  "/profile")
                        .setCancelUrl(baseUrl + "/subscriptions")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setPrice(subscriptionType.getPriceId())
                                        .setQuantity(1L)
                                        .build())
                        .putMetadata("userId", String.valueOf(userId))
                        .putMetadata("subscriptionType", subscriptionType.name())
                        .build();

        Session session = Session.create(params);
        return session.getUrl();
    }

    @Override
    public PaymentResponse charge(PaymentRequest paymentRequest) throws Exception {
        PaymentIntent intent = PaymentIntent.retrieve(paymentRequest.getToken());

        PaymentIntent confirmedIntent;
        try{
            confirmedIntent = intent.confirm();
        } catch (Exception e) {
            confirmedIntent = intent;
        }

        Payment payment = Payment.builder()
                .amount(paymentRequest.getAmount())
                .currency(paymentRequest.getCurrency())
                .description(paymentRequest.getDescription())
                .platform(Platform.STRIPE)
                .status(PaymentStatus.COMPLETED)
                .build();

        try{
            paymentRepository.save(payment);
        } catch (Exception e) {
            System.out.println("Error saving payment: " + e.getMessage());
        }


        return PaymentResponse.from(payment, confirmedIntent.getId());
    }
}
