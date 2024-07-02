package com.example.emploinet.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.PasswordResetRequest;
import com.example.emploinet.model.User;


public interface PasswordResetRequestRepository extends MongoRepository<PasswordResetRequest, String> { 
  Optional<PasswordResetRequest> findByLinkIdentifier(String linkIdentifier);
  Optional<PasswordResetRequest> findByUser(User user);
} 