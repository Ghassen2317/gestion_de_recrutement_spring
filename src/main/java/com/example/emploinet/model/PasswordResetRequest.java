package com.example.emploinet.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "password_reset_requests")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordResetRequest {
    @Id
    private String id;
    private String linkIdentifier;
    private LocalDateTime expiryDate;

    @DBRef
    private User user;
}
