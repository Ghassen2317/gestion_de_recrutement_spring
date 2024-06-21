package com.example.emploinet.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.User;


public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByEmail(String email);
}
