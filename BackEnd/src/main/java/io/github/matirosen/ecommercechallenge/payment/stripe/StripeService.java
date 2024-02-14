package io.github.matirosen.ecommercechallenge.payment.stripe;

import com.stripe.Stripe;
import com.stripe.model.Charge;
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
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentRequest.getAmount());
        chargeParams.put("currency", paymentRequest.getCurrency());
        chargeParams.put("description", paymentRequest.getDescription());
        chargeParams.put("source", paymentRequest.getToken());

        Charge charge = Charge.create(chargeParams);

        Payment payment = Payment.builder()
                .amount(Double.valueOf(charge.getAmount()))
                .currency(charge.getCurrency())
                .description(charge.getDescription())
                .platform(Platform.STRIPE)
                .build();

        paymentRepository.save(payment);

        return PaymentResponse.from(payment, charge.getId());
    }
}
