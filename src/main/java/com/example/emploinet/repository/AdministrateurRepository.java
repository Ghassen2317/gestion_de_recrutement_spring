package com.example.emploinet.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.Administrateur;

public interface AdministrateurRepository extends MongoRepository<Administrateur, String> {
  Administrateur findByEmail(String email);
}
