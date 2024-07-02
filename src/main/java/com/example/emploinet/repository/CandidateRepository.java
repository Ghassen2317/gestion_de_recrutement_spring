package com.example.emploinet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.enums.Sexe;
import com.example.emploinet.enums.Diplome;
import com.example.emploinet.model.Candidate;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
  public Candidate findBySexe(Sexe sexe);
  public Candidate findByDiplome(Diplome diplome);
}
