package com.example.emploinet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.User;


public interface UserRepository extends MongoRepository<User, String> {
  
}
