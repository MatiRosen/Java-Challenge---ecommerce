package io.github.matirosen.ecommercechallenge.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/subscription")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/")
    public ResponseEntity<SubscriptionResponse> create(@RequestBody SubscriptionRequest request) {
        try {
            return ResponseEntity.ok(subscriptionService.create(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(SubscriptionResponse.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SubscriptionDTO> getSubscriptionByUserId(@PathVariable Long userId) {
        try{
            return ResponseEntity.ok(subscriptionService.getSubscriptionByUserId(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/types")
    public ResponseEntity<SubscriptionType[]> getSubscriptionTypes() {
        return ResponseEntity.ok(SubscriptionType.values());
    }
}
