package com.example.emploinet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.Entreprise;

public interface EntrepriseRepository extends MongoRepository<Entreprise, String> {

}
