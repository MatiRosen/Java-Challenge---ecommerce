package io.github.matirosen.ecommercechallenge.payment.stripe;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import com.stripe.model.checkout.Session;
import io.github.matirosen.ecommercechallenge.payment.PaymentRequest;
import io.github.matirosen.ecommercechallenge.payment.PaymentResponse;
import io.github.matirosen.ecommercechallenge.subscription.SubscriptionCheckoutRequest;
import io.github.matirosen.ecommercechallenge.subscription.SubscriptionService;
import jakarta.servlet.http.HttpServletRequest;
import io.github.matirosen.ecommercechallenge.subscription.SubscriptionRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class StripeController {

    private final StripeService paymentService;
    private final SubscriptionService subscriptionService;

    @Value("${stripe.endpoint.secret}")
    private String endpointSecret;



    @PostMapping("/charge")
    public ResponseEntity<PaymentResponse> charge(@RequestBody PaymentRequest paymentRequest) {
        try {
            return ResponseEntity.ok(paymentService.charge(paymentRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> createCheckoutSession(@RequestBody SubscriptionCheckoutRequest request) {
        try {
            String url = paymentService.createCheckoutSession(request.getSubscriptionType(), request.getUserId());
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, HttpServletRequest request) {
        String sigHeader = request.getHeader("Stripe-Signature");
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            return ResponseEntity.badRequest().build();
        }

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            return ResponseEntity.badRequest().build();
        }


        if (event.getType().equals("checkout.session.completed")) {
            Session session = (Session) stripeObject;
            String userId = session.getMetadata().get("userId");
            if (userId != null) {
                SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
                subscriptionRequest.setUserId(Long.parseLong(userId));
                subscriptionRequest.setSubscriptionType(session.getMetadata().get("subscriptionType"));
                try {
                    subscriptionService.create(subscriptionRequest);
                } catch (RuntimeException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
                }
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}
