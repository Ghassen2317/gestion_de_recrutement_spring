package com.example.emploinet.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(String name);
}