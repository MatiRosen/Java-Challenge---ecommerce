package io.github.matirosen.ecommercechallenge.payment;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "payments")
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "platform", nullable = false)
    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
