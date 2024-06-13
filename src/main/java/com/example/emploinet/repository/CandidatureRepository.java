package com.example.emploinet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.Candidature;

public interface CandidatureRepository extends MongoRepository<Candidature, String> {

}
