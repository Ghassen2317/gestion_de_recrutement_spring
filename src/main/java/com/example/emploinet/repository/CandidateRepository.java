package com.example.emploinet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.Candidate;

public interface CandidateRepository extends MongoRepository<Candidate, String> {

}
