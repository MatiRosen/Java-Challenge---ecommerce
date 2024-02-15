package io.github.matirosen.ecommercechallenge.subscription;


import io.github.matirosen.ecommercechallenge.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "subscriptions")
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SubscriptionType type;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}