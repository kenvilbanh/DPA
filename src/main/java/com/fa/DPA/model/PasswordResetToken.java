package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = CustomerAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private CustomerAccount usler;

    private LocalDate expiryDate;
}
